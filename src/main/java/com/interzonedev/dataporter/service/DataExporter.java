package com.interzonedev.dataporter.service;

import java.util.List;

public interface DataExporter {

	public byte[] export(DataSourceProperties dataSourceProperties, List<String> tableNames) throws DataExportException;

	public byte[] export(DataSourceProperties dataSourceProperties) throws DataExportException;

}
