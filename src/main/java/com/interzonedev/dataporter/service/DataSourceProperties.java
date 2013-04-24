package com.interzonedev.dataporter.service;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DataSourceProperties {

	private final String driverClassName;

	private final String url;

	private final String username;

	private final String password;

	public DataSourceProperties(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getDriverClassName()).append(getUrl()).append(getUsername())
				.append(getPassword()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DataSourceProperties)) {
			return false;
		}

		DataSourceProperties that = (DataSourceProperties) obj;

		boolean equals = new EqualsBuilder().append(getDriverClassName(), that.getDriverClassName())
				.append(getUrl(), that.getUrl()).append(getUsername(), that.getUsername())
				.append(getPassword(), that.getPassword()).isEquals();

		return equals;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("driverClassName", getDriverClassName()).append("url", getUrl())
				.append("username", getUsername()).append("password", getPassword()).toString();
	}

}
