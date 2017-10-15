package com.cg.obs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.cg.obs.dao.IAdminDAO;
import com.cg.obs.exception.BankingException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Resource
	IAdminDAO adminDAO;
	

	@Override
	public boolean validateDate(LocalDate transDate) throws BankingException {
		
		if(transDate.compareTo(LocalDate.now())<=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean validateMonth(String month) throws BankingException {
		
		return month.matches("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)");
	}
	

	
}
