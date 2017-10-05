package com.cg.onlineBanking.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.BankingService;
import com.cg.onlineBanking.service.IBankingService;
import com.cg.onlineBanking.service.IUserService;
import com.cg.onlineBanking.service.UserService;

public class UserConsole {
	
	private UserBean user;
	private IUserService service;
	public UserConsole(UserBean user) {
		this.user = user; 
		service = new UserService();
	}
 
	public void stop(){
		System.out.println("Bye");
	}
	public boolean start() {
		
		boolean res=true;
		/*System.out.println("Running succesfully");
		System.out.println(user);
		*/
		Scanner sc=new Scanner(System.in);
		int choice;
		boolean flag = false;
		int exitChoice=0;
		// all variables
		//for requests & tracking
		long accNo=0;
		int serviceId = 0;
		String desc = null;
		String reqDate = null;
		ArrayList<ServiceTrackerBean> beanList = new ArrayList<>();
		ServiceTrackerBean bean;
		//
		
		
		System.out.println("Welcome to ABC Banking Services");
		System.out.println("Your account details are as follows:");
		
		try {
			for(AccountMasterBean list:service.getAccountDetails(user.getUserId())){
			System.out.println(list);
			}
		} catch (BankingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*System.out.println("Please enter the account number to handle operations.");
		long accNo = sc.nextLong();*/
		
		do {
			System.out.println("1.View statements");
			System.out.println("2.Chage communication details");
			System.out.println("3.Request for Cheque book");
			System.out.println("4.Track service request");
			System.out.println("5.Fund Transfer");
			System.out.println("6.Change password");
			System.out.println("7.Exit");
		
			System.out.println("Please enter the account number to handle operations.");
			accNo = sc.nextLong();
			
			System.out.println("Pick a choice");
			choice = sc.nextInt();
			
			switch(choice){
			
			case 1:{
			
				
				break;
			}
			case 2:{
				
				break;
			}
			case 3:{
	
				//IBankingService serv = null;
	
				System.out.println("Raise Request");
				try {
					System.out.println("Enter Description");
					desc = sc.next();
					
					System.out.println("Enter Date");
					reqDate = sc.next();
					
					
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate req = LocalDate.parse(reqDate, format);
					//convert reqDate to LocalDate type
					bean = new ServiceTrackerBean(desc,req,accNo);
					//bean.setAccNo(123);
					//service = new BankingService();
					serviceId = service.raiseRequest(bean);
					
				} catch (BankingException e) {
					e.getMessage();
				}
				System.out.println("Your request is succesfuly regiseterd.Your service request ID is "+serviceId);
				System.out.println("Do you wish to continue? [1]Yes/[0]No");
				exitChoice=sc.nextInt();
				if (exitChoice==1) {
					break;
				} else {
					choice=7;
				}
				
				break;
			}
			case 4:{
				
				System.out.println("Track Request");
				int cho;
				
					System.out.println("1. Track Request by Request ID");
					System.out.println("2. Track Request by Account No");
					System.out.println("3. Exit");
					System.out.println("Enter your choice");
					cho = sc.nextInt();
					
					switch(cho) {
					
					case 1: {
						System.out.println("Track by Req ID");
						accNo = 0;
						try {
							System.out.println("Enter your Request ID : ");
							serviceId = sc.nextInt();
							beanList = service.trackRequest(serviceId, accNo);
							
							System.out.println(beanList);
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Do you wish to continue? [1]Yes/[0]No");
						exitChoice=sc.nextInt();
						if (exitChoice==1) {
							break;
						} else {
							choice=7;
						}
						
						break;
					}
					case 2: {
						System.out.println("Track by Account No");
						serviceId = 0;
						try {
							System.out.println("Enter your Account No");
							accNo = sc.nextLong();
							beanList = service.trackRequest(serviceId, accNo);
							
							for(ServiceTrackerBean list:beanList){
							System.out.println(list);
							}
							//cho=1;
							
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Do you wish to continue? [1]Yes/[0]No");
						exitChoice=sc.nextInt();
						if (exitChoice==1) {
							break;
						} else {
							System.out.println("Hello"+choice);
							choice=7;
						}
						
					}
				}
				//break;		
			}
			
			case 5:{
				
				break;
			}
			case 6:{
				do {
				System.out.println("Change Your Password");
				System.out.println("Please enter your Old password");
				String old = sc.next();
				if(old.equals(user.getLoginPassword())) {
					System.out.println("Please enter your new password (Maximum 15 characters)");
					String newpass = sc.next();
					System.out.println("Please re-enter your new password");
					String confpass = sc.next();
					
					if(newpass.equals(confpass)) {
							try {
							flag = service.changePassword(confpass,user.getUserId());
							//choice = 7;
							res=false;
							System.out.println("Please login with your new credentials");
							//NewUI ui = new NewUI();
							
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					}
					else {
						flag = false;
					}
				} else {
					flag = false;
				}
				}while(!flag);
			
				break;
			}
			
			default:{
				System.out.println("Thanks for visiting");
				//stop();
				break;
			}
	
		}

		}while(choice!=7);
	
		return res;
	}
}
