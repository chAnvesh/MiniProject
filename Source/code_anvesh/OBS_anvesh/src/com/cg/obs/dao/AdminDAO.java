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

import org.springframework.stereotype.Repository;

import com.cg.obs.exception.BankingException;
import com.cg.obs.util.ConnectionUtil;

@Repository
public class AdminDAO implements IAdminDAO {

	@PersistenceContext
	EntityManager maanger;
	
	
	/*@Override
	public long addCustomer(NewUserBean newUser) throws BankingException {
		
		String qry = "INSERT INTO ";
		
		return 0;
	}

	@Override
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
	}
*/	
	
	/*@Override
	public boolean updateCustomerTable(long accNo, CustomerBean cust) throws BankingException {
		String qry ="insert into customer values(?, ?, ?, ?,?)" ;
		

		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			stat.setLong(1,  accNo);
			stat.setString(2, cust.getCustName());
			stat.setString(3, cust.getAddress());
			stat.setString(4, cust.getEmail());
			stat.setString(5, cust.getPanCard());
			
			int recAffected = stat.executeUpdate();
		
			}catch(SQLException e){
				
				throw new BankingException("Problem in Updating Table",e);
			}
		return false;
			
		
		
	}
	@Override
	public long updateAccountMaster(AccountMasterBean acc) throws BankingException {
		
		String qry ="insert into account_master values(?, ?, ?, sysdate)" ;
		//gets accNo from sequence.
		long accNo = getAccountNo();
		int recAffected =0;
		//System.out.println();
		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			//Date dt= Date.valueOf(acc.getDate());
			stat.setLong(1,  accNo);
			stat.setString(2, acc.getAccType());
			stat.setFloat(3,acc.getAccBal());
			//stat.setDate(4, acc.getDate());
			
			recAffected = stat.executeUpdate();
		
			}catch(SQLException e){
				
				throw new BankingException("Problem in Updating Table",e);
			}
		return (recAffected==1) ? accNo:0;
	}
	
	
	private long getAccountNo() throws BankingException {
		long accNo=0;
		
		String qry = "select account_no_gen.NEXTVAL from DUAL";
		
		try (Statement stmt = connect.createStatement();
				
				ResultSet rs = stmt.executeQuery(qry);)
		{
			if(rs.next()){
				accNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			throw new BankingException("Unable to fetch accounNo",e);
		}
		return accNo;
	}

	@Override
	public UserBean getUserDetails(int userId)
			throws BankingException {
		//System.out.println(userId);
		String qry = "select * from user_table where user_id= ?";
		UserBean user = null;
		try (					
				PreparedStatement stat = connect.prepareStatement(qry);
				){
			stat.setInt(1, userId);
			
			
			ResultSet rs = stat.executeQuery();
			
				if(rs.next()){
					Long accNo =rs.getLong(1);
					int useId = rs.getInt(2);
					String password = rs.getString(3);
					String qsn = rs.getString(4);
				    String transPass = rs.getString(5);
				   int lockStat = rs.getInt(6);
					
				   user= new UserBean(accNo, useId, password, qsn, transPass, lockStat);
			
				}
			} catch (SQLException e) {
				
				throw new BankingException("Problem in Showing Transactions",e);
			}
		//System.out.println(user);

		    return user;
	}
	@Override
	public void updateUsertable(UserBean user, long accNo) throws BankingException{
		String qry ="insert into user_table values(?, ?, ?, ?, ?,?)" ;
		
		//System.out.println(user.getUserId());
		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			stat.setLong(1,  accNo);
			stat.setInt(2, user.getUserId());
			stat.setString(3, user.getLoginPassword());
			stat.setString(4, user.getSecretQstn());
			stat.setString(5, user.getTransPassword());
			stat.setInt(6, user.getLockStatus());
			
			int recAffected = stat.executeUpdate();
		
			
			}catch(SQLException e){
				
				throw new BankingException("Problem in Updating Table",e);
			}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//Transactions methods
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(LocalDate transDate)
			throws BankingException {
		ArrayList<TransactionBean> transList = new ArrayList<>();
		
		String daily = "SELECT * FROM TRANSACTIONS WHERE date_Of_Transaction = ? ";
		
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
	}

	
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
}
