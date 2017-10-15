package com.cg.obs.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.PayeeBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IUserService {

	//Miscellaneous
	
	/*public boolean changePassword(String password, int userId) throws BankingException;
	
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	//Service Request methods
	public void raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException;

	//Mini and detailed statements
		public ArrayList getMiniTransactionDetails(long accNo) throws BankingException;
		public ArrayList getDetailedTransactionDetails(long accNo,LocalDate dt1, LocalDate dt2) throws BankingException;
		
		//Update Address
		public int updateAddress(long accNo, String address) throws BankingException;
		
		public void addPayee(PayeeBean payee) throws BankingException;
		public int addTrans(TransactionBean trans,long payeeaccNo) throws BankingException;
		public ArrayList<PayeeBean> showPayeeList(long accNo) throws BankingException;
		public ArrayList getAccountNoList(int userId) throws BankingException;
		public boolean validateTransPassword(int userId,String UserEnteredpasswd)  throws BankingException;
		public boolean validateBalance(long accNo,float transactionAmount,int userId,long payeeAccNo) throws BankingException;
		public boolean validatePayeeAccountNo(long payeeAccNo) throws BankingException;

		public ArrayList<Long> getPayeeDetails(long userEnterPayee) throws BankingException;
	*/
}
