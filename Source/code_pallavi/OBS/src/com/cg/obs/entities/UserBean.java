package com.cg.obs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
public class UserBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String loginPassword;
	private String secretQstn;
	private String secretAns;
	private String transPassword;
	private int lockStatus;
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	
	public UserBean(int userId, String loginPassword,
					String secretQstn,String secretAns, String transPassword, int lockStatus) {
	super();
	this.userId = userId;
	this.loginPassword = loginPassword;
	this.secretQstn = secretQstn;
	this.secretAns = secretAns;
	this.transPassword = transPassword;
	this.lockStatus = lockStatus;	
	}

	public UserBean(int userId, String loginPassword) {
		super();
		this.userId = userId;
		this.loginPassword = loginPassword;
	}
	
	@Id
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

	@Column(name="SECRET_QUESTION")
	public String getSecretQstn() {
		return secretQstn;
	}

	public void setSecretQstn(String secretQstn) {
		this.secretQstn = secretQstn;
	}
	
	@Column(name="SECRET_ANSWER")
	public String getSecretAns() {
		return secretAns;
	}

	public void setSecretAns(String secretAns) {
		this.secretAns = secretAns;
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

	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId
				+ ", loginPassword=" + loginPassword + ", secretQstn="
				+ secretQstn + ",secretAns="
				+ secretAns + ", transPassword=" + transPassword
				+ ", lockStatus=" + lockStatus + "]";
	}

}