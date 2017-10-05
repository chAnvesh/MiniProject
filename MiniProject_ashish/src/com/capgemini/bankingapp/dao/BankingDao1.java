package com.capgemini.bankingapp.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.capgemini.bankingapp.beans.AccountMasterBean;
import com.capgemini.bankingapp.beans.CustomerBean;
import com.capgemini.bankingapp.beans.UserBean;
import com.capgemini.bankingapp.exception.CustomException;

public interface BankingDao1 {

	public ArrayList getMiniTransactionDetails(int accountNo) throws CustomException;
	public ArrayList getDetailedTransactionDetails(int accountNo,LocalDate dt1,LocalDate dt2) throws CustomException;
	public int updateAddress(int accId, String address) throws CustomException;
	public boolean updateCustomerTable(CustomerBean cust) throws CustomException;
	public void updateAccountMaster(AccountMasterBean acc) throws CustomException;
	public UserBean getUserDetails(int userId) throws CustomException;
	public void updateUsertable(UserBean user, long accNo) throws CustomException;
	
}
