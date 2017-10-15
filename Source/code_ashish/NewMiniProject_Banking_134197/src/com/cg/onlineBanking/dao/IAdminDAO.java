package com.cg.onlineBanking.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.CustomerBean;
import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;

public interface IAdminDAO {

	public void addAccount(AccountMasterBean bean) throws BankingException;
}
