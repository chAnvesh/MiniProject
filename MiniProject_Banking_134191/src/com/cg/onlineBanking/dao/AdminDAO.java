package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.util.ConnectionUtil;

public class AdminDAO implements IAdminDAO {

	private Connection connect ;
	private ConnectionUtil util;
	
	public AdminDAO() {
		connect = util.getConnection();
		
	}
	
	@Override
	public long addCustomer(NewUserBean newUser) throws BankingException {
		
		String qry = "INSERT INTO ";
		
		return 0;
	}

/*	@Override
	public ArrayList<TransactionBean> viewTransactions(LocalDate transDate, String month,int year)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String daily = "SELECT * FROM TRANSACTIONS WHERE dateOfTransaction = ? ";
		String monthly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(dateOfTransaction,'MON')=? AND TO_CHAR(dateOfTransaction,'YYYY')=?";
		String yearly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(dateOfTransaction,'YYYY')=?";
		
		PreparedStatement psDaily = connect.prepareStatement(daily);
		PreparedStatement psMonthly = connect.prepareStatement(monthly);
		PreparedStatement psYearly = connect.prepareStatement(yearly);
		
		psDaily.setDate(1,java.sql.Date.valueOf(transDate));
		psMonthly.setString(1, month);
		psMonthly.setInt(2, year);
		psYearly.setInt(1, year);
		
		try (	Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(qry1);)
			{
			
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
	
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(LocalDate transDate)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String daily = "SELECT * FROM TRANSACTIONS WHERE dateOfTransaction = ? ";
		
		try (	PreparedStatement psDaily = connect.prepareStatement(daily);)
			{
				psDaily.setDate(1,java.sql.Date.valueOf(transDate));
				ResultSet rs = psDaily.executeQuery();
		
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
	}

	
	@Override
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month,int year)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String monthly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(dateOfTransaction,'MON')=? AND TO_CHAR(dateOfTransaction,'YYYY')=?";
		
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
	}

	
	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(int year)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String yearly = "SELECT * FROM TRANSACTIONS WHERE TO_CHAR(dateOfTransaction,'YYYY')=?";
		
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
	}
}
