package com.cg.onlineBanking.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IUserDAO {

	
	
	//miscellaneous-userconsole
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException;
	
	//Mini and detailed statements
	public List<TransactionBean> getMiniTransactionDetails(long accountNo) throws BankingException;
	public List<TransactionBean> getDetailedTransactionDetails(long accountNo,Date dt1, Date dt2) throws BankingException;
	
	//Update Address
	public int updateAddress(int userId, String address) throws BankingException;
	
	
	
		public ArrayList getAccountNoList(int userId) throws BankingException;

		public String getAddress(int i);
	
	
}
