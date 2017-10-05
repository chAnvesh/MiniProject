package com.capgemini.bankingapp.beans;

public class UserBean {

		private long accNo;
		private int userId;
		private String password;
		private String qsn;
		private String transPass;
		private String lockStat;
		
		
		
		public UserBean(long accNo, int userId, String password, String qsn,
				String transPass, String lockStat) {
			super();
			this.accNo = accNo;
			this.userId = userId;
			this.password = password;
			this.qsn = qsn;
			this.transPass = transPass;
			this.lockStat = lockStat;
		}
		@Override
		public String toString() {
			return "UserBean [accNo=" + accNo + ", userId=" + userId
					+ ", password=" + password + ", qsn=" + qsn
					+ ", transPass=" + transPass + ", lockStat=" + lockStat
					+ "]";
		}
		public long getAccNo() {
			return accNo;
		}
		public void setAccNo(long accNo) {
			this.accNo = accNo;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getQsn() {
			return qsn;
		}
		public void setQsn(String qsn) {
			this.qsn = qsn;
		}
		public String getTransPass() {
			return transPass;
		}
		public void setTransPass(String transPass) {
			this.transPass = transPass;
		}
		public String getLockStat() {
			return lockStat;
		}
		public void setLockStat(String lockStat) {
			this.lockStat = lockStat;
		}
		
		
}
