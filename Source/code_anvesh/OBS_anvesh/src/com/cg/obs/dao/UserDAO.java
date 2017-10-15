package com.cg.obs.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.obs.entities.AccountMasterBean;
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
		
		String strQry = "SELECT a FROM AccountMasterBean a where a.userId=:id";
		TypedQuery<AccountMasterBean> qry = manager.createQuery(strQry, AccountMasterBean.class);
		qry.setParameter("id", userId);
		ArrayList<AccountMasterBean> list = (ArrayList<AccountMasterBean>)qry.getResultList();
		
		return list;
	}

	@Override
	public void resetLockStatus(int userId) throws BankingException {
		UserBean user = manager.find(UserBean.class, userId);
		
		user.setLockStatus(0);
		
	}
}
	
