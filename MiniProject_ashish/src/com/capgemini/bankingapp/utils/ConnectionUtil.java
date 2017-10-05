package com.capgemini.bankingapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.capgemini.bankingapp.exception.CustomException;


public class ConnectionUtil {
	private Connection connect;
	public ConnectionUtil() throws CustomException{
		PropertyServices propServices = new PropertyServices();
		String url = propServices.getPropValue("url");
		String userName = propServices.getPropValue("userName");
		String password = propServices.getPropValue("password");
		
		
		try {
			connect = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			throw new CustomException("Connection Opening Failed",e);
		}
	
	}
	public Connection getConnection(){
		return connect;
	}
}

