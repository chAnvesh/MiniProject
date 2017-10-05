package com.cg.onlineBanking.dao;

import java.util.ArrayList;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingDAO {

	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo) throws BankingException; 
	public boolean changePassword(String password, int userId) throws BankingException;
}
