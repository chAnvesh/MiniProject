package com.cg.onlineBanking.bean;

import java.time.LocalDate;
import java.util.Date;

public class ServiceTrackerBean {

	private int serviceId;
	private String description;
	private long accNo;
	private LocalDate raisedDate;
	private String status;
	
	public ServiceTrackerBean() {
		// TODO Auto-generated constructor stub
	}

	public ServiceTrackerBean(String description, long accNo, LocalDate raisedDate,
			String status) {
		super();
		this.description = description;
		this.accNo = accNo;
		this.raisedDate = raisedDate;
		this.status = status;
	}

	public int getServiceId() {
		return serviceId;
	}

	public ServiceTrackerBean(String description, LocalDate reqDate, long accNo2) {
		super();
		this.description = description;
		this.raisedDate = reqDate;
		this.accNo = accNo2;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public LocalDate getRaisedDate() {
		return raisedDate;
	}

	public void setRaisedDate(LocalDate raisedDate) {
		this.raisedDate = raisedDate;
	}

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
