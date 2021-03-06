package com.interzonedev.dataporter.web.exporter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.interzonedev.dataporter.web.ImporterExporterForm;

public class ExporterForm extends ImporterExporterForm {

    @NotEmpty
    private String tableNames = "taxonomy,class,entity";

    private String exportFilename;

    private String query;

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getExportFilename() {
        return exportFilename;
    }

    public void setExportFilename(String exportFilename) {
        this.exportFilename = exportFilename;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(super.toString()).append("tableNames", getTableNames())
                .append("exportFilename", getExportFilename()).append("query", getQuery()).toString();
    }

}
