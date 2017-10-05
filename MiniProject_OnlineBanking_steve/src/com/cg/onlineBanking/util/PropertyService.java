package com.cg.onlineBanking.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.cg.onlineBanking.exception.BankingException;



public class PropertyService {
private Properties props;
	
	public PropertyService() throws BankingException {
		props = new Properties();
		
		try(
			FileInputStream fis = new FileInputStream("BankingDB.properties"); 
			) {
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			throw new BankingException("Property file missing...",e);
		}
	}
	public String getPropValue(String key) {
		return props.getProperty(key);
	}
	
}
