package com.capgemini.bankingapp.beans;

import java.util.Date;

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

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public long getPayeeAccNo() {
		return payeeAccNo;
	}

	public void setPayeeAccNo(long payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

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
