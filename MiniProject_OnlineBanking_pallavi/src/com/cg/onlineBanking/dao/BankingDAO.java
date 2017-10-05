package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.util.ConnectionUtil;

public class BankingDAO implements IBankingDAO{

	private Connection connect;
	private ConnectionUtil util;
	
	public BankingDAO() throws BankingException {
		util = new ConnectionUtil();
		connect = util.getConnection();
		//System.out.println("Connection opened");
	}

	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		
		String qry = "INSERT INTO Service_Tracker VALUES(?,?,?,?,?)";
		int recsAffected=0;
		int serviceId = getRequestId();
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setInt(1, serviceId);
			ps.setString(2, request.getDescription());
			ps.setLong(3, request.getAccNo());
			
			Date sql = Date.valueOf(request.getRaisedDate());
			ps.setDate(4, sql);
			ps.setString(5, "OPEN");
			
			recsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankingException("Unable to raise request"+e.getMessage());
		}
		
		return (recsAffected==1) ? serviceId:0;
	}
	
	private int getRequestId() throws BankingException {
		int requestId = 0;
		String sql = "SELECT REQUEST_SEQ.NEXTVAL FROM DUAL";
		try(PreparedStatement pstmt= connect.prepareStatement(sql);)
		{
				ResultSet res = pstmt.executeQuery();
				if(res.next())
				{
					requestId = res.getInt(1);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankingException("Unable to generate Request ID"+e.getMessage());
		}
		
		return requestId;
	}

	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId,long accNo)
			throws BankingException {
		
		//System.out.println("In tracking method");
		String qry = "SELECT * FROM Service_Tracker where Service_id=? OR Account_id=?";
		ArrayList<ServiceTrackerBean> serviceList=new ArrayList<>();
		ResultSet rs;
		
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
		//	System.out.println("In tracking try block");
			ps.setInt(1, requestId);
			ps.setLong(2, accNo);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ServiceTrackerBean service = new ServiceTrackerBean();
				service.setServiceId(rs.getInt(1));
				service.setDescription(rs.getString(2));
				service.setAccNo((rs.getLong(3)));
				service.setRaisedDate(rs.getDate(4).toLocalDate());
				service.setStatus(rs.getString(5));
				
				serviceList.add(service);
				//System.out.println(serviceList);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankingException("Unable to retrieve request status"+e.getMessage());
		}
		
		return serviceList;
	}

	@Override
	public boolean changePassword(String password,int userId) throws BankingException {
		
		String qry = "UPDATE user_table set login_password=? where userId=?";
		int rs = 0;
		
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setString(1, password);
			ps.setInt(2, userId);
			
			rs = ps.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return (rs>0)?true:false;
	}

}
