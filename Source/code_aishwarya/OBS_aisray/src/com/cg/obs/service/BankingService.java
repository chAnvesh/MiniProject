package com.cg.obs.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.cg.obs.bean.AccountMasterBean;
import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.bean.UserBean;
import com.cg.obs.dao.BankingDAO;
import com.cg.obs.dao.IBankingDAO;
import com.cg.obs.dao.IUserDAO;
import com.cg.obs.exception.BankingException;

public class BankingService implements IBankingService {

	@Resource
	IBankingDAO bankDAO ;
	
	public BankingService() throws BankingException {
		bankDAO= new BankingDAO();
	}

	
	@Override
	public boolean validateUserId(int userId) throws BankingException {
		
		if(bankDAO.getUserIdList().contains(userId))
			return true;
		else
			return false;
		
	}
	
	@Override
	public boolean validateSecretQuestion(int userId, String secretQuestion)
			throws BankingException {
		UserBean user = bankDAO.getUserDetailsOnId(userId);
		if(secretQuestion.equals(user.getSecretQstn()))
			return true;
		else
			return false;
	}


	@Override
	public boolean validateSecretAns(int userId, String secretAns)
			throws BankingException {
		
		UserBean user = bankDAO.getUserDetailsOnId(userId);
		if(secretAns.equals(user.getSecrestAns()))
			return true;
		else
			return false;
	}
	
	@Override
	public void forgotPassword(int userId) throws BankingException {
		bankDAO.forgotPassword(userId);
	
				
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
		
		//ArrayList aList=bankDAO.getUserIdList();
		
		//if(aList.contains(userId)){
			return true;
		//}
		//else
		//{
		//return false;
	//}
	}
	

	@Override
	public boolean lockAccount(int userId) throws BankingException {
		// TODO Auto-generated method stub
		return bankDAO.upadteLockStatus(userId);
	}


	


	

	

	

}

