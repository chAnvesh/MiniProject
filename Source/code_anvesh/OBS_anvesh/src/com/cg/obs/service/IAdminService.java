package com.cg.obs.service;

import java.time.LocalDate;
import java.util.ArrayList;


import com.cg.obs.exception.BankingException;

public interface IAdminService {

//	public long addCustomer(NewUserBean newUser,CustomerBean cust) throws BankingException;

	//public ArrayList<TransactionBean> viewTransactions() throws BankingException;
	
	public boolean validateDate(LocalDate transDate) throws BankingException;
	public boolean validateMonth(String month) throws BankingException;
	
	/*ArrayList<TransactionBean> viewDailyTransactions(LocalDate date) throws BankingException;
	ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(int year) throws BankingException;
*/

}
