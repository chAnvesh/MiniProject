package com.cg.onlineBanking.service;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.dao.BankingDAO;
import com.cg.onlineBanking.dao.IBankingDAO;
import com.cg.onlineBanking.exception.BankingException;

public class BankingService implements IBankingService {

	IBankingDAO dao=null;
	final String dummyOtp="abc123";
	public BankingService() throws BankingException {
		 dao= new BankingDAO();
		
	}

	@Override
	public void close() throws Exception {
		
	}

	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		return 1;
	}

	@Override
	public void addPayee(PayeeBean payee) throws BankingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addTrans(TransactionBean trans) throws BankingException {
		return dao.addTrans(trans);
	}

	@Override
	public ArrayList<PayeeBean> showPayeeList(long accNo)
			throws BankingException {
		return dao.showPayeeList(accNo);
	}

	@Override
	public ArrayList getAccountNoList(int userId) throws BankingException {
		return dao.getAccountNoList(userId);
	}

	@Override
	public boolean validateTransPassword(int userId, String UserEnteredpasswd)
			throws BankingException {
		return dao.validateTransPassword(userId, UserEnteredpasswd);
	}

	@Override
	public boolean validateBalance(long accNo, float transactionAmount,
			int userId, long payeeAccNo) throws BankingException {
		return dao.validateBalance(accNo, transactionAmount, userId, payeeAccNo);
	}

	@Override
	public boolean validateOtp(String otp) {
		if(dummyOtp.equals(otp))
		{
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean validatePayeeAccountNo(long payeeAccNo)
			throws BankingException {
		return dao.validatePayeeAccountNo(payeeAccNo);
	}
	
	
	

}
