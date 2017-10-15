package com.cg.obs.dao;

import java.util.ArrayList;
import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.exception.BankingException;

public interface IUserDAO {


	/*change password
	 * 
	 * 1.validate oldPassword
	 * 2.update table user_table with newPassword
	 * 
	 * */
	
	public boolean validatePassword(int userId, String oldPassword) throws BankingException;
	public void updatePassword(int userId, String newPassword) throws BankingException;
	
	
	//misc - to get account details
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	public void resetLockStatus(int userId) throws BankingException;
	
}
