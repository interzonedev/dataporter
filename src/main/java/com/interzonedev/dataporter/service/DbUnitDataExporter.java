package com.interzonedev.dataporter.service;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

@Named("dataExporter")
public class DbUnitDataExporter implements DataExporter {

	private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Inject
	@Named("connectionSource")
	private ConnectionSource connectionSource;

	@Override
	public byte[] export(DataSourceProperties dataSourceProperties, List<String> tableNames) throws DataExportException {

		byte[] data = null;

		try {

			Connection connection = connectionSource.getConnection(dataSourceProperties);

			IDatabaseConnection databaseConnection = new DatabaseConnection(connection);

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
			log.error("export: " + errorMessage, t);
			throw new DataExportException(errorMessage, t);
		}

		return data;

	}

	@Override
	public byte[] export(DataSourceProperties dataSourceProperties) throws DataExportException {

		return export(dataSourceProperties, null);

	}

}
