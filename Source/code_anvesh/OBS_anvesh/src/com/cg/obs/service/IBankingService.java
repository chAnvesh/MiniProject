package com.cg.obs.service;



import java.util.ArrayList;

import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingService {
	
	//Related to UI alone.
	public boolean validateUserId(int userId) throws BankingException;
	

	
	public boolean validateSecretQuestion(int userId, String secretQuestion) throws BankingException;
	
	public boolean validateSecretAns(int userId, String secretAns) throws BankingException;
	
	public void forgotPassword(int userId) throws BankingException;
	
	public UserBean validatePassword(String password, int userId) throws BankingException;
	
	//public ArrayList<Integer> getUserIdList() throws BankingException;
}
