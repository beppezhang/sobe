package com.sdt.commons;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;

public class TimezoneEnabledDataSource extends BasicDataSource {
	
	private String timezone;
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection con = super.getConnection();
		con.createStatement().execute("SET time_zone = \'" + timezone+"\'");
		return con;
	}

}
