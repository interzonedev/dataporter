package com.interzonedev.dataporter.service.importer;

import com.interzonedev.dataporter.service.DataSourceProperties;

public interface DataImporter {

	public void importData(DataSourceProperties dataSourceProperties, byte[] dataSetContents)
			throws DataImporterException;

}
