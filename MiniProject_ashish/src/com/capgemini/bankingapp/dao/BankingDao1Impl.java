package com.capgemini.bankingapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;











import com.capgemini.bankingapp.beans.AccountMasterBean;
import com.capgemini.bankingapp.beans.CustomerBean;
import com.capgemini.bankingapp.beans.TransactionBean;
import com.capgemini.bankingapp.beans.UserBean;
import com.capgemini.bankingapp.exception.CustomException;
import com.capgemini.bankingapp.utils.ConnectionUtil;




public class BankingDao1Impl implements BankingDao1 {

	private Connection connect;

	public BankingDao1Impl() throws CustomException{
		ConnectionUtil obj = new ConnectionUtil();
	    connect= obj.getConnection();
		
	}
	@Override
	public ArrayList getMiniTransactionDetails(int accountNo) throws CustomException {
	
	ArrayList tList = new ArrayList();
	String qry = "Select * from (select * from transactions order by DateofTransaction) where rownum<=10 and Account_No= "+ accountNo;
			try (					
					Statement stat = connect.createStatement();
					ResultSet rs = stat.executeQuery(qry);){
					
					while(rs.next()){
						String tDesc = rs.getString(2);
						Date tDate = rs.getDate(3);
						String tType = rs.getString(4);
					    float tAmount = rs.getFloat(5);
					    long accNo = rs.getLong(6);
						
				   tList.add(new TransactionBean(tDesc, tDate, tType, tAmount, accNo));
				
					}
				} catch (SQLException e) {
					
					throw new CustomException("Problem in Showing Transactions",e);
				}
			
			    return tList;
			    
	     }
	@Override
	public ArrayList getDetailedTransactionDetails(int accountNo,
			LocalDate dt1, LocalDate dt2) throws CustomException {
		ArrayList tList = new ArrayList();
		
	Date sqldt1= Date.valueOf(dt1);
		Date sqldt2= Date.valueOf(dt2);
		
		String qry = "Select * from (select * from transactions order by DateofTransaction) where DateofTransaction between ? and ? and Account_No= ?";

				try (					
						PreparedStatement stat = connect.prepareStatement(qry);
						){
					stat.setDate(1, sqldt1);
					stat.setDate(2, sqldt2);
					stat.setInt(3, accountNo);
					System.out.println("printing...");
					ResultSet rs = stat.executeQuery();
					
						while(rs.next()){
							String tDesc = rs.getString(2);
							Date tDate = rs.getDate(3);
							String tType = rs.getString(4);
						    float tAmount = rs.getFloat(5);
						    long accNo = rs.getLong(6);
							
						    System.out.println(tDesc + tDate + tType + tAmount);
					   tList.add(new TransactionBean(tDesc, tDate, tType, tAmount, accNo));
					
						}
					} catch (SQLException e) {
						
						throw new CustomException("Problem in Showing Transactions",e);
					}
				System.out.println(tList);
				    return tList;
				    
		
	}
	@Override
	public int updateAddress(int accId, String address) throws CustomException {
int recAffected= 0;
		System.out.println("printing...");
		
		String qry ="update customer set address= ? where account_id= ?" ;
		
		System.out.println();
		try(PreparedStatement stat = connect.prepareStatement(qry);){
			
			stat.setString(1, address);
			stat.setInt(2, accId);
			
			
			recAffected = stat.executeUpdate();
		
			
			}catch(SQLException e){
				
				throw new CustomException("Problem in Updating Table",e);
			}
			
		
		
			return recAffected;
		
	}
	@Override
	public boolean updateCustomerTable(CustomerBean cust) throws CustomException {
String qry ="insert into customer values(?, ?, ?, ?, ?)" ;
		

		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			stat.setLong(1,  cust.getAccNo());
			stat.setString(2, cust.getCustName());
			stat.setString(3, cust.getAddress());
			stat.setString(4, cust.getEamil());
			stat.setString(5, cust.getPanCard());
			
			int recAffected = stat.executeUpdate();
		
			
			}catch(SQLException e){
				
				throw new CustomException("Problem in Updating Table",e);
			}
		return false;
			
		
		
	}
	@Override
	public void updateAccountMaster(AccountMasterBean acc)
			throws CustomException {
   String qry ="insert into account_master values(?, ?, ?, ?)" ;
		
		System.out.println();
		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			Date dt= Date.valueOf(acc.getDate());
			stat.setLong(1,  acc.getAccNo());
			stat.setString(2, acc.getAccType());
			stat.setInt(3,acc.getAccBal());
			stat.setDate(4, dt);
			
			
			int recAffected = stat.executeUpdate();
		
			
			}catch(SQLException e){
				
				throw new CustomException("Problem in Updating Table",e);
			}
		
	}
	@Override
	public UserBean getUserDetails(int userId)
			throws CustomException {
	
		String qry = "select * from user where user_id= ?";
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
				   String lockStat = rs.getString(6);
					
				   user= new UserBean(accNo, useId, password, qsn, transPass, lockStat);
			
				}
			} catch (SQLException e) {
				
				throw new CustomException("Problem in Showing Transactions",e);
			}

		    return user;
	}
	@Override
	public void updateUsertable(UserBean user, long accNo) throws CustomException {
		String qry ="insert into user values(?, ?, ?, ?, ?,?)" ;
		
		System.out.println();
		try(PreparedStatement stat = connect.prepareStatement(qry);){
		
			stat.setLong(1,  accNo);
			stat.setInt(2, user.getUserId());
			stat.setString(3, user.getPassword());
			stat.setString(4, user.getQsn());
			stat.setString(5, user.getTransPass());
			stat.setString(6, user.getLockStat());
			
			int recAffected = stat.executeUpdate();
		
			
			}catch(SQLException e){
				
				throw new CustomException("Problem in Updating Table",e);
			}
		
	}
	
	

}
