package com.cg.onlineBanking.dao;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingDAO {

	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ServiceTrackerBean trackRequest(int requestId,long accNo) throws BankingException; 
	
}
