package com.cg.obs.service;

import java.util.ArrayList;
import java.util.Date;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;


public interface IAdminService {

	// Viewing Transactions.
	ArrayList<TransactionBean> viewDailyTransactions(Date date) throws BankingException;
	ArrayList<TransactionBean> viewMonthlyTransactions(String month,String year) throws BankingException;
	ArrayList<TransactionBean> viewYearlyTransactions(String year) throws BankingException;
	
	public void addAccount(AccountMasterBean bean) throws BankingException;

}
