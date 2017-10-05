package com.cg.onlineBanking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.onlineBanking.exception.BankingException;

public class ConnectionUtil {

	private Connection conn;
	
	public ConnectionUtil() throws BankingException {
		PropertyService propServ = new PropertyService();
		String url=propServ.getPropValue("url");
		String username = propServ.getPropValue("username");
		String password = propServ.getPropValue("password");
		
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			throw new BankingException("Connection opening failed",e);
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
