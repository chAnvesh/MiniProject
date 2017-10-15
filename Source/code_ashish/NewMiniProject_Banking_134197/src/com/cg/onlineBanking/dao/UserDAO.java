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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager manager;
	private Connection connect;

	//static Logger myLogger = Logger.getLogger("myLogger");
	
	public UserDAO() throws BankingException {
	
	}

	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException {
		
		String strQry = "SELECT a FROM AccountMasterBean a where a.userId=:id";
		TypedQuery<AccountMasterBean> qry = manager.createQuery(strQry, AccountMasterBean.class);
		qry.setParameter("id", userId);
		ArrayList<AccountMasterBean> list = (ArrayList<AccountMasterBean>)qry.getResultList();
		
		return list;
	}
	
	//MiniStatement and detailed statements.

		public List<TransactionBean> getMiniTransactionDetails(long accountNo) throws BankingException{
		
		ArrayList tList = new ArrayList();
		String strQry = "Select e from TransactionBean e where rownum<=10 and e.accountId=:accNo order by e.transactionDate";
				
		TypedQuery qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("accNo", accountNo);
		List<TransactionBean> transactionList = qry.getResultList();
		System.out.println(transactionList);
		return transactionList;
				    
		     }
		

		@Override
		public int updateAddress(int userId, String address)
				throws BankingException {
			int recAffected= 0;

			CustomerBean customer =manager.find(CustomerBean.class, userId);
			customer.setAddress(address);
			return recAffected;
			
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
		public List<TransactionBean> getDetailedTransactionDetails(
				long accountNo, java.util.Date dt1, java.util.Date dt2)
				throws BankingException {
			ArrayList tList = new ArrayList();
			
				
		String strQry = "Select e from TransactionBean e where e.transactionDate between :dt1 and :dt2 and e.accountId= :accNo order by e.transactionDate";
		TypedQuery qry = manager.createQuery(strQry, TransactionBean.class);
				qry.setParameter("dt1", dt1);
				qry.setParameter("dt2", dt2);
				qry.setParameter("accNo", accountNo);
				List<TransactionBean> transactionList = qry.getResultList();
				
				System.out.println("From Detailed Method: "+transactionList);
				return transactionList;
		}

		@Override
		public String getAddress(int i) {
			CustomerBean cust = manager.find(CustomerBean.class, i);
			return cust.getAddress();
		}
		

}
