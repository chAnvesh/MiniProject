package com.cg.onlineBanking.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.AdminService;
import com.cg.onlineBanking.service.IAdminService;

public class AdminConsole {
	
	public void start() {
		
		Scanner sc = new Scanner(System.in);
		int adminChoice;
		IAdminService adminService;
		
		System.out.println("Welcome to Adminstrative Console");
		
		System.out.println("Please select one of the options");
		System.out.println("1.Add New Customer");
		System.out.println("2.View All daily Transactions");
		System.out.println("3.View All monthly Transactions");
		System.out.println("4.View All yearly Transactions");
		System.out.println("5.Exit");
		
		adminChoice = sc.nextInt();
		
		switch(adminChoice){
		
		/*case 1:{
			System.out.println("Please enter the following required details");
			
			String accountHolderName;
			String address;
			String mobileNumber; 
			String emailId;
			String accountType;
			String openingBalance; 
			String secretQuestion;
			String secretAnswer;
		
			System.out.println("Please enter your Name: ");
			accountHolderName = sc.next();
			System.out.println("Please enter your Address");
			address= sc.next();
			System.out.println("Please enter your Mobile Number: ");
			mobileNumber= sc.next();
			System.out.println("Please enter your EMail Id: ");
			emailId = sc.next();
			System.out.println("Please enter account type: ");
			accountType = sc.next();
			System.out.println("Please enter opening balance: ");
			openingBalance = sc.next();
			System.out.println("Please enter a Secret Question: ");
			secretQuestion = sc.next();
			System.out.println("Please enter Secret Answer: ");
			secretAnswer = sc.next();
			
			NewUserBean newUser = new NewUserBean(accountHolderName, address, mobileNumber, emailId, accountType,
					openingBalance, secretQuestion, secretAnswer);
			
		}*/
		
		case 2:{
			
			adminService = new AdminService();
			ArrayList<TransactionBean> list=null;
			boolean flag=false;
			LocalDate date;
			do{
				System.out.println("Please enter the required date for viewing transactions(yyyy-MM-dd)");
				String transDate =sc.next();
				
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(transDate, format);
				try {
					flag = adminService.validateDate(date);
				} catch (BankingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}while(!flag);
			
			try {
				list = adminService.viewDailyTransactions(date);
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(TransactionBean trans:list){
				System.out.println(trans);
			}
			
			System.out.println("Do you wish to continue?(Y/N)");
			String ch = sc.next();
			if("Y".equals(ch)){
				break;
			}else{
				adminChoice=5;
			}
		}
		
			case 3:{
			
				adminService = new AdminService();
				boolean monthValid = false;
				String month ;
				int year;
				
				ArrayList<TransactionBean> list=new ArrayList<>();
				
				do{
					System.out.println("Please enter the required month(eg., Aug)");
					month = sc.next();
					
					try {
						monthValid = adminService.validateMonth(month);
					} catch (BankingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!monthValid)
						System.out.println("Please enter valid month in the given format");
				}while(!monthValid);
				
				
				do{
					System.out.println("Please enter the required year for viewing transactions");
					year = sc.nextInt();
					
					if(year>2017)
						System.out.println("Please enter valid year");
				}while(year>2017);
				
				
				try {
					list = adminService.viewMonthlyTransactions(month,year);
				} catch (BankingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(TransactionBean trans:list){
					System.out.println(trans);
				}
				
				System.out.println("Do you wish to continue?(Y/N)");
				String flag = sc.next();
				if("Y".equals(flag)){
					break;
				}else{
					adminChoice=5;
				}
			}
				
			case 4:{
				
				adminService = new AdminService();
				ArrayList<TransactionBean> list=new ArrayList<>();
				int year;
				do{
					System.out.println("Please enter the required year for viewing transactions");
					year = sc.nextInt();
					
					if(year>2017)
						System.out.println("Please enter valid year");
				}while(year>2017);
				
				try {
					list = adminService.viewYearlyTransactions(year);
				} catch (BankingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(TransactionBean trans:list){
					System.out.println(trans);
				}
				
				System.out.println("Do you wish to continue?(Y/N)");
				String flag = sc.next();
				if("Y".equals(flag)){
					break;
				}else{
					adminChoice=5;
				}
			}
		
		}
	}
	
/*	private NewUserBean newUser;
	private IAdminService adminService;
	
	public AdminConsole() {
		adminService = new AdminService();
	}
	
	public AdminConsole(NewUserBean newUser) {
		super();
		this.newUser = newUser;
	}



	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	public long addCustomer(NewUserBean newUser) throws BankingException{
		
		long accNo = adminService.addCustomer(newUser);
		return accNo;
	}
*/	
	
	
}
