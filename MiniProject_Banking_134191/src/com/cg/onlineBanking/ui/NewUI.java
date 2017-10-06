package com.cg.onlineBanking.ui;

import java.util.Scanner;

import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.BankingService;
import com.cg.onlineBanking.service.IBankingService;
import com.cg.onlineBanking.service.IUserService;
import com.cg.onlineBanking.service.UserService;

public class NewUI {
public static boolean stat;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int userId =0;
		String password;
		IUserService userService = new UserService(); 
		IBankingService bankService = null;
		try {
			bankService = new BankingService();
		} catch (BankingException e1) {
			
			e1.printStackTrace();
		}
		int loginAttempts=0;
		
		
		UserBean user ;
		UserBean resultUser=new UserBean();
		boolean flag=false;
		boolean flagu=false;
		boolean flagp=false;
		boolean flagUser=false;
		boolean flagAdmin=false;
		
		System.out.println("Welcome to Banking Application");
		
		do{
		int value;
		System.out.println("Please enter your choice:");
		System.out.println("1. login");
		System.out.println("2. Forgot Password");
		value= sc.nextInt();
		switch(value){
		case 1 :{
			
				
				System.out.println("Please enter your UserId");
				if (sc.hasNextInt()) {
					userId= sc.nextInt();
				} else {
					System.out.println("Please enter User Id in valid format(Numericals)");
					sc.next();
					continue;
				}
				
				System.out.println("Please enter your Password");
				password = sc.next();
				loginAttempts++;
				
				//System.out.println(loginAttempts);
				
					user= new UserBean(userId,password);
					
					if(userId==5&&"admin".equals(password)){
						flagAdmin =true;
						flagUser = false;
						AdminConsole aConsole = new AdminConsole();
						aConsole.start();
					}else{
						flagUser = true;
						flagAdmin = false;
						try {
							
							resultUser= bankService.validateCredentials(user);
							if(resultUser.getUserId()==0){
								flagu=false;
								flagUser=flagu&&flagp;
								System.out.println("UserId doesn't exists.");
								if(loginAttempts>=3){
									System.out.println("Login limit exceeded");
								}
							}
							else if(resultUser.getLoginPassword()==null){
								flagp = false;
								flagUser=flagu&&flagp;
								System.out.println("Password doesn't match.");
								if(loginAttempts>=3){
									System.out.println("Login limit exceeded");
								}
							}
							else {
								flagu=true;
								flagp=true;
								//flagUser=flagu&&flagp;
								UserConsole uConsole = new UserConsole(resultUser);
								flagUser = uConsole.start();
								//System.out.println(stat);
							}
							
							flag=flagAdmin||flagUser||loginAttempts>=3;
		
						}catch (BankingException e) {
						System.out.println("asdfghj");
						}
					}
					//System.out.println(flag);
				//}while(!flag);
			break;
		}
		case 2 :{
			System.out.println("Please enter your user Id:");
			userId = sc.nextInt();
			
			System.out.println("Please enter your Seceret Question:");
			String secretQstn = sc.next();
			
			boolean marker = false;
			try {
				marker = bankService.forgotPassword(userId, secretQstn);
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(marker==false){
				System.out.println("Sorry you entered wrong Seceret Question");
			}
			else{
				System.out.println("Your password is set to 'sbq500#' Please login again and change your password");
			}
			break;
		}
	
		}
		}while(!flag);
		
		
	
}
				
}

/*if(flag){
				AdminConsole aConsole = new AdminConsole();
				aConsole.start();
			}else if(!flag&&flagu&&flagp)
			{
				UserConsole uConsole = new UserConsole(resultUser);
				uConsole.start();
			}
			else{
				System.out.println("Application stopped");
			}*/
			
	
	
