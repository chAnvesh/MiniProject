package com.cg.onlineBanking.service;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingService extends AutoCloseable{
	
	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public void addPayee(PayeeBean payee) throws BankingException;
	public int addTrans(TransactionBean trans) throws BankingException;
	public ArrayList<PayeeBean> showPayeeList(long accNo) throws BankingException;
	public ArrayList getAccountNoList(int userId) throws BankingException;
	public boolean validateTransPassword(int userId,String UserEnteredpasswd)  throws BankingException;
	public boolean validateBalance(long accNo,float transactionAmount,int userId,long payeeAccNo) throws BankingException;
	public boolean validateOtp(String otp);
	public boolean validatePayeeAccountNo(long payeeAccNo) throws BankingException;

}
