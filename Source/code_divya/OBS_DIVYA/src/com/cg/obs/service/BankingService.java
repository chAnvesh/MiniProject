package com.cg.obs.service;

import java.util.ArrayList;

import com.cg.obs.dao.BankingDAO;
import com.cg.obs.dao.IBankingDAO;
import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public class BankingService implements IBankingService {

	IBankingDAO bankDAO ;
	
	public BankingService() throws BankingException {
		bankDAO= new BankingDAO();
	}

	@Override
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
		}
		
	

	
	
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

