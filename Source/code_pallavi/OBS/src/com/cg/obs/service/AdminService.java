package com.cg.obs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.obs.dao.AdminDAO;
import com.cg.obs.dao.IAdminDAO;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Resource
	private IAdminDAO adminDAO;
	
	/*@Override
	public long addCustomer(NewUserBean newUser,CustomerBean cust) throws BankingException {
		
		AccountMasterBean acc = new AccountMasterBean(newUser.getAccountType(), newUser.getOpeningBalance());
		
		long accNo = adminDAO.updateAccountMaster(acc);
		adminDAO.updateCustomerTable(accNo,cust);
		//System.out.println(newUser.getUserId());
		//System.out.println(adminDAO.getUserDetails(newUser.getUserId()));
		adminDAO.updateUsertable(adminDAO.getUserDetails(newUser.getUserId()),accNo);
		
		return accNo;
	}

	*/

	/*@Override
	public boolean validateDate(LocalDate transDate) throws BankingException {
		
		if(transDate.compareTo(LocalDate.now())<=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean validateMonth(String month) throws BankingException {
		
		return month.matches("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)");
	}*/
	
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

	/*@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,
			int year) throws BankingException {
		
		return adminDAO.viewMonthlyTransactions(month,year);
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(int year)
			throws BankingException {
		
		return adminDAO.viewYearlyTransactions(year);
	}

	*/
}
