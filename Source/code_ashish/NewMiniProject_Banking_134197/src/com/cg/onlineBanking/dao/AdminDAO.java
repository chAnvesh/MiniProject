package com.cg.onlineBanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;








import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

@Repository
public class AdminDAO implements IAdminDAO {

	@PersistenceContext
	private EntityManager manager;
	private Connection connect ;
	
	
	
	public AdminDAO() {
	}



	@Override
	public void addAccount(AccountMasterBean bean) throws BankingException {
		manager.persist(bean); 
		
	}
	
	

	

}
