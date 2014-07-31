package com.interzonedev.dataporter.service.exporter;

import java.util.List;

import com.interzonedev.dataporter.service.DataSourceProperties;

public interface DataExporter {

    public byte[] exportData(DataSourceProperties dataSourceProperties, List<String> tableNames, String query)
            throws DataExporterException;

}
