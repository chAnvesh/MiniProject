package com.cg.onlineBanking.bean;

public class UserBean {

	private long accNo;
	private int userId;
	private String loginPassword;
	private String secretQstn;
	private String transPassword;
	private String lockStatus;
	
	public UserBean() {
			// TODO Auto-generated constructor stub
	}
	
	public UserBean(long accNo, int userId, String loginPassword,
					String secretQstn, String transPassword, String lockStatus) {
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

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getSecretQstn() {
		return secretQstn;
	}

	public void setSecretQstn(String secretQstn) {
		this.secretQstn = secretQstn;
	}

	public String getTransPassword() {
		return transPassword;
	}

	public void setTransPassword(String transPassword) {
		this.transPassword = transPassword;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Override
	public String toString() {
		return "UserBean [accNo=" + accNo + ", userId=" + userId
				+ ", loginPassword=" + loginPassword + ", secretQstn="
				+ secretQstn + ", transPassword=" + transPassword
				+ ", lockStatus=" + lockStatus + "]";
	}
	
	

}
