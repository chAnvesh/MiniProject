package com.cg.onlineBanking.bean;

public class PayeeBean {

	private long accNo;
	private long payeeAccNo;
	private String payeeName;
	
	
	public PayeeBean() {
		// TODO Auto-generated constructor stub
	}


	public PayeeBean(long accNo, long payeeAccNo, String payeeName) {
		super();
		this.accNo = accNo;
		this.payeeAccNo = payeeAccNo;
		this.payeeName = payeeName;
	}


	public PayeeBean(Long payeeAccNo2, String payeeName2) {

		super();
		this.payeeAccNo = payeeAccNo;
		this.payeeName = payeeName;
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


	public String getPayeeName() {
		return payeeName;
	}


	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}


	@Override
	public String toString() {
		return "PayeeBean [accNo=" + accNo + ", payeeAccNo=" + payeeAccNo
				+ ", payeeName=" + payeeName + "]";
	}

		
}
