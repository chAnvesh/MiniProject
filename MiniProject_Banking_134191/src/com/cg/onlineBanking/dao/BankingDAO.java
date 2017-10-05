package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.util.ConnectionUtil;

public class BankingDAO implements IBankingDAO{

	private Connection connect;
	private ConnectionUtil util;
	
	public BankingDAO() throws BankingException {
		util = new ConnectionUtil();
		connect = util.getConnection();
		
	}

	//Service Tracking Methods
	@Override
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		
		String qry = "INSERT INTO Service_Tracker VALUES(?,?,?,?,?)";
		int recsAffected=0;
		int serviceId = 0;
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setString(1, request.getDescription());
			ps.setLong(2, request.getAccNo());
			
			Date sql = Date.valueOf(request.getRaisedDate());
			ps.setDate(3, sql);
			ps.setString(4, "OPEN");
			
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
	public ServiceTrackerBean trackRequest(int requestId,long accNo)
			throws BankingException {
		
		String qry = "SELECT * FROM Service_Tracker where Service_id=? OR Account_id=?";
		ServiceTrackerBean service=null;
		ResultSet rs;
		
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setInt(1, requestId);
			ps.setLong(2, accNo);
			
			rs = ps.executeQuery();
			if(rs.next()){
				
				service = new ServiceTrackerBean();
				service.setServiceId(rs.getInt(1));
				service.setDescription(rs.getString(2));
				service.setAccNo((rs.getLong(3)));
				service.setRaisedDate(rs.getDate(4).toLocalDate());
				service.setStatus(rs.getString(5));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankingException("Unable to retrieve request status"+e.getMessage());
		}
		
		return service;
	}
	
	//End of Service Tracking Methods
}
