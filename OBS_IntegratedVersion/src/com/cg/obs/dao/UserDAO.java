package com.cg.obs.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.FundTransferBean;
import com.cg.obs.entities.PayeeBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;




@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager manager;
	
	
	//Changing password/////////////////////////////////////////////////////////////
	
		@Override
		public boolean validatePassword(int userId, String oldPassword) throws BankingException {
			UserBean user = manager.find(UserBean.class, userId);
		
			if(oldPassword.equals(user.getLoginPassword())){
				
				return true;
			}else{
				System.out.println("not valid");
			return false;
			}
		
		}
		@Override
		public void updatePassword(int userId, String newPassword) throws BankingException {
			UserBean user = manager.find(UserBean.class, userId);
					user.setLoginPassword(newPassword);
			
		}

	@Override
	public ArrayList<AccountMasterBean> getAccountDetails(int userId) throws BankingException {
		
		String strQry = "SELECT a FROM AccountMasterBean a WHERE a.userId=:id";
		TypedQuery<AccountMasterBean> qry = manager.createQuery(strQry, AccountMasterBean.class);
		qry.setParameter("id", userId);
		ArrayList<AccountMasterBean> list = (ArrayList<AccountMasterBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}

	@Override
	public void resetLockStatus(int userId) throws BankingException {
		UserBean user = manager.find(UserBean.class, userId);
		
		user.setLockStatus(0);
		
	}
	@Override
	public List<TransactionBean> getMiniTransactionDetails(long accountNo)
			throws BankingException {
		
		String strQry = "Select e from TransactionBean e where rownum<=10 and e.accountId=:accNo order by e.transactionDate desc";
				
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("accNo", accountNo);
		List<TransactionBean> transactionList = qry.getResultList();
		System.out.println(transactionList);
		return transactionList;
				    
	}
	@Override
	public List<TransactionBean> getDetailedTransactionDetails(long accountNo,
			Date dt1, Date dt2) throws BankingException {
		
		String strQry = "Select e from TransactionBean e where e.transactionDate between :dt1 and :dt2 and e.accountId= :accNo order by e.transactionDate";
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
				qry.setParameter("dt1", dt1);
				qry.setParameter("dt2", dt2);
				qry.setParameter("accNo", accountNo);
				List<TransactionBean> transactionList = qry.getResultList();
				
				System.out.println("From Detailed Method: "+transactionList);
				return transactionList;
	}
	
	@Override
	public String getAddress(int userId) {
		CustomerBean cust = manager.find(CustomerBean.class, userId);
		return cust.getAddress();
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
	public void raiseRequest(ServiceTrackerBean request) throws BankingException {
		
		/*
		 * DML operations cannot happen without transactions management
		 * here we r doing declarative transaction handling instead of programmatic
		 * this is done in the service layer
	 	 */
		request.setRaisedDate(java.sql.Date.valueOf(LocalDate.now()));
		manager.persist(request);
	}

	@Override
	public List<Long> getAccNoList(int userId) throws BankingException {
		List<Long> accList = new ArrayList<>();
		String strQry = "SELECT t.accountId FROM AccountMasterBean t WHERE t.userId=:uId";
		TypedQuery<Long> qry = manager.createQuery(strQry, Long.class);
		qry.setParameter("uId", userId);
		accList = (ArrayList<Long>)qry.getResultList();
		return accList;
	}
	
	@Override
	public ArrayList<ServiceTrackerBean> trackRequest(int requestId, long accNo)
			throws BankingException {
		ArrayList<ServiceTrackerBean> tList = new ArrayList<>();
		
		if(accNo==0){
			
			tList.add(manager.find(ServiceTrackerBean.class, requestId));
			
		}else{
			
			String strQry = "SELECT t FROM ServiceTrackerBean t WHERE t.accountId=:acc AND ((:dat-t.raisedDate)<=180) ORDER BY t.raisedDate)";
			TypedQuery<ServiceTrackerBean> qry = manager.createQuery(strQry, ServiceTrackerBean.class);
			qry.setParameter("acc", accNo);
			qry.setParameter("dat", java.sql.Date.valueOf(LocalDate.now()));
			tList = (ArrayList<ServiceTrackerBean>)qry.getResultList();
			
		}
		return tList;
	}
	
	
	@Override // to add payee to beneficiary list
	public void addPayee(PayeeBean payee) throws BankingException {
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
		
		
		AccountMasterBean acc = manager.find(AccountMasterBean.class, trans.getAccountId());
		acc.setAccountBalance(acc.getAccountBalance()-trans.getTransactionAmount());
		
	}
	
	

	public void updateAccBalOfPayee(long transAmount,long payeeAccNo) throws BankingException
	{
		
		AccountMasterBean acc = manager.find(AccountMasterBean.class, payeeAccNo);
		acc.setAccountBalance(acc.getAccountBalance()+transAmount);
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
		String qry="SELECT a.accountId FROM AccountMasterBean a WHERE a.userId=?";
		TypedQuery<Long> query = manager.createQuery(qry, Long.class);
		query.setParameter(1,userId);
		List<Long> idList=query.getResultList();
		System.out.println(idList);
		return idList;
		
	}

	
	@Override
	public boolean validateTransPassword(int userId,String UserEnteredpasswd) throws BankingException {
		String qry="SELECT transPassword FROM UserBean WHERE userId=?";
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
		String qry="SELECT accountBalance FROM AccountMasterBean  WHERE accountId=?";
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
		String qry=("SELECT accountId FROM AccountMasterBean");
			TypedQuery<Long> query = manager.createQuery(qry, Long.class);
			List<Long> payeeList=query.getResultList();
		System.out.println(payeeList);
			if(payeeList.contains(payeeAccNo)){
		return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean validatePayeeSelf(long payeeAccNo, long accNo)
			throws BankingException {
		String qry=("SELECT payeeAccountId FROM PayeeBean WHERE accountId=?");
		TypedQuery<Long> query = manager.createQuery(qry, Long.class);
		query.setParameter(1,accNo);
		List<Long> payeeList=query.getResultList();
		System.out.println(payeeList);
		System.out.println(payeeAccNo);
		if(payeeList.contains(payeeAccNo))
		{
			System.out.println("true");
		    return true;
			
		}
		else
		{
			System.out.println("fasle");
			return false;
			
		}
	}
}
	
