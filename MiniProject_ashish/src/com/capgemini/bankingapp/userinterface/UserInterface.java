package com.capgemini.bankingapp.userinterface;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.bankingapp.dao.BankingDao1;
import com.capgemini.bankingapp.dao.BankingDao1Impl;
import com.capgemini.bankingapp.exception.CustomException;

public class UserInterface {

	public static void main(String[] args) throws CustomException {
		
		int choice=0;
		BankingDao1 dao ;
		do{
			System.out.println("Menu");
			System.out.println("1. View Mini Statement");
			System.out.println("2. View Detailed Statement");
			System.out.println("3. Change Address");
			System.out.println("4. Exit");
			
			Scanner scan  = new Scanner(System.in);
		    choice =  scan.nextInt();
			
		    switch(choice){
		    case 1:{
		    	System.out.println("Enter A/C No");
		    	int acNo  = scan.nextInt();
		    	dao = new BankingDao1Impl();
		    	ArrayList tList = new ArrayList();
		    	tList = dao.getMiniTransactionDetails(acNo);
		    	
		    	for(Object var:tList)
		    		System.out.println(var);
		    	break;
		    }
		    case 2:{
		    //	SimpleDateFormat myFormat = new SimpleDateFormat("dd/mm/yyyy"); 
		    	System.out.println("Enter A/C No");
		    	int acNo  =scan.nextInt();
		    	System.out.println("Enter start date");
		    	String str1 = scan.next();
		    	LocalDate dt1 = null;
		    	LocalDate dt2 = null;
		    	dt1 = LocalDate.parse(str1);
		    	System.out.println("Enter enddate");
		    	String str2 = scan.next();
		    	dt2 = LocalDate.parse(str2);
		    	
		    	dao = new BankingDao1Impl();
		    	ArrayList tList = new ArrayList();
		    	tList = dao.getDetailedTransactionDetails(acNo, dt1, dt2);
		    	
		    	for(Object var:tList)
		    		System.out.println(var);
		    	break;
		    }
		    
		    case 3:{
			   
			    	dao = new BankingDao1Impl();
			    	System.out.println("Enter the Account_id");
			    	int accId = scan.nextInt();
			    	System.out.println("Enter new address");
			    	String address = scan.next();
			    	System.out.println(address);
			    	System.out.println(accId);
			    	int succ = dao.updateAddress(accId, address);
			    	if(succ == 1)
			    		System.out.println("Successfully changed");
			    	
			    }
		    }
		}while(choice!=2);	
	  }
}
