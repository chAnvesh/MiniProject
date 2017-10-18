package com.cg.obs.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT_MASTER")
public class AccountMasterBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long accountId;
	private int  userId;
	private String accountType;
	private long accountBalance;
	private Date openDate;
	
	public AccountMasterBean() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountMasterBean(long accountId, int userId, String accountType,
			long accountBalance, Date openDate) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.openDate = openDate;
	}
	
	public AccountMasterBean( int userId, String accountType,
			long accountBalance, Date openDate) {
		super();
		this.userId = userId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.openDate = openDate;
	}
	
	@Id
	@SequenceGenerator(name="accIdSeq",sequenceName="ACCOUNT_ID_GEN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="accIdSeq")
	@Column(name="ACCOUNT_ID")
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="ACCOUNT_TYPE")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Column(name="ACCOUNT_BALANCE")
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	@Column(name="OPEN_DATE")
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	@Override
	public String toString() {
		return "AccountMasterBean [accountId=" + accountId + ", userId="
				+ userId + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + ", openDate=" + openDate + "]";
	}
	
}
	
	