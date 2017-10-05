package com.cg.onlineBanking.service;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IUserService {

	//Miscellaneous
	
	//Validation of Credentials
	public UserBean validateCredentials(UserBean user) throws BankingException;
	public ArrayList validateUserPassword() throws BankingException ;
	
	public boolean changePassword(String password, int userId) throws BankingException;
	
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	//Service Request methods
	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException;

}
