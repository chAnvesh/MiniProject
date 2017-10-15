package com.cg.onlineBanking.service;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingService {
	
	//Related to UI alone.
	public UserBean validateCredentials(UserBean user) throws BankingException;
	//public ArrayList validateUserPassword() throws BankingException ;
	boolean forgotPassword(int userId, String secretQstn) throws BankingException;
	public boolean validateUser(int userId) throws BankingException;
	
	public boolean lockAccount(int userId) throws BankingException;

}
