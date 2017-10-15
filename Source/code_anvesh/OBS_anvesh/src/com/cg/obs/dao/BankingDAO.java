package com.cg.obs.dao;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;

@Repository
public class BankingDAO implements IBankingDAO{

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public ArrayList<Integer> getUserIdList() throws BankingException {
		String strQry = "SELECT u.userId FROM UserBean u";
		TypedQuery<Integer> qry = manager.createQuery(strQry, Integer.class);
		ArrayList<Integer> list = (ArrayList<Integer>)qry.getResultList();
		System.out.println("in banking dao");
		return list;
		
	}

	@Override
	public UserBean getUserDetailsOnId(int userId)
			throws BankingException {
		return manager.find(UserBean.class, userId);
	}

	
	//method for forgot password
	@Override
	public void forgotPassword(int userId) throws BankingException {
		
		UserBean user = manager.find(UserBean.class, userId);
		user.setLoginPassword("sbq500#");
	}
}
