package com.cg.onlineBanking.bean;

import java.time.LocalDate;
import java.util.Date;

public class AccountMasterBean {

	
	private long accNo;
	private String accType;
	private long accBal;
	private LocalDate date;
	
	
	public long getAccNo() {
		return accNo;
	}


	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}


	public String getAccType() {
		return accType;
	}


	public void setAccType(String accType) {
		this.accType = accType;
	}


	public long getAccBal() {
		return accBal;
	}


	public void setAccBal(long accBal) {
		this.accBal = accBal;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}

	

	@Override
	public String toString() {
		return "Your "+accType+"account details are[accNo=" + accNo + ", accType=" + accType
				+ ", accBal=" + accBal + ", date=" + date + "]";
	}

	

	public AccountMasterBean(long accNo, String accType, long accBal,
			LocalDate date) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.accBal = accBal;
		this.date = date;
	}


	public AccountMasterBean(String accType, long accBal) {
		super();
		this.accType = accType;
		this.accBal = accBal;
	}


	public AccountMasterBean() {
		
	}


	public AccountMasterBean(long accNo, long accBal) {
		super();
		this.accNo = accNo;
		this.accBal = accBal;
	}


	public AccountMasterBean(long accNo, String accType, long accBal) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.accBal = accBal;
	}

	
}
