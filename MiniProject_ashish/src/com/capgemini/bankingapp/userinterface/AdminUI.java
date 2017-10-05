package com.capgemini.bankingapp.userinterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.capgemini.bankingapp.beans.AccountMasterBean;
import com.capgemini.bankingapp.beans.CustomerBean;
import com.capgemini.bankingapp.beans.UserBean;
import com.capgemini.bankingapp.dao.BankingDao1;
import com.capgemini.bankingapp.dao.BankingDao1Impl;
import com.capgemini.bankingapp.exception.CustomException;

public class AdminUI {

	public static void main(String[] args) throws CustomException {
		
		int choice=0;
		BankingDao1 dao = new BankingDao1Impl() ;
		do{
			System.out.println("Menu");
			System.out.println("1. Add New Account");
			System.out.println("2. Exit");
			
			Scanner scan  = new Scanner(System.in);
		    choice =  scan.nextInt();
			
		    switch(choice){
		    case 1:{
		    	System.out.println("Enter UserId");
		    	int userId = scan.nextInt();
		    	System.out.println("Enter Name");
		    	String name = scan.next();
		    	System.out.println("Enter Address");
		    	String address = scan.next();
		    	System.out.println("Enter EmailId");
		    	String emailId = scan.next();
		    	System.out.println("Enter Pan");
		    	String pan = scan.next();
		    	System.out.println("Enter Account Type");
		    	String acctype =scan.next();
		    	System.out.println("Opening Balance");
		    	int balance =scan.nextInt();
		    	
		    	Random rnd = new Random();
		    	long accNo= 100000 + rnd.nextInt(900000);
		    	
		    	LocalDate dt = LocalDate.now();
		    	
		    	CustomerBean cust = new CustomerBean(accNo, name, emailId, address,pan );
		    	System.out.println(cust);
		    	boolean x = dao.updateCustomerTable(cust);
		    	
		    	AccountMasterBean acc = new AccountMasterBean(accNo, acctype, balance, dt);
		    	dao.updateAccountMaster(acc);
		    	
		    	UserBean user = dao.getUserDetails(userId);
		    	
		    	dao.updateUsertable(user, accNo);
		    	
		    	
		    }
		    }
		}while(choice!=2);	

	}

}
