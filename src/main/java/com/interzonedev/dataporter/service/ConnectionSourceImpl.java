package com.interzonedev.dataporter.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Named("connectionSource")
public class ConnectionSourceImpl implements ConnectionSource {

	@Override
	public Connection getConnection(DataSourceProperties dataSourceProperties) throws SQLException {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(dataSourceProperties.getUrl(),
				dataSourceProperties.getUsername(), dataSourceProperties.getPassword());

		return driverManagerDataSource.getConnection();

	}

}
