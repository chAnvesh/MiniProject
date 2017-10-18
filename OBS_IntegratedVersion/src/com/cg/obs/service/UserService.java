package com.cg.obs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.obs.dao.IUserDAO;
import com.cg.obs.dao.UserDAO;
import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.FundTransferBean;
import com.cg.obs.entities.PayeeBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;

@Service
@Transactional
public class UserService implements IUserService {

	@Resource
	IUserDAO userDAO;
	
	public UserService() throws BankingException {
		userDAO = new UserDAO();
	}

	//Changing Password
		@Override
		public boolean changePassword(int userId, String oldPassword, String newPassword) throws BankingException {
			
			if(userDAO.validatePassword(userId, oldPassword)){
				userDAO.updatePassword(userId, newPassword);
				return true;
			}else{
				return false;
			}
			
			
		}
	
	
	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId)
			throws BankingException {
		
		return userDAO.getAccountDetails(userId);
	}

	@Override
	public void resetLockStatus(int userId) throws BankingException {
		
		userDAO.resetLockStatus(userId);
	}

	@Override
	public List<TransactionBean> getMiniTransactionDetails(long accNo)
			throws BankingException {
		return userDAO.getMiniTransactionDetails(accNo);
	}

	@Override
	public List<TransactionBean> getDetailedTransactionDetails(long accNo,
			Date startDate, Date endDate) throws BankingException {
		return userDAO.getDetailedTransactionDetails(accNo, startDate, endDate);
	}

	@Override
	public String getAddress(int userId) throws BankingException {
		return userDAO.getAddress(userId);
	}

	@Override
	public int updateAddress(int userId, String address)
			throws BankingException {
		return userDAO.updateAddress(userId, address);
	}
	
	@Override
	public void raiseRequest(ServiceTrackerBean request) throws BankingException {
		userDAO.raiseRequest(request);
	}

	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		return userDAO.trackRequest(requestId, accNo);
	}

	@Override
	public List<Long> getAccNoList(int userId) throws BankingException {
		
		return userDAO.getAccNoList(userId);
	}

	

	@Override
	public void addPayee(PayeeBean payee) throws BankingException {
		
		 userDAO.addPayee(payee);
	}

	@Override
	public int addTransForCredit(TransactionBean trans,long payeeAccNo) throws BankingException {
		return userDAO.addTransForCredit(trans,payeeAccNo);
	}

	@Override
	public int addTransForDebit(TransactionBean trans) throws BankingException {
		// TODO Auto-generated method stub
		return userDAO.addTransForDebit(trans);
	}
	
	@Override
	public List<PayeeBean> showPayeeList(long accNo)
			throws BankingException {
		return userDAO.showPayeeList(accNo);
	}

	@Override
	public List getAccountNoList(int userId) throws BankingException{
		return userDAO.getAccountNoList(userId);
	}

	@Override
	public boolean validateTransPassword(int userId, String UserEnteredpasswd)
			throws BankingException {
		return userDAO.validateTransPassword(userId, UserEnteredpasswd);
	}

	@Override
	public boolean validateBalance(long accNo, long transactionAmount,
			int userId, long payeeAccNo) throws BankingException {
		return userDAO.validateBalance(accNo, transactionAmount, userId, payeeAccNo);
	}

	

	@Override
	public boolean validatePayeeAccountNo(long payeeAccNo)
			throws BankingException {
		return userDAO.validatePayeeAccountNo(payeeAccNo);
	}

	@Override
	public void addFundTransfer(FundTransferBean fundTrans)
			throws BankingException {
		userDAO.addFundTransfer(fundTrans);
		
	}

	@Override
	public boolean validatePayeeSelf(long payeeAccNo, long accNo)
			throws BankingException {
		return userDAO.validatePayeeSelf(payeeAccNo, accNo);
	}

	
}

