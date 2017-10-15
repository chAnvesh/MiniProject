package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.dao.IUserDAO;
import com.cg.onlineBanking.dao.UserDAO;
import com.cg.onlineBanking.exception.BankingException;
@Service
@Transactional
public class UserService implements IUserService {

	@Resource
	IUserDAO userDAO;
	
	public UserService() throws BankingException {
		userDAO = new UserDAO();
	}

	


	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId)
			throws BankingException {
		
		return userDAO.getAccountDetails(userId);
	}

	
	@Override
	public List<TransactionBean> getMiniTransactionDetails(long accountNo)
			throws BankingException {
		
		return userDAO.getMiniTransactionDetails(accountNo);
	}

	@Override
	public List<TransactionBean> getDetailedTransactionDetails(long accountNo,
			Date dt1, Date dt2) throws BankingException {
		
		return userDAO.getDetailedTransactionDetails(accountNo, dt1, dt2);
	}

	@Override
	public int updateAddress(int userId, String address) throws BankingException {
		
		return userDAO.updateAddress(userId, address);
	}
		
	
	
	@Override
	public ArrayList getAccountNoList(int userId) throws BankingException {
		return userDAO.getAccountNoList(userId);
	}




	@Override
	public boolean changePassword(String password, int userId)
			throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void addPayee(PayeeBean payee) throws BankingException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public int addTrans(TransactionBean trans, long payeeaccNo)
			throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public ArrayList<PayeeBean> showPayeeList(long accNo)
			throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean validateTransPassword(int userId, String UserEnteredpasswd)
			throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean validateBalance(long accNo, float transactionAmount,
			int userId, long payeeAccNo) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean validatePayeeAccountNo(long payeeAccNo)
			throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public ArrayList<Long> getPayeeDetails(long userEnterPayee)
			throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String getAddress(int i) {
		return userDAO.getAddress(i);
	}
	
		
		
	}

