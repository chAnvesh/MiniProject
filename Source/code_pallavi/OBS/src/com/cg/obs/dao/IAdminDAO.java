package com.cg.obs.dao;

import java.time.LocalDate;
import java.util.ArrayList;



import java.util.Date;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

public interface IAdminDAO {

	//public long addCustomer(NewUserBean newUser) throws BankingException;
	//public ArrayList<TransactionBean> viewTransactions() throws BankingException;
	
	public ArrayList<TransactionBean> viewDailyTransactions(Date transDate)
			throws BankingException;
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,String year)
			throws BankingException;
	public ArrayList<TransactionBean> viewYearlyTransactions(String year)
			throws BankingException;
	/*ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(int year) throws BankingException;

	UserBean getUserDetails(int userId) throws BankingException;

	void updateUsertable(UserBean user, long accNo) throws BankingException;

	long updateAccountMaster(AccountMasterBean acc) throws BankingException;

	boolean updateCustomerTable(long accNo,CustomerBean cust) throws BankingException;*/
}
