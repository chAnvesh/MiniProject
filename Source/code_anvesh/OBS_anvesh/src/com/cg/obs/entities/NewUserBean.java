package com.cg.obs.entities;

public class NewUserBean {

	private int userId;
	private String accountHolderName;
	private String address;
	private String emailId;
	private String accountType;
	private long openingBalance; 
	
	public NewUserBean() {
		// TODO Auto-generated constructor stub
	}

	public NewUserBean(int userId, String accountHolderName, String address,
			String emailId, String accountType,
			long openingBalance) {
		super();
		this.userId = userId;
		this.accountHolderName = accountHolderName;
		this.address = address;
		
		this.emailId = emailId;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}

	
}
