package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.FundTransferBean;
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

	//Changing Password
	/*@Override
	public boolean changePassword(String password, int userId) throws BankingException {
		
		return userDAO.changePassword(password, userId);
	}


	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId)
			throws BankingException {
		
		return userDAO.getAccountDetails(userId);
	}

	//Raising requests and Tracking
	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		return userDAO.raiseRequest(request);
	}

	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		return userDAO.trackRequest(requestId, accNo);
	}

	@Override
	public ArrayList getMiniTransactionDetails(long accountNo)
			throws BankingException {
		
		return userDAO.getMiniTransactionDetails(accountNo);
	}

	@Override
	public ArrayList getDetailedTransactionDetails(long accountNo,
			LocalDate dt1, LocalDate dt2) throws BankingException {
		
		return userDAO.getDetailedTransactionDetails(accountNo, dt1, dt2);
	}

	@Override
	public int updateAddress(long accId, String address) throws BankingException {
		
		return userDAO.updateAddress(accId, address);
	}
		
	*/
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

	

	/*@Override
	public ArrayList<Long> getPayeeDetails(long userEnterPayee)
			throws BankingException {
		
		return userDAO.getPayeeDetails(userEnterPayee);
	}*/


		
	}

