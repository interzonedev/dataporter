package com.interzonedev.dataporter.service;

import java.util.List;

import javax.inject.Named;

@Named("dataExporter")
public class DbUnitDataExporter implements DataExporter {

	@Override
	public byte[] export(DataSourceProperties dataSourceProperties, List<String> tableNames) {

		byte[] data = "Placeholder".getBytes();

		return data;

	}

	@Override
	public byte[] export(DataSourceProperties dataSourceProperties) {

		return export(dataSourceProperties, null);

	}

}
