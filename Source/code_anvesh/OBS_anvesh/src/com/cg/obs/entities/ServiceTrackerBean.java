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
@Table(name="SERVICE_TRACKER")
public class ServiceTrackerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int serviceId;
	private String description;
	private long accountId;
	private Date raisedDate;
	private String status;
	
	public ServiceTrackerBean() {
		// TODO Auto-generated constructor stub
	}
	public ServiceTrackerBean(int serviceId, String description,
			long accountId, Date raisedDate, String status) {
		super();
		this.serviceId = serviceId;
		this.description = description;
		this.accountId = accountId;
		this.raisedDate = raisedDate;
		this.status = status;
	}

	@Id
	@Column(name = "SERVICE_ID")
	@SequenceGenerator(name="serviceIdSeq",sequenceName="SERVICE_ID_GEN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="serviceIdSeq")
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	@Column(name = "SERVICE_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "ACCOUNT_ID")
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SERVICE_RAISED_DATE")
	public Date getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(Date raisedDate) {
		this.raisedDate = raisedDate;
	}

	@Column(name = "SERVICE_STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ServiceTrackerBean [serviceId=" + serviceId + ", description="
				+ description + ", accountId=" + accountId + ", raisedDate="
				+ raisedDate + ", status=" + status + "]";
	}

}
