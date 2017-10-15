package com.cg.obs.service;

import java.util.ArrayList;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.exception.BankingException;

public interface IUserService {

	//Miscellaneous
	
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;

	public void resetLockStatus(int userId) throws BankingException;
	
	public boolean changePassword(int userId, String oldPassword, String newPassword) throws BankingException;
}
