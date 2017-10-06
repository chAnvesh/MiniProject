package com.cg.onlineBanking.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IAdminDAO {

	public long addCustomer(NewUserBean newUser) throws BankingException;
	//public ArrayList<TransactionBean> viewTransactions() throws BankingException;
	
	ArrayList<TransactionBean> viewDailyTransactions(LocalDate date) throws BankingException;
	ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(int year) throws BankingException;

	UserBean getUserDetails(int userId) throws BankingException;

	void updateUsertable(UserBean user, long accNo) throws BankingException;

	long updateAccountMaster(AccountMasterBean acc) throws BankingException;

	boolean updateCustomerTable(long accNo,CustomerBean cust) throws BankingException;
}
