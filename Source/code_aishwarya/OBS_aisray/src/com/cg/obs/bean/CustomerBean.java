package com.cg.obs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CUSTOMER")
public class CustomerBean {

	private long accNo;
	private String custName;
	private String email;
	private String address;
	private String panCard;
	
	public CustomerBean() {
		// TODO Auto-generated constructor stub
	}

	public CustomerBean(String custName, String email, String address
	) {
		super();
		this.custName = custName;
		this.email = email;
		this.address = address;
	
	}
	
	
	public CustomerBean(String custName, String email, String address,
			String panCard) {
		super();
		this.custName = custName;
		this.email = email;
		this.address = address;
		this.panCard = panCard;
	}

	@Id
	@Column(name = "ACCOUNT_ID")
	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	@Column(name="CUSTOMER_NAME")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEamil(String email) {
		this.email = email;
	}

	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	@Override
	public String toString() {
		return "CustomerBean [accNo=" + accNo + ", custName=" + custName
				+ ", email=" + email + ", address=" + address + ", panCard="
				+ panCard + "]";
	}
	
	

}
