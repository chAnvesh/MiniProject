package com.cg.obs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;






import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.bean.AccountMasterBean;
import com.cg.obs.bean.CustomerBean;
import com.cg.obs.bean.PayeeBean;
import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.bean.TransactionBean;
import com.cg.obs.bean.UserBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.util.ConnectionUtil;

@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager manager;
	
	private Connection connect;

	//Miscellaneous-Validating Credentials
	
	////////////////////////////////////////////////////////////////////////////////

	//Changing password/////////////////////////////////////////////////////////////
	
	@Override
	public boolean validatePassword(int userId, String oldPassword) throws BankingException {
		UserBean user = manager.find(UserBean.class, userId);
		
		if("oldPassword".equals(user.getLoginPassword())){
			return true;
		}else{
		return false;
		}
	}
	@Override
	public void updatePassword(int userId, String newPassword) throws BankingException {
		UserBean user = manager.find(UserBean.class, userId);
				user.setLoginPassword(newPassword);
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	//Miscellaneous--For UserConsole
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException{
		

		String qry = "SELECT * FROM account_master where account_id="
				+ "ANY (SELECT account_id FROM USER_TABLE WHERE USER_ID = ? )";
		
		/*
		String qry = "SELECT account_id,account_balance FROM account_master where account_id="
				+ "ANY (SELECT account_id FROM USER_TABLE WHERE USER_ID = ? )";
		*/			
		ArrayList<AccountMasterBean> list = new ArrayList<>();
		try(PreparedStatement ps = connect.prepareStatement(qry);) 
			{
				ps.setInt(1, userId);
						
				ResultSet rs = ps.executeQuery();
					
				while(rs.next()){
					
					long accNo = rs.getLong("account_id");
					long accBal = rs.getLong("account_balance");
					String accType = rs.getString("account_type");
					LocalDate date = rs.getDate("Open_date").toLocalDate();
					
					list.add(new AccountMasterBean(accNo,accType,accBal,date));
				}
				
			} catch (SQLException e) {
				throw new BankingException("Failed to retreive account details"+e);
			}
				return list; 	
	}
	////////////////////////////////////////////////////////////////////////////

	//Raising request and tracking
	@Override
	public void raiseRequest(ServiceTrackerBean request) throws BankingException {
		
		/*
		 * DML operations cannot happen without transactions management
		 * here we r doing declarative transaction handling instead of programmatic
		 * this is done in the service layer
	 	 */
		request.setRaisedDate(java.sql.Date.valueOf(LocalDate.now()));
		manager.persist(request);
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
			throw new BankingException("Unable to generate Request ID"+e.getMessage());
		}
		
		return requestId;
	}
	
	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		ArrayList<ServiceTrackerBean> tList = new ArrayList<>();
		
		if(accNo==0){
			
			tList.add(manager.find(ServiceTrackerBean.class, requestId));
			
		}else{
			
			String strQry = "SELECT t FROM SERVICETRACKERBEAN t WHERE t.accNo=:number";
			TypedQuery<ServiceTrackerBean> qry = manager.createQuery(strQry, ServiceTrackerBean.class);
			qry.setParameter("number", accNo);
			tList = (ArrayList<ServiceTrackerBean>)qry.getResultList();
			
		}
		return tList;
		
		/*String qry = "SELECT * FROM (select * from Service_Tracker order by service_raised_date) where (Service_id=? OR Account_id=?) and rownum<=20";
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
				service.setRaisedDate(rs.getDate(4));      
				service.setStatus(rs.getString(5));
				
				serviceList.add(service);
				//System.out.println(serviceList);
			}
		
		} catch (SQLException e) {
			throw new BankingException("Unable to retrieve request status"+e.getMessage());
		}
		
		return serviceList;*/
	}	
	////////////////////////////////////////////////////////////////////////////
	
	//MiniStatement and detailed statements.

		public ArrayList getMiniTransactionDetails(long accountNo) throws BankingException{
		
		ArrayList tList = new ArrayList();
		String qry = "Select * from (select * from transactions order by Date_of_Transaction) where rownum<=10 and Account_No= "+ accountNo;
				try (					
						Statement stat = connect.createStatement();
						ResultSet rs = stat.executeQuery(qry);){
						
						while(rs.next()){
							int tId     =rs.getInt(1);
							String tDesc = rs.getString(2);
							Date tDate = rs.getDate(3);
							String tType = rs.getString(4);
						    float tAmount = rs.getFloat(5);
						    long accNo = rs.getLong(6);
							
					   tList.add(new TransactionBean(tId, tDesc, tDate, tType, tAmount, accNo));
					
						}
					} catch (SQLException e) {
						
						throw new BankingException("Problem in Showing Transactions",e);
					}
					
				    return tList;
				    
		     }
		@Override
		public ArrayList getDetailedTransactionDetails(long accountNo,LocalDate dt1, LocalDate dt2) throws BankingException {
			
			ArrayList tList = new ArrayList();
			
		Date sqldt1= Date.valueOf(dt1);
			Date sqldt2= Date.valueOf(dt2);
			
			String qry = "Select * from (select * from transactions order by Date_of_Transaction) where Date_of_Transaction between ? and ? and Account_No= ?";

					try (					
							PreparedStatement stat = connect.prepareStatement(qry);
							){
						stat.setDate(1, sqldt1);
						stat.setDate(2, sqldt2);
						stat.setLong(3, accountNo);
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
							
							throw new BankingException("Problem in Showing Transactions",e);
						}
					System.out.println(tList);
					    return tList;
					    
			
		}

		@Override
		public int updateAddress(long accId, String address)
				throws BankingException {
			int recAffected= 0;

			
			String qry ="update customer set address= ? where account_id= ?" ;
			
			System.out.println();
			try(PreparedStatement stat = connect.prepareStatement(qry);){
				
				stat.setString(1, address);
				stat.setLong(2, accId);
				
				
				recAffected = stat.executeUpdate();
			
				
				}catch(SQLException e){
					
					throw new BankingException("Problem in Updating Table",e);
				}
				
			
			
				return recAffected;
			
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
				throw new BankingException("failed to add payee",e);
			}
			
			
			
		}

		@Override // to insert transaction details to transaction table after successfull transaction
		public int addTrans(TransactionBean trans,long payeeAccNo) throws BankingException {
		
		int  transId=getTransId();
		int outcome=0;
		try{
			PreparedStatement stmt=connect.prepareStatement("insert into Transactions values (?,?,sysdate,?,?,?) ");
			stmt.setInt(1,transId);
			stmt.setString(2,trans.getTransactionDesc());
			//stmt.setDate(3,new java.sql.Date(trans.getTransactionDate().getTime()));
			stmt.setString(3,trans.getTransactionType());
			stmt.setFloat(4, trans.getTransactionAmount());
			stmt.setLong(5,trans.getAccNo());
			
			outcome=stmt.executeUpdate();
				
			
		}catch(Exception e)
		{
			throw new BankingException("transaction failed",e);
		}
		if(outcome==1)
		{
			updateAccBal(trans);
			updateAccBalOfPayee(trans.getTransactionAmount(), payeeAccNo);
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
				throw new BankingException("Failed to update",e);
			}
			
		}
		
		
		public void updateAccBalOfPayee(float transAmount,long payeeAccNo) throws BankingException
		{
			
			try{
				
				PreparedStatement pstmt = connect.prepareStatement("update Account_Master set Account_Balance = Account_Balance + ? where Account_ID=?");
				pstmt.setFloat(1,transAmount);
				pstmt.setLong(2, payeeAccNo);
				pstmt.execute();
				
				
			}catch(Exception e)
			{
				throw new BankingException("failed to make transaction",e);
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
				throw new BankingException("failed to generate transaction id",e);
			}
			
			return transId;
		}

		
		@Override
		public ArrayList<PayeeBean> showPayeeList(long accNo)
				throws BankingException {
			ArrayList<PayeeBean> payeeList = new ArrayList<>();
			
			PayeeBean bean=null;
			try{
				PreparedStatement psmt=connect.prepareStatement("select * from PayeeTable where Account_Id=?");
				psmt.setLong(1,accNo);
				ResultSet rs=psmt.executeQuery();

				while(rs.next()){
					Long payeeAccNo=rs.getLong(2);
					String payeeName=rs.getString(3);
					
					bean=new PayeeBean(accNo, payeeAccNo,payeeName);
					payeeList.add(bean);

				}
				
			}catch(Exception e)
			{
				throw new BankingException("failed to retreive payee",e);
			}
		
			return payeeList;
		}

		@Override
		//for retriving accno list for doing transactions
		
		public ArrayList getAccountNoList(int userId) throws BankingException {
			String qry="select account_id from user_table where user_Id=?";
			ArrayList idlist=new ArrayList();
			try(PreparedStatement psmt=connect.prepareStatement(qry)){
				psmt.setLong(1,userId);
				ResultSet rs=psmt.executeQuery();
				
				while(rs.next()){
					Long accNo=rs.getLong(1);
					idlist.add(accNo);

				}
			} catch (SQLException e) {
				throw new BankingException("failed to get account number",e);
			}
			return idlist;
		}

		
		@Override
		public boolean validateTransPassword(int userId,String UserEnteredpasswd) throws BankingException {
			String qry="select Transaction_password from user_table where user_Id=?";
			String password=null;
			try(PreparedStatement psmt=connect.prepareStatement(qry)){
				
				psmt.setLong(1,userId);
				ResultSet rs=psmt.executeQuery();
				
				while(rs.next()){
					password=rs.getString(1);			}
			//	System.out.println(password);
			} catch (SQLException e) {
				throw new BankingException("failed to get transaction password",e);
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
				throw new BankingException("failed to get account balance",e);
			}
			if(accBal>transactionAmount&&transactionAmount>0)
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
				throw new BankingException("failed get account id",e);
			}		
			if(payeeList.contains(payeeAccNo))
			return true;
			else
				return false;
		}

		@Override
		public ArrayList<Long> getPayeeDetails(long userEnterPayee) throws BankingException {
			String qry="select * from payeetable where payee_account_id=?";
			ArrayList<Long> idList=new ArrayList<>();
			Long payeeNo =(long) 0;
			String payeeName=null;
			try(PreparedStatement psmt=connect.prepareStatement(qry)){
				psmt.setLong(1,userEnterPayee);
				ResultSet rs=psmt.executeQuery();
				
				while(rs.next()){
					payeeNo=rs.getLong(2);			
					idList.add(payeeNo);

				}
			} catch (SQLException e) {
				throw new BankingException("failed to get account number",e);
			}
			
			return idList;
		}
		
		

}
