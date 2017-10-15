package com.cg.obs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List getUserIdList() throws BankingException {
		
		String qryStr = "SELECT u.userId FROM USER_TABLE u";
		Query qry = manager.createQuery(qryStr);
		List userIdList = qry.getResultList();
		return userIdList;
		
		
	}
	
	

	@Override
	public UserBean getUserDetailsOnId(int userId) throws BankingException {
		
		UserBean user = manager.find(UserBean.class, userId);
		return user;
	}
	
	//method for forgot password
			@Override
			public void forgotPassword(int userId) throws BankingException {
				
				UserBean user = manager.find(UserBean.class, userId);
				user.setLoginPassword("sbq500#");
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
