package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.dao.AdminDAO;
import com.cg.onlineBanking.dao.IAdminDAO;
import com.cg.onlineBanking.dao.IUserDAO;
import com.cg.onlineBanking.exception.BankingException;
@Service
@Transactional
public class AdminService implements IAdminService {

	@Resource
	IAdminDAO adminDAO;
	
	public AdminService() throws BankingException {
		adminDAO = new AdminDAO();
	}
	

	@Override
	public void addAccount(AccountMasterBean bean) throws BankingException {
		adminDAO.addAccount(bean);
		
	}


	


	
}
