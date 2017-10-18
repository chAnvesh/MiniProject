package com.cg.obs.service;

import java.util.List;

import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingService {
	
	public void setInitLockStatus() throws BankingException;
	
	//User Validation
	public boolean validateUserId(int userId) throws BankingException;
	public UserBean validatePassword(String password, int userId) throws BankingException;
	
	//Forgot Password
	public boolean validateSecretQuestion(int userId, String secretQuestion) throws BankingException;
	public boolean validateSecretAns(int userId, String secretAns) throws BankingException;
	public void forgotPassword(int userId) throws BankingException;
	
	public CustomerBean getCustDetails(int userId) throws BankingException;
	
	public List<Integer> getUserIdList() throws BankingException;
}
