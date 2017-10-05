package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.dao.AdminDAO;
import com.cg.onlineBanking.dao.IAdminDAO;
import com.cg.onlineBanking.exception.BankingException;

public class AdminService implements IAdminService {

	IAdminDAO adminDAO;
	
	public AdminService() {
		adminDAO = new AdminDAO();
	}
	
	@Override
	public long addCustomer(NewUserBean newUser) throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*@Override
	public ArrayList<TransactionBean> viewTransactions()
			throws BankingException {
		return adminDAO.viewTransactions();
	}*/

	@Override
	public boolean validateDate(LocalDate transDate) throws BankingException {
		
		if(transDate.compareTo(LocalDate.now())>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean validateMonth(String month) throws BankingException {
		
		return month.matches("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)");
	}
	
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(LocalDate date)
			throws BankingException {
		
		return adminDAO.viewDailyTransactions(date);
	}

	@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,
			int year) throws BankingException {
		// TODO Auto-generated method stub
		return adminDAO.viewMonthlyTransactions(month,year);
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(int year)
			throws BankingException {
		// TODO Auto-generated method stub
		return adminDAO.viewYearlyTransactions(year);
	}

}
