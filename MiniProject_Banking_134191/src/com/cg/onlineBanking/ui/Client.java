package com.cg.onlineBanking.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.cg.onlineBanking.bean.ServiceTrackerBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.BankingService;
import com.cg.onlineBanking.service.IBankingService;

public class Client {

	public static void main(String[] args) {
		
		int choice = 0;
		long accNo = 0;
		int serviceId = 0;
		String desc = null;
		String reqDate = null;
		ServiceTrackerBean bean = null;
		IBankingService serv = null;
		Scanner sc = new Scanner(System.in);
		do {

			System.out.println("1. Raise Request");
			System.out.println("2. Track Request");
			System.out.println("3. Exit");
			
			choice = sc.nextInt();
			switch(choice) {
			case 1: {
				System.out.println("Raise Request");
				try {
					System.out.println("Enter Description");
					desc = sc.nextLine();
					System.out.println("Enter Date");
					reqDate = sc.nextLine();
					LocalDate req = reqDate;
					//convert reqDate to LocalDate type
					bean = new ServiceTrackerBean(desc,req,accNo);
					serv = new BankingService();
					serviceId = serv.raiseRequest(bean);
					
				} catch (BankingException e) {
					
					e.getMessage();
				}
				System.out.println(serviceId);
				break;
			}
			case 2: {
				System.out.println("Track Request");
				int cho;
				do {
					
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
							bean = serv.trackRequest(serviceId, accNo);
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					case 2: {
						System.out.println("Track by Account No");
						serviceId = 0;
						try {
							System.out.println("Enter your Account No");
							accNo = sc.nextLong();
							bean = serv.trackRequest(serviceId, accNo);
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					}
					
				}while(cho!=3);
				
				
				break;
			}
			}
		}while(choice!=3);
	}
}