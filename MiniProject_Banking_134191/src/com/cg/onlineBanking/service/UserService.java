package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.dao.IUserDAO;
import com.cg.onlineBanking.dao.UserDAO;
import com.cg.onlineBanking.exception.BankingException;

public class UserService implements IUserService {

	IUserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}

	//Changing Password
	@Override
	public boolean changePassword(String password, int userId) throws BankingException {
		
		return userDAO.changePassword(password, userId);
	}


	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId)
			throws BankingException {
		// TODO Auto-generated method stub
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
	public ArrayList getMiniTransactionDetails(int accountNo)
			throws BankingException {
		// TODO Auto-generated method stub
		return userDAO.getMiniTransactionDetails(accountNo);
	}

	@Override
	public ArrayList getDetailedTransactionDetails(int accountNo,
			LocalDate dt1, LocalDate dt2) throws BankingException {
		// TODO Auto-generated method stub
		return userDAO.getDetailedTransactionDetails(accountNo, dt1, dt2);
	}
		
		
		/*	
		UserBean existingUser = userDAO.validateUser(user.getUserId());
		
		if(existingUser==null){
			break;
		}else if(existingUser.getLoginPassword()!=user.getLoginPassword()){
			break;
		}
		else{
			flag=true;
		}
		return flag;
	}
*/
	}

