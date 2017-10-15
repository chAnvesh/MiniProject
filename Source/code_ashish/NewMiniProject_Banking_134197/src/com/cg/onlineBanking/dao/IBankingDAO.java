package com.cg.onlineBanking.dao;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingDAO {
		
		//public UserBean validateUser(int userId) throws BankingException;
		public ArrayList getUserIdList()  throws BankingException;
		//public ArrayList validateUserPassword()  throws BankingException;
		public UserBean getUserDetailsOnId(int userId) throws BankingException;
		boolean forgotPassword(int userId, String secretQstn) throws BankingException;
		public boolean upadteLockStatus(int userId) throws BankingException;
		
//		public boolean lockAccount(int userId) throws BankingException;
}