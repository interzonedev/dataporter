package com.interzonedev.dataporter.web;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

public class ImporterExporterForm {

	@NotEmpty
	private String driverClassName = "com.mysql.jdbc.Driver";

	@NotEmpty
	private String url = "jdbc:mysql://localhost/cms_taxonomy";

	@NotEmpty
	private String username = "rluser";

	@NotEmpty
	private String password = "rluser";

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("driverClassName", getDriverClassName()).append("url", getUrl())
				.append("username", getUsername()).append("password", getPassword()).toString();
	}

}
