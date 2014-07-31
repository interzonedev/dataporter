package com.interzonedev.dataporter.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Named;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Logger;

@Named("connectionSource")
public class ConnectionSourceImpl implements ConnectionSource {

    private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    @Override
    public Connection getConnection(DataSourceProperties dataSourceProperties) throws SQLException {

        Assert.notNull(dataSourceProperties, "getConnection: The data source properties must be set");

        log.debug("getConnection: Getting connection for - " + dataSourceProperties);

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(), dataSourceProperties.getPassword());

        Connection connection = driverManagerDataSource.getConnection();

        log.debug("getConnection: Got connection - " + connection);

        return connection;

    }

}
