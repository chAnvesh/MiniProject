package com.cg.obs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.bean.UserBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.util.ConnectionUtil;

public class BankingDAO implements IBankingDAO{

	private Connection connect;
	private ConnectionUtil util;
	//static Logger myLogger = Logger.getLogger("myLogger");
	
	public BankingDAO() throws BankingException {
		util = new ConnectionUtil();
		connect = util.getConnection();
		//myLogger.info("Connection Procured in BankingDao");
	}

	@Override
	public ArrayList getUserIdList() throws BankingException {
		String qry="select user_Id from user_table";
		ArrayList userList;
		try {
			Statement st=connect.createStatement();
			userList = new ArrayList();
			ResultSet rs=st.executeQuery(qry);
			
			while(rs.next()){
				userList.add(rs.getInt("user_Id"));
				
			}
		} catch (SQLException e) {
			throw new BankingException("unable to retrieve userId List",e);
		}
		
		return userList;
	}

	@Override
	public UserBean getUserDetailsOnId(int userId) throws BankingException {
		String qry="select * from user_table where user_id=?";
		UserBean bean=null;
		try {
			PreparedStatement st=connect.prepareStatement(qry);
			st.setInt(1, userId);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()){
				bean=new UserBean();
				bean.setAccNo(rs.getInt("account_id"));
				bean.setLockStatus(rs.getInt("lock_status"));
				bean.setLoginPassword(rs.getString("login_password"));
				bean.setSecretQstn(rs.getString("secret_question"));
				bean.setUserId(userId);
				bean.setTransPassword(rs.getString("transaction_Password"));
				
			}
			
		} catch (SQLException e) {
			
			throw new BankingException("unable to retrieve userDetails List",e);
		}
		
		return bean;
	}
	
	//method for forgot password
			@Override
			public boolean forgotPassword(int userId, String secretQstn)
					throws BankingException {
				String qry = "SELECT * FROM USER_TABLE WHERE USER_ID=?";
				 
				try(PreparedStatement ps = connect.prepareStatement(qry);) {
					
					ps.setInt(1, userId);
					ResultSet rs = ps.executeQuery();
					UserBean user = new UserBean();
					if(rs.next()){
						user.setAccNo(rs.getInt(1));
						user.setLoginPassword(rs.getString(3));
						user.setSecretQstn(rs.getString(4));
						user.setUserId(userId);
					}
					if(secretQstn.equals(user.getSecretQstn())){
						String query = "UPDATE user_table set login_password='sbq500#' where user_Id=?";
						
						PreparedStatement pst = connect.prepareStatement(query);
						pst.setInt(1, userId);
						int rst = 0 ;
						rst = pst.executeUpdate();
						
						return (rst>0)?true:false;
					}
					
					return false;
				} catch (SQLException e) {
					throw new BankingException("Failed to Update password"+e);
				}
				
			}

			@Override
			public boolean upadteLockStatus(int userId) throws BankingException {
				String qry = "Update user_table set lock_status=lock_status+1 where user_id=?";
				int rst = 0;
				try(PreparedStatement pst = connect.prepareStatement(qry);){
					pst.setInt(1, userId);
					rst = pst.executeUpdate();
					
				}catch (SQLException e) {
					throw new BankingException("Failed to Update lock status"+e);
				}
				return (rst>0)?true:false;
			}


	
}
