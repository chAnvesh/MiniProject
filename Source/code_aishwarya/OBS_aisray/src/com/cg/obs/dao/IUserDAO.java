package com.cg.obs.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.obs.bean.AccountMasterBean;
import com.cg.obs.bean.PayeeBean;
import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.bean.TransactionBean;
import com.cg.obs.bean.UserBean;
import com.cg.obs.exception.BankingException;

public interface IUserDAO {

	/*change password
	 * 
	 * 1.validate oldPassword
	 * 2.update table user_table with newPassword
	 * 
	 * */
	
	public boolean validatePassword(int userId, String oldPassword) throws BankingException;
	public void updatePassword(int userId, String newPassword) throws BankingException;
	
	//Raising and tracking requests
	public void raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException; 
	
	
	//miscellaneous-userconsole
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	//Mini and detailed statements
	public ArrayList getMiniTransactionDetails(long accountNo) throws BankingException;
	public ArrayList getDetailedTransactionDetails(long accountNo,LocalDate dt1, LocalDate dt2) throws BankingException;
	
	//Update Address
	public int updateAddress(long accId, String address) throws BankingException;
	
	
	//fund transfer
		public void addPayee(PayeeBean payee) throws BankingException;
		public int addTrans(TransactionBean trans,long payeeaccNo) throws BankingException;
		public ArrayList<PayeeBean> showPayeeList(long accNo) throws BankingException;
		public ArrayList getAccountNoList(int userId) throws BankingException;
		public boolean validateTransPassword(int userId,String UserEnteredpasswd)  throws BankingException;
		public boolean validateBalance(long accNo,float transactionAmount,int userId,long payeeAccNo) throws BankingException;
		public boolean validatePayeeAccountNo(long payeeAccNo) throws BankingException;

		public ArrayList<Long> getPayeeDetails(long userEnterPayee) throws BankingException;
	
}
