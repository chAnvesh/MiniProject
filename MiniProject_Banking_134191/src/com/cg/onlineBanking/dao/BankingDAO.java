package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
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
			// TODO Auto-generated catch block
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
				
				bean.setLockStatus(rs.getString("lock_status"));
				bean.setLoginPassword(rs.getString("login_password"));
				bean.setSecretQstn(rs.getString("secret_question"));
				bean.setUserId(userId);
				bean.setTransPassword(rs.getString("transaction_Password"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankingException("unable to retrieve userDetails List",e);
		}
		
		return bean;
	}

	
}
