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
@Table(name="FUND_TRANSFER")
public class FundTransferBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int transferId;
	private long accountId;
	private long payeeAccountId;
	private Date transferDate;
	private float transferAmount;
	
	public FundTransferBean() {
		// TODO Auto-generated constructor stub
	}

	public FundTransferBean(int transferId, long accountId,
			long payeeAccountId, Date transferDate, float transferAmount) {
		super();
		this.transferId = transferId;
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.transferDate = transferDate;
		this.transferAmount = transferAmount;
	}

	public FundTransferBean(long accNo, long payeeAccNo, Date date,
			long transAmt) {
		this.accountId = accNo;
		this.payeeAccountId = payeeAccNo;
		this.transferDate = date;
		this.transferAmount = transAmt;
	}

	@Id
	@Column(name="FUND_TRANSFER_ID")
	@SequenceGenerator(name="transferIdSeq",sequenceName="FUND_TRANSFER_ID_GEN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transferIdSeq")
	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	@Column(name="ACCOUNT_ID")
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name="PAYEE_ACCOUNT_ID")
	public long getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_TRANSFER")
	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	@Column(name="TRANSFER_AMOUNT")
	public float getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(float transferAmount) {
		this.transferAmount = transferAmount;
	}

	@Override
	public String toString() {
		return "FundTransferBean [transferId=" + transferId + ", accountId="
				+ accountId + ", payeeAccountId=" + payeeAccountId + ", transferDate="
				+ transferDate + ", transferAmount=" + transferAmount + "]";
	}
	
}
