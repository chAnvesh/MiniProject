package com.cg.obs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PAYEE_TABLE")
public class PayeeBean {

	private int serialId;
	@Id
	@Column(name = "SERIAL_ID")
	public int getSerialId() {
		return serialId;
	}


	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}


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
		this.payeeAccNo = payeeAccNo;
		this.payeeName = payeeName;
	}


	@Column(name = "ACCOUNT_ID")
	public long getAccNo() {
		return accNo;
	}


	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}


	@Column(name = "PAYEE_ACCOUNT_ID")
	public long getPayeeAccNo() {
		return payeeAccNo;
	}


	public void setPayeeAccNo(long payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}

	
	
	@Column(name = "NICK_NAME")
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
