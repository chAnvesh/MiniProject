package com.cg.obs.dao;

import java.util.ArrayList;

import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingDAO {
		
	public void setInitLockStatus() throws BankingException;
	
	public ArrayList<Integer> getUserIdList() throws BankingException;
	public UserBean getUserDetailsOnId(int userId) throws BankingException;
	public void forgotPassword(int userId) throws BankingException;
	
	public CustomerBean getCustDetails(int userId) throws BankingException;
 }
