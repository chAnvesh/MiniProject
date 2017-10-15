package com.cg.obs.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SERVICE_TRACKER")
public class ServiceTrackerBean {

	private int serviceId;
	private String description;
	private long accNo;
	private Date raisedDate;
	private String status;
	
	public ServiceTrackerBean() {
		// TODO Auto-generated constructor stub
	}

	public ServiceTrackerBean(String description, long accNo, Date raisedDate,
			String status) {
		super();
		this.description = description;
		this.accNo = accNo;
		this.raisedDate = raisedDate;
		this.status = status;
	}

	@Id
	@Column(name = "SERVICE_ID")
	public int getServiceId() {
		return serviceId;
	}

	public ServiceTrackerBean(String description, Date reqDate, long accNo2) {
		super();
		this.description = description;
		this.raisedDate = reqDate;
		this.accNo = accNo2;
	}

	public ServiceTrackerBean(String desc, long accNo2) {
		this.description = description;
		//this.raisedDate = reqDate;
		this.accNo = accNo2;
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
	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

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
				+ description + ", accNo=" + accNo + ", raisedDate="
				+ raisedDate + ", status=" + status + "]";
	}

}
