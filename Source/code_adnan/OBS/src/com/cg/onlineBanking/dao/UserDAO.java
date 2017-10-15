package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.FundTransferBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.util.ConnectionUtil;

@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager manager;
	
	
	
	static Logger myLogger = Logger.getLogger("myLogger");

	//Miscellaneous-Validating Credentials
	
	////////////////////////////////////////////////////////////////////////////////

	//Changing password/////////////////////////////////////////////////////////////
	/*@Override
	public boolean changePassword(String password, int userId)
			throws BankingException {
		String qry = "UPDATE user_table set login_password=? where user_Id=?";
		int rs = 0;
		
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setString(1, password);
			ps.setInt(2, userId);
			
			rs = ps.executeUpdate();
		
	} catch (SQLException e) {
		throw new BankingException("Failed to change password"+e);
	}
		return (rs>0)?true:false;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	//Miscellaneous--For UserConsole
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException{
		

		String qry = "SELECT * FROM account_master where account_id="
				+ "ANY (SELECT account_id FROM USER_TABLE WHERE USER_ID = ? )";
		
		
		String qry = "SELECT account_id,account_balance FROM account_master where account_id="
				+ "ANY (SELECT account_id FROM USER_TABLE WHERE USER_ID = ? )";
					
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
	public int raiseRequest(ServiceTrackerBean request) throws BankingException {
		String qry = "INSERT INTO Service_Tracker VALUES(?,?,?,sysdate,?)";
		int recsAffected=0;
		int serviceId = getRequestId();
		try(PreparedStatement ps = connect.prepareStatement(qry);) {
			
			ps.setInt(1, serviceId);
			ps.setString(2, request.getDescription());
			ps.setLong(3, request.getAccNo());
			
			ps.setString(4, "OPEN");
			
			recsAffected = ps.executeUpdate();
		} catch (SQLException e) {
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
			throw new BankingException("Unable to generate Request ID"+e.getMessage());
		}
		
		return requestId;
	}
	
	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		String qry = "SELECT * FROM (select * from Service_Tracker order by service_raised_date) where (Service_id=? OR Account_id=?) and rownum<=20";
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
			throw new BankingException("Unable to retrieve request status"+e.getMessage());
		}
		
		return serviceList;
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

	
	
	
	
	
	
	
	
	
	
	*/	@Override // to add payee to beneficiary list
		public void addPayee(PayeeBean payee) throws BankingException {
			System.out.println("in add payee dao mehtod");
			manager.persist(payee);			
		}
	
	
	

		@Override // to insert transaction details to transaction table after successfull transaction
		public int addTransForCredit(TransactionBean trans,long payeeAccNo) throws BankingException {
					
			manager.persist(trans);
			updateAccBalOfPayee(trans.getTransactionAmount(), payeeAccNo);
			return trans.getTransactionId();
		
		}
		
		@Override
		public int addTransForDebit(TransactionBean trans) throws BankingException
		{
			manager.persist(trans);
			updateAccBal(trans);
			return trans.getTransactionId();
		}
		
		@Override 
		public void addFundTransfer(FundTransferBean fundTrans) throws BankingException
		{
			System.out.println(fundTrans);
			manager.persist(fundTrans);
			
		
		}
		
		public void updateAccBal(TransactionBean trans) throws BankingException
		{
			
			/*String strQry="update AccountMasterBean  set accountBalance = accountBalance - ? where accountId=?";
			
			TypedQuery<Integer> qry=manager.createQuery(strQry,Integer.class);
			qry.setParameter(2,trans.getAccountId());
			qry.setParameter(1,trans.getTransactionAmount());*/
			
			AccountMasterBean acc = manager.find(AccountMasterBean.class, trans.getAccountId());
			acc.setAccountBalance(acc.getAccountBalance()-trans.getTransactionAmount());
			
		}
		
		
		
		
		
		public void updateAccBalOfPayee(long transAmount,long payeeAccNo) throws BankingException
		{
			/*String strQry="update AccountMasterBean a set a.accountBalance = a.accountBalance + ? where a.accountId=?";
			
			TypedQuery<Integer> qry=manager.createQuery(strQry,Integer.class);
			qry.setParameter(1,transAmount);
			qry.setParameter(2,payeeAccNo);
			manager.merge(qry);
			*/
			
			AccountMasterBean acc = manager.find(AccountMasterBean.class, payeeAccNo);
			acc.setAccountBalance(acc.getAccountBalance()+transAmount);
			//manager.merge(acc);
		}
		
		
	
		
		@Override
		public List<PayeeBean> showPayeeList(long accNo)
				throws BankingException {
			System.out.println(accNo);
			String qStr = "SELECT P FROM PayeeBean P WHERE P.accountId=:acc";
			TypedQuery<PayeeBean> query = manager.createQuery(qStr, PayeeBean.class);
			query.setParameter("acc", accNo);
			List<PayeeBean> payeeList=query.getResultList();
			System.out.println(payeeList);
			return payeeList;
		}

		@Override
		//for retriving accno list for doing transactions
		
		public List getAccountNoList(int userId) throws BankingException {
			String qry="select a.accountId from AccountMasterBean a where a.userId=?";
			TypedQuery<Long> query = manager.createQuery(qry, Long.class);
			query.setParameter(1,userId);
			List<Long> idList=query.getResultList();
			System.out.println(idList);
			return idList;
			
		}

		
		@Override
		public boolean validateTransPassword(int userId,String UserEnteredpasswd) throws BankingException {
			String qry="select transPassword from UserBean where userId=?";
			String password=null;
			TypedQuery<String> query = manager.createQuery(qry, String.class);
			query.setParameter(1,userId);
			password=query.getSingleResult();
			
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
		public boolean validateBalance(long accNo, long transactionAmount,int userId,long payeeAccNo)
				throws BankingException {
			List idlist=new ArrayList();
			idlist=getAccountNoList(userId);
			String qry="select accountBalance from AccountMasterBean  where accountId=?";
			long accBal=0;
			TypedQuery<Long> query = manager.createQuery(qry, Long.class);
			query.setParameter(1,accNo);
			accBal=query.getSingleResult();
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
			String qry=("select accountId from AccountMasterBean");
				TypedQuery<Long> query = manager.createQuery(qry, Long.class);
				List<Long> payeeList=query.getResultList();
				System.out.println(payeeList);
			if(payeeList.contains(payeeAccNo))
			return true;
			else
				return false;
		}

	/*	@Override
		public ArrayList<Long> getPayeeDetails(long userEnterPayee) throws BankingException {
			String qry="select p from PayeeBean p where payee_account_id=?";
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
		}*/
		

}
