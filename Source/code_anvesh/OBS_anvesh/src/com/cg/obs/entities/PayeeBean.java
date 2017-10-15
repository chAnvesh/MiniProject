package com.cg.obs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PAYEE_TABLE")
public class PayeeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int serialId;
	private long accountId;
	private long payeeAccountId;
	private String payeeName;
	
	public PayeeBean() {
		// TODO Auto-generated constructor stub
	}

	public PayeeBean(long accountId, long payeeAccountId, String payeeName) {
		super();
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.payeeName = payeeName;
	}

	@Id
	@Column(name = "SERIAL_ID")
	@SequenceGenerator(name="serialIdSeq",sequenceName="SERIAL_ID_GEN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="serialIdSeq")
	public int getSerialId() {
		return serialId;
	}


	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	@Column(name = "ACCOUNT_ID")
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "PAYEE_ACCOUNT_ID")
	public long getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
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
		return "PayeeBean [accountId=" + accountId + ", payeeAccountId=" + payeeAccountId
				+ ", payeeName=" + payeeName + "]";
	}

}
