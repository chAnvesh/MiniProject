package com.cg.onlineBanking.service;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.dao.BankingDAO;
import com.cg.onlineBanking.dao.IBankingDAO;
import com.cg.onlineBanking.exception.BankingException;

public class BankingService implements IBankingService {

	IBankingDAO bankDAO ;
	
	public BankingService() throws BankingException {
		bankDAO= new BankingDAO();
	}

	
	@Override
	public UserBean validateUserId(int userId) throws BankingException {
		UserBean resultUser = new UserBean();
		ArrayList aList = bankDAO.getUserIdList();
		if(aList.contains(userId)){
			resultUser = bankDAO.getUserDetailsOnId(userId);
		}
		else
		{
			resultUser.setUserId(0);
		}
		return resultUser;
	}

	@Override
	public UserBean validatePassword(String password, int userId)
			throws BankingException {
		UserBean resultUser = new UserBean();
		
		resultUser = bankDAO.getUserDetailsOnId(userId);
		
		if(password.equals(resultUser.getLoginPassword())){
			return resultUser;
		}
		else{
			resultUser.setLoginPassword(null);
			return resultUser;
		}
	}
	
	
	/*@Override
	public UserBean validateCredentials(UserBean user) throws BankingException {
		UserBean resultUser = new UserBean();
		ArrayList aList=bankDAO.getUserIdList();
		
		if(aList.contains(user.getUserId())){
			
			resultUser.setUserId(user.getUserId());
			UserBean existUser=bankDAO.getUserDetailsOnId(user.getUserId());
			
			if(user.getLoginPassword().equals(existUser.getLoginPassword()) )
				{
					resultUser = existUser;
					return resultUser;
				}
				else{
					//bankDAO.upadteLockStatus(user); 
					return resultUser;
				}
		}
		else
		{
			//resultUser.setLockStatus("Y");
			return resultUser;
		}
		}*/
		
	

	
	
	@Override
	public boolean validateUser(int userId) throws BankingException {
		
		ArrayList aList=bankDAO.getUserIdList();
		
		if(aList.contains(userId)){
			return true;
		}
		else
		{
		return false;
	}
	}
	@Override
	public boolean forgotPassword(int userId, String secretQstn)
			throws BankingException {
		
		return bankDAO.forgotPassword(userId, secretQstn);
	}

	@Override
	public boolean lockAccount(int userId) throws BankingException {
		// TODO Auto-generated method stub
		return bankDAO.upadteLockStatus(userId);
	}

	

	

}

