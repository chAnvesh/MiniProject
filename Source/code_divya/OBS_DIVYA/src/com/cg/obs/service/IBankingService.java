package com.cg.obs.service;

import java.util.ArrayList;

import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingService {
	
	//Related to UI alone.
	public UserBean validateCredentials(UserBean user) throws BankingException;
	//public ArrayList validateUserPassword() throws BankingException ;
	boolean forgotPassword(int userId, String secretQstn) throws BankingException;
	public boolean validateUser(int userId) throws BankingException;
	
	public boolean lockAccount(int userId) throws BankingException;

}
