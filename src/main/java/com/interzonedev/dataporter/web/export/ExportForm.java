package com.interzonedev.dataporter.web.export;

import org.hibernate.validator.constraints.NotEmpty;

public class ExportForm {

	@NotEmpty
	private String tableNames;

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

}
