package com.cg.obs.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.bean.UserBean;
import com.cg.obs.exception.BankingException;

public interface IBankingDAO {
		
		public List getUserIdList()  throws BankingException;
	    
		public UserBean getUserDetailsOnId(int userId) throws BankingException;
		
		public void forgotPassword(int userId) throws BankingException;
		
		
		public boolean upadteLockStatus(int userId) throws BankingException;
		

}
