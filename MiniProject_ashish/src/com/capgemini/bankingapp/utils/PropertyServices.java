package com.capgemini.bankingapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import com.capgemini.bankingapp.exception.CustomException;



public class PropertyServices {
	private Properties props;
	public PropertyServices() throws CustomException {
		props = new Properties();
		
		
		try(FileInputStream fls=new FileInputStream("ProjectDb.properties");){
			props.load(fls);
		}
		catch(IOException e1){
			throw new CustomException("Property file missing",e1);
		}
	}
	public String getPropValue(String prop){
		return props.getProperty(prop);
	}
	
	public Set<Object> getPropKeys(){
		
		Set<Object> keys = (Set<Object>)props.keySet();
		return keys;
	}
}
