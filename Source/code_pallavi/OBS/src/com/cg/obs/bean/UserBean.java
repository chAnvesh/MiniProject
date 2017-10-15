package com.cg.obs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="USER_TABLE")
public class UserBean {

	private long accNo;
	private int userId;
	private String loginPassword;
	private String secretQstn;
	private String transPassword;
	private int lockStatus;
	
	public UserBean() {
			
	}
	
	public UserBean(long accNo, int userId, String loginPassword,
					String secretQstn, String transPassword, int lockStatus) {
	super();
	this.accNo = accNo;
	this.userId = userId;
	this.loginPassword = loginPassword;
	this.secretQstn = secretQstn;
	this.transPassword = transPassword;
	this.lockStatus = lockStatus;	
	}

	public UserBean(int userId, String loginPassword) {
		super();
		this.userId = userId;
		this.loginPassword = loginPassword;
	}
	@Id
	@Column(name="ACCOUNT_ID")
	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="LOGIN_PASSWORD")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name="SECRETE_QUESTION")
	public String getSecretQstn() {
		return secretQstn;
	}

	public void setSecretQstn(String secretQstn) {
		this.secretQstn = secretQstn;
	}
	@Column(name="TRANSACTION_PASSWORD")
	public String getTransPassword() {
		return transPassword;
	}

	public void setTransPassword(String transPassword) {
		this.transPassword = transPassword;
	}
	@Column(name="LOCK_STATUS")
	public int getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(int i) {
		this.lockStatus = i;
	}

	@Override
	public String toString() {
		return "UserBean [accNo=" + accNo + ", userId=" + userId
				+ ", loginPassword=" + loginPassword + ", secretQstn="
				+ secretQstn + ", transPassword=" + transPassword
				+ ", lockStatus=" + lockStatus + "]";
	}
	
	

}
