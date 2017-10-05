package com.cg.onlineBanking.bean;

import java.time.LocalDate;

public class AccountMasterBean {

	
	private long accNo;
	private String accType;
	private int accBal;
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


	public int getAccBal() {
		return accBal;
	}


	public void setAccBal(int accBal) {
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
		return "AccountMasterBean [accNo=" + accNo + ", accType=" + accType
				+ ", accBal=" + accBal + ", date=" + date + "]";
	}


	public AccountMasterBean(long accNo, String accType, int accBal,
			LocalDate date) {
		super();
		this.accNo = accNo;
		this.accType = accType;
		this.accBal = accBal;
		this.date = date;
	}


	public AccountMasterBean() {
		
	}

}
