package com.cg.obs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;




import com.cg.obs.dao.IAdminDAO;
import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Resource
	private IAdminDAO adminDAO;
	
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(Date date)
			throws BankingException {
		
		return adminDAO.viewDailyTransactions(date);
	}

	@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,
			String year) throws BankingException {
		// TODO Auto-generated method stub
		return adminDAO.viewMonthlyTransactions(month,year);
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(String year)
			throws BankingException {
		// TODO Auto-generated method stub
		return adminDAO.viewYearlyTransactions(year);
	}

	@Override
	public void addAccount(AccountMasterBean bean) throws BankingException {
		adminDAO.addAccount(bean);
		
	}


}
