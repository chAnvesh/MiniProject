package com.cg.obs.service;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.obs.dao.IUserDAO;
import com.cg.obs.dao.UserDAO;
import com.cg.obs.entities.AccountMasterBean;
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
	
	}

