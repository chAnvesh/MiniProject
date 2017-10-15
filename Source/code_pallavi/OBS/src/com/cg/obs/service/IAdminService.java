package com.cg.obs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;

public interface IAdminService {

	//public long addCustomer(NewUserBean newUser,CustomerBean cust) throws BankingException;

	//public ArrayList<TransactionBean> viewTransactions() throws BankingException;
	
	/*public boolean validateDate(LocalDate transDate) throws BankingException;
	public boolean validateMonth(String month) throws BankingException;
	*/
	ArrayList<TransactionBean> viewDailyTransactions(Date date) throws BankingException;
	ArrayList<TransactionBean> viewMonthlyTransactions(String month,String year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(String year) throws BankingException;
/*	ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(int year) throws BankingException;*/


}
