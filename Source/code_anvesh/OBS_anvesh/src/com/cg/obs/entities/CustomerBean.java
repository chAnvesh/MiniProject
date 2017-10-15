package com.cg.obs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CUSTOMER")
public class CustomerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String custName;
	private String email;
	private String address;
	private String panCard;
	
	public CustomerBean() {
		// TODO Auto-generated constructor stub
	}

	
	public CustomerBean(int userId, String custName, String email,
			String address, String panCard) {
		super();
		this.userId = userId;
		this.custName = custName;
		this.email = email;
		this.address = address;
		this.panCard = panCard;
	}

	@Id
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="PANCARD")
	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	@Override
	public String toString() {
		return "CustomerBean [userId=" + userId + ", custName="
				+ custName + ", email=" + email + ", address=" + address
				+ ", panCard=" + panCard + "]";
	}
}