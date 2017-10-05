package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.util.ConnectionUtil;

public class BankingDAO implements IBankingDAO{

	private Connection connect;
	private ConnectionUtil util;
	
	public BankingDAO() throws BankingException {
		util = new ConnectionUtil();
		connect = util.getConnection();
		
	}

	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override // to add payee to beneficiary list
	public void addPayee(PayeeBean payee) throws BankingException {
		int resultSet=0;
		try
		{
			PreparedStatement pstmt = connect.prepareStatement("insert into PayeeTable values(?,?,?)");
			pstmt.setLong(2, payee.getPayeeAccNo());
			pstmt.setLong(1, payee.getAccNo());
			pstmt.setString(3, payee.getPayeeName());
			
			resultSet= pstmt.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			throw new BankingException(e.getMessage());
		}
		
		
		
	}

	@Override // to insert transaction details to transaction table after successfull transaction
	public int addTrans(TransactionBean trans) throws BankingException {
	
	int  transId=getTransId();
	int outcome=0;
	try{
		PreparedStatement stmt=connect.prepareStatement("insert into Transactions values (?,?,?,?,?,?) ");
		stmt.setInt(1,transId);
		stmt.setString(2,trans.getTransactionDesc());
		stmt.setDate(3,new java.sql.Date(trans.getTransactionDate().getTime()));
		stmt.setString(4,trans.getTransactionType());
		stmt.setFloat(5, trans.getTransactionAmount());
		stmt.setLong(6,trans.getAccNo());
		
		outcome=stmt.executeUpdate();
			
		
	}catch(Exception e)
	{
		throw new BankingException(e.getMessage());
	}
	if(outcome==1)
	{
		updateAccBal(trans);
		return transId;
	}
	else
		return 0;
	}
	
	
	public void updateAccBal(TransactionBean trans) throws BankingException
	{
		
		try{
			
			PreparedStatement pstmt = connect.prepareStatement("update Account_Master set Account_Balance = Account_Balance - ? where Account_ID=?");
			pstmt.setFloat(1,trans.getTransactionAmount());
			pstmt.setLong(2, trans.getAccNo());
			pstmt.execute();
			
			
		}catch(Exception e)
		{
			throw new BankingException(e.getMessage());
		}
		
		
		
		
		
		
	}
	
	
	
	public int getTransId() throws BankingException
	{
		String qry="select transId.NEXTVAL from dual";
		int transId=0;
		try
		{
			
			PreparedStatement  stmt=connect.prepareStatement(qry);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				transId=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			throw new BankingException(e.getMessage());
		}
		
		return transId;
	}

	
	@Override
	public ArrayList<PayeeBean> showPayeeList(long accNo)
			throws BankingException {
		ArrayList<PayeeBean> payeeList = new ArrayList<>();
		
		PayeeBean bean=null;
		try{
			PreparedStatement psmt=connect.prepareStatement("select Payee_Account_Id from PayeeTable where Account_Id=?");
			psmt.setLong(1,accNo);
			ResultSet rs=psmt.executeQuery();

			while(rs.next()){
				Long payeeAccNo=rs.getLong(1);
				String payeeName=rs.getString(2);
				
				bean=new PayeeBean(payeeAccNo,payeeName);
				payeeList.add(bean);

			}
			
		}catch(Exception e)
		{
			throw new BankingException(e.getMessage());
		}
		
		return payeeList;
	}

	@Override
	//for retriving accno list for doing transactions
	
	public ArrayList getAccountNoList(int userId) throws BankingException {
		String qry="select accNo from user_table where userId=?";
		ArrayList idlist=new ArrayList();
		try(PreparedStatement psmt=connect.prepareStatement(qry)){
			psmt.setLong(1,userId);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next()){
				Long accNo=rs.getLong(1);
				idlist.add(accNo);

			}
		} catch (SQLException e) {
			throw new BankingException(e.getMessage());
		}
		return idlist;
	}

	
	@Override
	public boolean validateTransPassword(int userId,String UserEnteredpasswd) throws BankingException {
		String qry="select Transaction_password from user_table where userId=?";
		String password=null;
		try(PreparedStatement psmt=connect.prepareStatement(qry)){
			
			psmt.setLong(1,userId);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next()){
				password=rs.getString(1);			}
		} catch (SQLException e) {
			throw new BankingException(e.getMessage());
		}
		if(UserEnteredpasswd.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}

	@Override// validating transatrion amount based on enterd amt and type of acct
	public boolean validateBalance(long accNo, float transactionAmount,int userId,long payeeAccNo)
			throws BankingException {
		ArrayList idlist=new ArrayList();
		idlist=getAccountNoList(userId);
		String qry="select Account_Balance from Account_Master  where Account_ID=?";
		float accBal=0;
        try(PreparedStatement psmt=connect.prepareStatement(qry)){
			
			psmt.setLong(1,accNo);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next()){
				accBal=rs.getFloat(1);			}
		} catch (SQLException e) {
			throw new BankingException(e.getMessage());
		}
		if(accBal>transactionAmount)
		{
			if(idlist.contains(payeeAccNo))
			{
				return true;
			}
			else
			{
				if(transactionAmount<1000000)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		
			}

	@Override
	public boolean validatePayeeAccountNo(long payeeAccNo ) throws BankingException {
		ArrayList<Long> payeeList=new ArrayList<>();
		
		try(PreparedStatement psmt=connect.prepareStatement("select Account_ID from Account_master");){
			
			
			ResultSet rs=psmt.executeQuery();

			while(rs.next()){
				Long existingpayeeAccNo=rs.getLong(1);
				
				payeeList.add(existingpayeeAccNo);

			}
			
		}catch(Exception e)
		{
			throw new BankingException(e.getMessage());
		}		
		if(payeeList.contains(payeeAccNo))
		return true;
		else
			return false;
	}
}