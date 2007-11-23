package org.gdms.sql.customQuery.spatial.convert;

import java.util.LinkedList;
import java.util.List;

import org.gdms.data.DataSource;
import org.gdms.data.DataSourceFactory;
import org.gdms.data.ExecutionException;
import org.gdms.data.indexes.SpatialIndex;
import org.gdms.data.metadata.Metadata;
import org.gdms.data.types.Constraint;
import org.gdms.data.types.ConstraintNames;
import org.gdms.data.types.DefaultType;
import org.gdms.data.types.Type;
import org.gdms.data.values.Value;
import org.gdms.data.values.ValueFactory;
import org.gdms.driver.memory.ObjectMemoryDriver;
import org.gdms.spatial.GeometryValue;
import org.gdms.spatial.SpatialDataSourceDecorator;
import org.gdms.sql.customQuery.CustomQuery;
import org.gdms.sql.strategies.FirstStrategy;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;

// select * from ds where the_geom IS NOT NULL and isVali 

//select Explode() from points;
//select Explode(the_geom) from points;

public class Explode implements CustomQuery {
	public String getName() {
		return "Explode";
	}

	public String getDescription() {
		return "Convert any GeometryCollection into a set of single Geometries";
	}

	public DataSource evaluate(DataSourceFactory dsf, DataSource[] tables,
			Value[] values) throws ExecutionException {
		if (tables.length != 1) {
			throw new ExecutionException("Explode only operates on one table");
		}
		if (values.length > 1) {
			throw new ExecutionException(
					"Explode operates with no more than one value");
		}

		DataSource resultDs = null;
		try {
			final SpatialDataSourceDecorator sds = new SpatialDataSourceDecorator(
					tables[0]);
			sds.open();
			if (1 == values.length) {
				// if no spatial's field's name is provided, the default (first)
				// one is arbitrarily choose.
				final String spatialFieldName = values[0].toString();
				sds.setDefaultGeometry(spatialFieldName);
			}
			final int spatialFieldIndex = sds.getSpatialFieldIndex();

			final Metadata metadata = sds.getMetadata();
			// a simple :
			// final ObjectMemoryDriver driver = new
			// ObjectMemoryDriver(metadata);
			// is not enough... we also need to remove all pk or unique
			// constraints !
			final int fieldCount = metadata.getFieldCount();
			final Type[] fieldsTypes = new Type[fieldCount];
			final String[] fieldsNames = new String[fieldCount];

			for (int fieldId = 0; fieldId < fieldCount; fieldId++) {
				fieldsNames[fieldId] = metadata.getFieldName(fieldId);
				fieldsTypes[fieldId] = metadata.getFieldType(fieldId);

				if ((null == fieldsTypes[fieldId]
						.getConstraint(ConstraintNames.PK))
						|| ((null == fieldsTypes[fieldId]
								.getConstraint(ConstraintNames.UNIQUE)))) {
					// let us try to remove the PK/UNIQUE constraint...
					final List<Constraint> lc = new LinkedList<Constraint>();
					for (Constraint c : fieldsTypes[fieldId].getConstraints()) {
						if ((ConstraintNames.PK != c.getConstraintName())
								&& (ConstraintNames.UNIQUE != c
										.getConstraintName())) {
							lc.add(c);
						}
					}
					fieldsTypes[fieldId] = new DefaultType(lc
							.toArray(new Constraint[0]), fieldsTypes[fieldId]
							.getDescription(), fieldsTypes[fieldId]
							.getTypeCode());
				}
			}
			final ObjectMemoryDriver driver = new ObjectMemoryDriver(
					fieldsNames, fieldsTypes);
			resultDs = dsf.getDataSource(driver);
			resultDs.open();

			long nbOfRows = sds.getRowCount();
			for (long rowIndex = 0; rowIndex < nbOfRows; rowIndex++) {
				final Value[] fieldsValues = sds.getRow(rowIndex);
				final Geometry geometry = ((GeometryValue) fieldsValues[spatialFieldIndex])
						.getGeom();

				if (geometry instanceof GeometryCollection) {
					final long nbOfGeometries = geometry.getNumGeometries();
					for (int i = 0; i < nbOfGeometries; i++) {
						fieldsValues[spatialFieldIndex] = ValueFactory
								.createValue(geometry.getGeometryN(i));
						resultDs.insertFilledRow(fieldsValues);
					}

				} else {
					resultDs.insertFilledRow(fieldsValues);
				}
			}

			resultDs.commit();
			sds.cancel();
			// spatial index for the new grid

			dsf.getIndexManager().buildIndex(resultDs.getName(),
					sds.getDefaultGeometry(), SpatialIndex.SPATIAL_INDEX);

			FirstStrategy.indexes = true;
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
		return resultDs;
	}
}