package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;

import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.dao.AdminDAO;
import com.cg.onlineBanking.dao.IAdminDAO;
import com.cg.onlineBanking.exception.BankingException;

public class AdminService implements IAdminService {

	IAdminDAO adminDAO;
	
	public AdminService() throws BankingException {
		adminDAO = new AdminDAO();
	}
	
	/*@Override
	public long addCustomer(NewUserBean newUser,CustomerBean cust) throws BankingException {
		
		AccountMasterBean acc = new AccountMasterBean(newUser.getAccountType(), newUser.getOpeningBalance());
		
		long accNo = adminDAO.updateAccountMaster(acc);
		adminDAO.updateCustomerTable(accNo,cust);
		//System.out.println(newUser.getUserId());
		//System.out.println(adminDAO.getUserDetails(newUser.getUserId()));
		adminDAO.updateUsertable(adminDAO.getUserDetails(newUser.getUserId()),accNo);
		
		return accNo;
	}*/

	

	@Override
	public boolean validateDate(LocalDate transDate) throws BankingException {
		
		if(transDate.compareTo(LocalDate.now())<=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean validateMonth(String month) throws BankingException {
		
		return month.matches("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)");
	}
	
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(LocalDate date)
			throws BankingException {
		
		return adminDAO.viewDailyTransactions(date);
	}

	@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,
			int year) throws BankingException {
		
		return adminDAO.viewMonthlyTransactions(month,year);
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(int year)
			throws BankingException {
		
		return adminDAO.viewYearlyTransactions(year);
	}

	
}