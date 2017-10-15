package com.cg.obs.bean;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ACCOUNT_MASTER")
public class AccountMasterBean {

	
	private long accNo;
	private String accType;
	private long accBal;
	private LocalDate date;
	

	@Id
	@Column(name="ACCOUNT_ID")
	public long getAccNo() {
		return accNo;
	}


	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	@Column(name="ACCOUNT_TYPE")
	public String getAccType() {
		return accType;
	}


	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Column(name="ACCOUNT_BALANCE")
	public long getAccBal() {
		return accBal;
	}


	public void setAccBal(long accBal) {
		this.accBal = accBal;
	}

	@Column(name="OPEN_DATE")
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
