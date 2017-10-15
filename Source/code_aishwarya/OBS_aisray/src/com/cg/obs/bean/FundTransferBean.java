package com.cg.obs.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="FUNDS_TRANFER")
public class FundTransferBean {

	private int transferId;
	private long accNo;
	private long payeeAccNo;
	private Date transferDate;
	private float transferAmount;
	
	public FundTransferBean() {
		// TODO Auto-generated constructor stub
	}

	public FundTransferBean(long accNo, long payeeAccNo, Date transferDate,
			float transferAmount) {
		super();
		this.accNo = accNo;
		this.payeeAccNo = payeeAccNo;
		this.transferDate = transferDate;
		this.transferAmount = transferAmount;
	}

	@Id
	@Column(name="FUNDTRANSFER_ID")
	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	@Column(name="ACCOUNT_ID")
	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	@Column(name="PAYEE_ACCOUNT_ID")
	public long getPayeeAccNo() {
		return payeeAccNo;
	}

	public void setPayeeAccNo(long payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}

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
		return "FundTransferBean [transferId=" + transferId + ", accNo="
				+ accNo + ", payeeAccNo=" + payeeAccNo + ", transferDate="
				+ transferDate + ", transferAmount=" + transferAmount + "]";
	}

	
	
}
