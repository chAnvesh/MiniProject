package com.cg.onlineBanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IAdminService {

	public void addAccount(AccountMasterBean bean) throws BankingException;



}
