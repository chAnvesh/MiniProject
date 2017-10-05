package com.cg.onlineBanking.bean;

import java.util.Date;

public class TransactionBean {

	private int transactionId;
	private String transactionDesc;
	private Date transactionDate;
	private String transactionType;
	private float transactionAmount;
	private long accNo;
	
	public TransactionBean() {
		// TODO Auto-generated constructor stub
	}

	public TransactionBean(int transactionId, String transactionDesc,
			Date transactionDate, String transactionType,
			float transactionAmount, long accNo) {
		super();
		this.transactionId = transactionId;
		this.transactionDesc = transactionDesc;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.accNo = accNo;
	}

	public TransactionBean(String transactionDesc, Date transactionDate,
			String transactionType, float transactionAmount, long accNo) {
		super();
		this.transactionDesc = transactionDesc;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.accNo = accNo;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	@Override
	public String toString() {
		return "TransactionBean [transactionId=" + transactionId
				+ ", transactionDesc=" + transactionDesc + ", transactionDate="
				+ transactionDate + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", accNo="
				+ accNo + "]";
	}
	
	
}
