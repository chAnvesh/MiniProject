package com.cg.onlineBanking.bean;

public class NewUserBean {

	private String accountHolderName;
	private String address;
	private String mobileNumber; 
	private String emailId;
	private String accountType;
	private String openingBalance; 
	private String secretQuestion;
	private String secretAnswer;

	public NewUserBean() {
		// TODO Auto-generated constructor stub
	}

	public NewUserBean(String accountHolderName, String address,
			String mobileNumber, String emailId, String accountType,
			String openingBalance, String secretQuestion, String secretAnswer) {
		super();
		this.accountHolderName = accountHolderName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	
}
