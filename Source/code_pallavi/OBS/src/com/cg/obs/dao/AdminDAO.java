package com.cg.obs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.util.ConnectionUtil;

@Repository
public class AdminDAO implements IAdminDAO {

	@PersistenceContext
	EntityManager manager;
	
	
	
	//////////////////////////////////////////////////////////////////////////////
	//Transactions methods
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(Date transDate)
			throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE t.transactionDate=:dt";
		//"Select e from TransactionBean e where e.transactionDate between :dt1 and :dt2 and e.accountId= :accNo order by e.transactionDate"
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("dt", transDate);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}
	
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month, String year)
			throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE TO_CHAR(t.transactionDate,'MON')=:mon AND TO_CHAR(t.transactionDate,'YYYY')=:yr";
		//"Select e from TransactionBean e where e.transactionDate between :dt1 and :dt2 and e.accountId= :accNo order by e.transactionDate"
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("mon", month);
		qry.setParameter("yr", year);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(String year)
			throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE TO_CHAR(t.transactionDate,'YYYY')=:yr";
		//"Select e from TransactionBean e where e.transactionDate between :dt1 and :dt2 and e.accountId= :accNo order by e.transactionDate"
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		
		qry.setParameter("yr", year);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}

	
	/*@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String monthly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(date_Of_Transaction,'MON')=? AND TO_CHAR(date_Of_Transaction,'YYYY')=?";
		
		try (	PreparedStatement psMonthly = connect.prepareStatement(monthly);)
			{
				psMonthly.setString(1, month);
				psMonthly.setInt(2, year);
					
				ResultSet rs = psMonthly.executeQuery();
		
			while(rs.next()){
				
				int transactionId = rs.getInt(1);
				String transactionDesc = rs.getString(2);
				Date transactionDate = rs.getDate(3);
				String transactionType = rs.getString(4);
				float transactionAmount = rs.getFloat(5);
				long accNo = rs.getLong(6);
				
				transList.add(new TransactionBean(transactionId, transactionDesc, transactionDate, transactionType, transactionAmount, accNo));
			}
		} catch (SQLException e) {
			throw new BankingException("Unable to retreive Transaction details",e);
			 
		}
		
		return transList;
	}*/

	/*
	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(int year)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String yearly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(date_Of_Transaction,'YYYY')=?";
		
		try (	PreparedStatement psYearly = connect.prepareStatement(yearly);)
			{
				psYearly.setInt(1, year);		
				ResultSet rs = psYearly.executeQuery();
		
			while(rs.next()){
				
				int transactionId = rs.getInt(1);
				String transactionDesc = rs.getString(2);
				Date transactionDate = rs.getDate(3);
				String transactionType = rs.getString(4);
				float transactionAmount = rs.getFloat(5);
				long accNo = rs.getLong(6);
				
				transList.add(new TransactionBean(transactionId, transactionDesc, transactionDate, transactionType, transactionAmount, accNo));
			}
		} catch (SQLException e) {
			throw new BankingException("Unable to retreive Transaction details",e);
			 
		}
		
		return transList;
	}*/
	/*
	@Override
	public UserBean getUserDetails(int userId) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateCustomerTable(long accNo,
			com.cg.obs.dao.CustomerBean cust) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public UserBean getUserDetails(int userId) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateUsertable(UserBean user, long accNo)
			throws BankingException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public long updateAccountMaster(AccountMasterBean acc)
			throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}
*/}
