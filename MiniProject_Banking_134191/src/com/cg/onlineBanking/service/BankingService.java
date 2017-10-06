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
	public UserBean validateCredentials(UserBean user) throws BankingException {
		UserBean resultUser = new UserBean();
		ArrayList aList=bankDAO.getUserIdList();
		
		if(aList.contains(user.getUserId())){
			
			resultUser.setUserId(user.getUserId());
			UserBean existUser=bankDAO.getUserDetailsOnId(user.getUserId());
			
			if(user.getLoginPassword().equals(existUser.getLoginPassword()))
				{
					resultUser = existUser;
					return resultUser;
				}
				else{
					return resultUser;
				}
		}
		else
		{
			return resultUser;
		}
	}

	@Override
	public ArrayList validateUserPassword() throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean forgotPassword(int userId, String secretQstn)
			throws BankingException {
		// TODO Auto-generated method stub
		return bankDAO.forgotPassword(userId, secretQstn);
	}

	

}

