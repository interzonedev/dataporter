package com.interzonedev.dataporter.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSource {

	public Connection getConnection(DataSourceProperties dataSourceProperties) throws SQLException;

}
