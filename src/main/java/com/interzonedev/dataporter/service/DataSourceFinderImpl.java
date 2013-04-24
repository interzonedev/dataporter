package com.interzonedev.dataporter.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.sql.DataSource;

@Named("dataSourceFinder")
public class DataSourceFinderImpl implements DataSourceFinder {

	@Override
	public List<DataSource> getDataSourcesInContext() {

		List<DataSource> dataSourcesInContext = new ArrayList<DataSource>();

		return dataSourcesInContext;

	}

}
