package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
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
		public List<TransactionBean> getMiniTransactionDetails(long accNo) throws BankingException;
		public List<TransactionBean> getDetailedTransactionDetails(long accNo,Date startDate, Date endDate) throws BankingException;
		
		//Update Address
		public int updateAddress(int userId, String address) throws BankingException;
		
		public void addPayee(PayeeBean payee) throws BankingException;
		public int addTrans(TransactionBean trans,long payeeaccNo) throws BankingException;
		public ArrayList<PayeeBean> showPayeeList(long accNo) throws BankingException;
		public ArrayList getAccountNoList(int userId) throws BankingException;
		public boolean validateTransPassword(int userId,String UserEnteredpasswd)  throws BankingException;
		public boolean validateBalance(long accNo,float transactionAmount,int userId,long payeeAccNo) throws BankingException;
		public boolean validatePayeeAccountNo(long payeeAccNo) throws BankingException;

		public ArrayList<Long> getPayeeDetails(long userEnterPayee) throws BankingException;

		public String getAddress(int i);
	
}
