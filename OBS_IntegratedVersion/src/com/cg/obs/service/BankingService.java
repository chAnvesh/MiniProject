package com.cg.obs.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.obs.dao.IBankingDAO;
import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;


@Service
@Transactional
public class BankingService implements IBankingService {

	@Resource
	IBankingDAO bankDAO ;
	

	@Override
	public void setInitLockStatus() throws BankingException {
		bankDAO.setInitLockStatus();
		
	}


	@Override
	public boolean validateUserId(int userId) throws BankingException {
		ArrayList<Integer> aList = bankDAO.getUserIdList();
		if(aList.contains(userId)){
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public UserBean validatePassword(String password, int userId)
			throws BankingException {
		UserBean newUser = new UserBean();
		UserBean resultUser = bankDAO.getUserDetailsOnId(userId);
		
		if(password.equals(resultUser.getLoginPassword()) && resultUser.getLockStatus()<3){
			newUser = resultUser;
			return newUser;
		}
		else{
			resultUser.setLockStatus(resultUser.getLockStatus()+1);
			newUser.setLockStatus(resultUser.getLockStatus());
			newUser.setLoginPassword("wrong");
			return newUser;
		}
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
		if(secretAns.equals(user.getSecretAns()))
			return true;
		else
			return false;
	}


	@Override
	public void forgotPassword(int userId) throws BankingException {
		bankDAO.forgotPassword(userId);
	}


	@Override
	public CustomerBean getCustDetails(int userId) throws BankingException {
		
		return bankDAO.getCustDetails(userId);
	}


	@Override
	public List<Integer> getUserIdList() throws BankingException {
		
		return bankDAO.getUserIdList();
	}
	
	
}

