package com.cg.onlineBanking.service;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IBankingService {

	public int raiseRequest(ServiceTrackerBean request) throws BankingException;
	public ServiceTrackerBean trackRequest(int requestId,long accNo) throws BankingException;


}
