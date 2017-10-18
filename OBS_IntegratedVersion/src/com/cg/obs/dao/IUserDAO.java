package com.cg.obs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.FundTransferBean;
import com.cg.obs.entities.PayeeBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
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
	
	
	//misc - to get account details
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	public void resetLockStatus(int userId) throws BankingException;
	
	//Mini/Detailed trans
	public List<TransactionBean> getMiniTransactionDetails(long accountNo) throws BankingException;
	public List<TransactionBean> getDetailedTransactionDetails(long accountNo,Date dt1, Date dt2) throws BankingException;
	
	//comm details
	public String getAddress(int userId);
	public int updateAddress(int userId, String address) throws BankingException;
	
	//Service Request methods
	public void raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException;
	public List<Long> getAccNoList(int userId) throws BankingException; 
	
	//fund transfer
			public void addPayee(PayeeBean payee) throws BankingException;
			public int addTransForCredit(TransactionBean trans,long payeeaccNo) throws BankingException;
			public int addTransForDebit(TransactionBean trans) throws BankingException;
			public List<PayeeBean> showPayeeList(long accNo) throws BankingException;
			public List getAccountNoList(int userId) throws BankingException;
			public boolean validateTransPassword(int userId,String UserEnteredpasswd)  throws BankingException;
			public boolean validateBalance(long accNo,long transactionAmount,int userId,long payeeAccNo) throws BankingException;
			public boolean validatePayeeAccountNo(long payeeAccNo) throws BankingException;
			public void addFundTransfer(FundTransferBean fundTrans) throws BankingException;
		    public boolean validatePayeeSelf(long payeeAccNo,long accNo) throws BankingException;

}
