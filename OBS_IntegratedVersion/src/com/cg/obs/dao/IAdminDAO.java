package com.cg.obs.dao;


import java.util.ArrayList;
import java.util.Date;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;

public interface IAdminDAO {

	//Viewing Transactions.
	public ArrayList<TransactionBean> viewDailyTransactions(Date transDate) throws BankingException;
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,String year) throws BankingException;
	public ArrayList<TransactionBean> viewYearlyTransactions(String year) throws BankingException;
	
	public void addAccount(AccountMasterBean bean) throws BankingException;
}
