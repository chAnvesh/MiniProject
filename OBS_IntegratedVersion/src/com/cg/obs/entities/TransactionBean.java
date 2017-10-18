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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TRANSACTIONS")
public class TransactionBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int transactionId;
	private String transactionDesc;
	private Date transactionDate;
	private String transactionType;
	private long transactionAmount;
	private long accountId;
	
	public TransactionBean() {
		// TODO Auto-generated constructor stub
	}

	public TransactionBean(int transactionId, String transactionDesc,
			Date transactionDate, String transactionType,
			long transactionAmount, long accountId) {
		super();
		this.transactionId = transactionId;
		this.transactionDesc = transactionDesc;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.accountId = accountId;
	}

	public TransactionBean( String transactionDesc,
			Date transactionDate, String transactionType,
			long transactionAmount, long accountId) {
		super();
		
		this.transactionDesc = transactionDesc;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.accountId = accountId;
	}

	
	@Id
	@Column(name="TRANSACTION_ID")
	@SequenceGenerator(name="trSeq",sequenceName="TRANSACTION_ID_GEN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="trSeq")
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	@Column(name="TRANSACTION_DESCRIPTION")
	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_TRANSACTION")
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Column(name="TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Column(name="TRANS_AMOUNT")
	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	@Column(name="ACCOUNT_ID")
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "TransactionBean [transactionId=" + transactionId
				+ ", transactionDesc=" + transactionDesc + ", transactionDate="
				+ transactionDate + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", accountId="
				+ accountId + "]";
	}
	
	
}
