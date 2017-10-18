package com.cg.obs.dao;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

@Repository
public class BankingDAO implements IBankingDAO{

	@PersistenceContext
	private EntityManager manager;

	
	@Override
	public void setInitLockStatus() throws BankingException{
		String strQry = "SELECT u FROM UserBean u";
		TypedQuery<UserBean> qry = manager.createQuery(strQry, UserBean.class);
		
		ArrayList<UserBean> list = (ArrayList<UserBean>)qry.getResultList();
		
		for(UserBean ub:list){
			ub.setLockStatus(0);
		}
	}

	@Override
	public ArrayList<Integer> getUserIdList() throws BankingException {
		
		String strQry = "SELECT u.userId FROM UserBean u";
		TypedQuery<Integer> qry = manager.createQuery(strQry, Integer.class);
		ArrayList<Integer> list = (ArrayList<Integer>)qry.getResultList();
		
		return list;
		
	}

	@Override
	public UserBean getUserDetailsOnId(int userId) throws BankingException {
		return manager.find(UserBean.class, userId);
	}
	
	
	@Override
	public void forgotPassword(int userId) throws BankingException {
		UserBean user = manager.find(UserBean.class, userId);
		user.setLoginPassword("sbq500#");
		
	}

	@Override
	public CustomerBean getCustDetails(int userId) throws BankingException {
		CustomerBean custDet = manager.find(CustomerBean.class, userId);
		return custDet;
	}

}
