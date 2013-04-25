package com.interzonedev.dataporter.service.exporter;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Logger;

import com.interzonedev.dataporter.service.ConnectionSource;
import com.interzonedev.dataporter.service.DataSourceProperties;
import com.interzonedev.dataporter.service.dbunit.DbUnitHelper;

@Named("dataExporter")
public class DbUnitDataExporter implements DataExporter {

	private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Inject
	@Named("connectionSource")
	private ConnectionSource connectionSource;

	@Inject
	@Named("dbUnitHelper")
	private DbUnitHelper dbUnitHelper;

	@Override
	public byte[] exportData(DataSourceProperties dataSourceProperties, List<String> tableNames)
			throws DataExporterException {

		Assert.notNull(dataSourceProperties, "exportData: The data source properties must be set");
		Assert.notNull(tableNames, "exportData: The table names must be set");
		Assert.notEmpty(tableNames, "exportData: The table names must not be empty");

		log.debug("exportData: Exporting " + tableNames + " from " + dataSourceProperties);

		byte[] data = null;

		Connection connection = null;

		try {

			connection = connectionSource.getConnection(dataSourceProperties);

			IDatabaseConnection databaseConnection = dbUnitHelper.getDatabaseConnection(connection);

			QueryDataSet queryDataSet = new QueryDataSet(databaseConnection);
			for (String tableName : tableNames) {
				queryDataSet.addTable(tableName);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			FlatXmlWriter datasetWriter = new FlatXmlWriter(baos);
			datasetWriter.write(queryDataSet);

			data = baos.toByteArray();

		} catch (Throwable t) {
			String errorMessage = "Error exporting data";
			log.error("exportData: " + errorMessage, t);
			throw new DataExporterException(errorMessage, t);
		} finally {
			try {
				if ((null != connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException se) {
				log.error("exportData: Error closing connection", se);
			}
		}

		return data;

	}

}
