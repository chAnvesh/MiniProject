package com.cg.obs.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;


@Repository
public class AdminDAO implements IAdminDAO {

	@PersistenceContext
	EntityManager manager;

	//Transactions methods
	@Override
	public ArrayList<TransactionBean> viewDailyTransactions(Date transDate) throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE t.transactionDate=:dt";
	
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("dt", transDate);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}
	
	public ArrayList<TransactionBean> viewMonthlyTransactions(String month, String year) throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE TO_CHAR(t.transactionDate,'MON')=:mon "
				+ "AND TO_CHAR(t.transactionDate,'YYYY')=:yr";

		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		qry.setParameter("mon", month);
		qry.setParameter("yr", year);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}

	@Override
	public ArrayList<TransactionBean> viewYearlyTransactions(String year) throws BankingException {
		String strQry = "SELECT t FROM TransactionBean t WHERE TO_CHAR(t.transactionDate,'YYYY')=:yr";
		
		TypedQuery<TransactionBean> qry = manager.createQuery(strQry, TransactionBean.class);
		
		qry.setParameter("yr", year);
		ArrayList<TransactionBean> list =(ArrayList<TransactionBean>)qry.getResultList();
		System.out.println(list);
		return list;
	}

	@Override
	public void addAccount(AccountMasterBean bean) throws BankingException {
		manager.persist(bean); 
		
	}
	
}
