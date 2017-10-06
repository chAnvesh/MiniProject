package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IUserService {

	//Miscellaneous
	
	public boolean changePassword(String password, int userId) throws BankingException;
	
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	//Service Request methods
	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException;

	//Mini and detailed statements
		public ArrayList getMiniTransactionDetails(int accountNo) throws BankingException;
		public ArrayList getDetailedTransactionDetails(int accountNo,LocalDate dt1, LocalDate dt2) throws BankingException;
	
}
