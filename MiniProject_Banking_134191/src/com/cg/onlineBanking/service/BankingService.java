package com.cg.onlineBanking.service;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.dao.BankingDAO;
import com.cg.onlineBanking.dao.IBankingDAO;
import com.cg.onlineBanking.exception.BankingException;

public class BankingService implements IBankingService {

	IBankingDAO dao ;
	
	public BankingService() throws BankingException {
		dao = new BankingDAO();
	}

	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {

		return dao.raiseRequest(request);
	}

	@Override
	public ServiceTrackerBean trackRequest(int requestId, long accNo)
			throws BankingException {
		
		return dao.trackRequest(requestId, accNo);
	}

}

