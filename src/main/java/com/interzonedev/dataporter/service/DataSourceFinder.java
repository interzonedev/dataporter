package com.interzonedev.dataporter.service;

import java.util.List;

import javax.sql.DataSource;

public interface DataSourceFinder {

	public List<DataSource> getDataSourcesInContext();

}
