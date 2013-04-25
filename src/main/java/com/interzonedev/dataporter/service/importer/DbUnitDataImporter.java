package com.interzonedev.dataporter.service.importer;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Logger;

import com.interzonedev.dataporter.service.ConnectionSource;
import com.interzonedev.dataporter.service.DataSourceProperties;
import com.interzonedev.dataporter.service.dbunit.DbUnitHelper;

@Named("dataImporter")
public class DbUnitDataImporter implements DataImporter {

	private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Inject
	@Named("connectionSource")
	private ConnectionSource connectionSource;

	@Inject
	@Named("dbUnitHelper")
	private DbUnitHelper dbUnitHelper;

	@Override
	public void importData(DataSourceProperties dataSourceProperties, byte[] dataSetContents)
			throws DataImporterException {

		Assert.notNull(dataSourceProperties, "importData: The data source properties must be set");
		Assert.notNull(dataSetContents, "importData: The data set contents must be set");

		Connection connection = null;

		try {

			FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
			ByteArrayInputStream dataSetStream = new ByteArrayInputStream(dataSetContents);
			IDataSet dataSet = flatXmlDataSetBuilder.build(dataSetStream);

			connection = connectionSource.getConnection(dataSourceProperties);

			IDatabaseConnection databaseConnection = dbUnitHelper.getDatabaseConnection(connection);

			DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);

		} catch (Throwable t) {
			String errorMessage = "Error importing data";
			log.error("importData: " + errorMessage, t);
			throw new DataImporterException(errorMessage, t);
		} finally {
			try {
				if ((null != connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException se) {
				log.error("importData: Error closing connection", se);
			}
		}

	}

}
