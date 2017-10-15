package com.cg.obs.dao;

import java.util.ArrayList;

import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingDAO {
		
		/*//public UserBean validateUser(int userId) throws BankingException;
		public ArrayList getUserIdList()  throws BankingException;
		//public ArrayList validateUserPassword()  throws BankingException;
		public UserBean getUserDetailsOnId(int userId) throws BankingException;
		boolean forgotPassword(int userId, String secretQstn) throws BankingException;
		public boolean upadteLockStatus(int userId) throws BankingException;
		
		public boolean lockAccount(int userId) throws BankingException;/*/
	
	
		public ArrayList<Integer> getUserIdList() throws BankingException;
		public UserBean getUserDetailsOnId(int userId) throws BankingException;
	
		public void forgotPassword(int userId) throws BankingException;
 }
