package com.cg.onlineBanking.ui;

import java.util.Scanner;

import com.cg.onlineBanking.bean.NewUserBean;
import com.cg.onlineBanking.bean.UserBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.IUserService;
import com.cg.onlineBanking.service.UserService;

public class FrontUI {

	public static void main(String[] args) {
		
			Scanner sc = new Scanner(System.in);
			int choice = -1;
			
			IUserService userService = new UserService();
			
			int userId;
			String password;
			String role;
			
			System.out.println("Please pick a choice");
			System.out.println("1. SignIn");
			System.out.println("2. Create New Account");
			System.out.println("3. Exit");
			
			choice = sc.nextInt();
			
			switch(choice){
			
			case 1:{
				
				//boolean isExist =false;
				UserBean user ;
				UserBean resultUser=new UserBean() ;
				boolean flagu=false;
				boolean flagp=false;
				do{
					System.out.println("Enter your UserName");
					userId= sc.nextInt();
					
					System.out.println("Enter your password");
					password= sc.next();
					
						user= new UserBean(userId,password);
						
						try {
							
							resultUser= userService.validateCredentials(user);
							if(resultUser.getUserId()==0){
								flagu=false;
								System.out.println("UserId doesn't exists.");
							}
							else if(resultUser.getLoginPassword()==null){
								flagp = false;
								System.out.println("Password doesn't match.");
							}
							else {
								flagu=true;
								flagp=true;
							}

						}catch (BankingException e) {
						System.out.println("asdfghj");

						}
					}while(!(flagu||flagp));

					UserConsole uConsole = new UserConsole(resultUser);
					uConsole.start();
				break;
			}
			
			case 2:{
				
				
				AdminConsole aConsole = new AdminConsole();
				aConsole.start();
				
			}
			
			}

}
}