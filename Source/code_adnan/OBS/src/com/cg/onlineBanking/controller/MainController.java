package com.cg.onlineBanking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.onlineBanking.bean.FundTransferBean;
import com.cg.onlineBanking.bean.PayeeBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.IUserService;

@Controller
public class MainController {

	
	@Resource
	IUserService service;
	
	public MainController() {
		super();
		//session = request.getSession(true);
	}
	
	@RequestMapping("/getHomePage.do")
	public ModelAndView getHomePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("HomePage");
		return mAndV;
	}
	
	@RequestMapping("/getAdminLogin.do")
	public ModelAndView getAdminLoginPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AdminLogin");
		return mAndV;
	}
	
	@RequestMapping("/getAdminHome.do")
	public ModelAndView getAdminHomePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AdminHome");
		return mAndV;
	}
	
	@RequestMapping("/getUserLogin.do")
	public ModelAndView getUserLoginPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("UserLogin");
		return mAndV;
	}
	
	@RequestMapping("/getUserHome.do")
	public ModelAndView getUserHomePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("UserHome");
		return mAndV;
	}
	
	@RequestMapping("/getForgotPassword.do")
	public ModelAndView getForgotPasswordPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ForgotPassword");
		return mAndV;
	}
	
	
	
	
	
	//User Functionality pages
	@RequestMapping("/getViewStatement.do")
	public ModelAndView getViewStatementPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewStatement");
		return mAndV;
	}
	
	@RequestMapping("/getChangeDetails.do")
	public ModelAndView getChangeDetailsPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangeDetails");
		return mAndV;
	}
	
	@RequestMapping("/getRaiseRequest.do")
	public ModelAndView getRaiseRequestPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("RaiseRequest");
		return mAndV;
	}
	
	@RequestMapping("/getTrackRequest.do")
	public ModelAndView getTrackRequestPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("TrackRequest");
		return mAndV;
	}
	
	@RequestMapping("/getFundsTransfer.do")
	public ModelAndView getFundsTransferPage() throws BankingException {
		
		System.out.println("c");
		ModelAndView mAndV = new ModelAndView("SelectAccount");
		System.out.println("a");
		List<Long> accNoList = service.getAccountNoList(10001);
		System.out.println("b");
		System.out.println(accNoList);
		mAndV.addObject("accList",accNoList);
		return mAndV;
	}
	
	@RequestMapping("/getPayeeList.do")
	public ModelAndView getSelfTransferPage(HttpServletRequest request,@RequestParam("accNo") String accNumber,@RequestParam("transType") String transType, HttpSession session) throws BankingException {
		Long accNo=Long.parseLong(accNumber);
		session.setAttribute("transAccNo", accNo);
		System.out.println(accNo);
		//session.setAttribute("accNo", accNo);
		ModelAndView  mAndV ;
		if(transType.equals("SelfBank"))
		{
			mAndV = new ModelAndView("PayeeList");
			List payeeList = service.getAccountNoList(10001);
			System.out.println(payeeList);
			mAndV.addObject("payeeList", payeeList);
			mAndV.addObject("selfAccNo", accNo);
			
		}
		else{
		
			mAndV = new ModelAndView("PayeeListInter");
			List payeeList=service.showPayeeList(accNo);
			System.out.println(payeeList);
			mAndV.addObject("payeeList", payeeList);
		}
		
		return mAndV;
	}
	
	
	@RequestMapping("/getAddPayeePage.do")
	public ModelAndView getAddPayeePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AddPayee");
		PayeeBean payee= new PayeeBean();
		mAndV.addObject("PayeeBean", payee);
		return mAndV;
	}
	
		@RequestMapping("/validatePassAndAmt.do")
		public ModelAndView validatePassAndAmt(@RequestParam("accNo") String payeeAccNumber,@RequestParam("transAmount") String transAmount,@RequestParam("desc") String desc,@RequestParam("transPassword") String transPassword, HttpSession session) throws BankingException
		{
			long transAmt=Long.parseLong(transAmount);
			long payeeAccNo=Long.parseLong(payeeAccNumber);
	       // long accNo=(Long)session.getAttribute("accNo");
			 long accNo=(Long)session.getAttribute("transAccNo");
	        ModelAndView mAndV ;
			if(service.validateBalance(accNo, transAmt, 10001, payeeAccNo))
			{
				if(service.validateTransPassword(10001, transPassword))
				{
					java.util.Date date = java.sql.Date.valueOf(LocalDate.now());
					FundTransferBean fundTrans = new FundTransferBean(accNo,payeeAccNo,date,transAmt);
					TransactionBean credit = new TransactionBean(desc,date,"C",transAmt,payeeAccNo);
					TransactionBean debit = new TransactionBean(desc,date,"D",transAmt,accNo);
					service.addFundTransfer(fundTrans);
					service.addTransForCredit(credit, payeeAccNo);
					service.addTransForDebit(debit);
					
					mAndV=	new ModelAndView("Success");
					mAndV.addObject("transactionSuccess", "Transaction Is SuccessFull");
					
				}
				else
				{
					mAndV=	new ModelAndView("Error");
					mAndV.addObject("errMsg", "Password Invalid");
				}
				
			}
			else
			{
				mAndV=	new ModelAndView("Error");
				mAndV.addObject("errMsg", "Invalid Balance");
			}
						return mAndV;
		}
		
	/*@RequestMapping("/getInterTransfer.do")
	public ModelAndView getInterTransferPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("InterTransfer");
		return mAndV;
	}*/
	
	@RequestMapping(value="/getAddPayee.do", method=RequestMethod.POST)
	public ModelAndView getAddPayeePage(@ModelAttribute("PayeeBean") PayeeBean payee, HttpSession session) throws BankingException {
		//long accNo=Long.parseLong(request.getParameter("accNo"));
		//long accNo=2000000000;
		payee.setAccountId((Long)session.getAttribute("transAccNo"));
		ModelAndView mAndV;
		System.out.println(payee);
		if(service.validatePayeeAccountNo(payee.getPayeeAccountId()))
		{	
			
			if(service.getAccountNoList(10001).contains(payee.getPayeeAccountId()))
			{
				mAndV= new ModelAndView("Error");
				mAndV.addObject("errMsg", "Enter an Account number different from your's to be added as Payee");
			}
			else{
				
				System.out.println("in if maincontroller");
				  service.addPayee(payee);
				  mAndV = new ModelAndView("SuccesPayeeAddedPage");
				  mAndV.addObject("payeeBean",payee);					
			}
			
			
		}
		else
		{
		
			mAndV= new ModelAndView("Error");
			mAndV.addObject("errMsg", "Invalid Payee Account Number");
		}
		return mAndV;
	}
	
	
	@RequestMapping("/getChangePassword.do")
	public ModelAndView getChangePasswordPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangePassword");
		return mAndV;
	}
	
	
	
	
	
	
	//Admin Functionality pages
	@RequestMapping("/getAddNewAccount.do")
	public ModelAndView getAddNewAccountPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AddNewAccount");
		return mAndV;
	}
	
	@RequestMapping("/getViewAllTransactions.do")
	public ModelAndView getViewAllTransactionsPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewAllTransactions");
		return mAndV;
	}
	
	// Common pages
	@RequestMapping("/getHeader.do")
	public ModelAndView getHeaderPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("HeaderPage");
		return mAndV;
	}
	
	@RequestMapping("/getSuccess.do")
	public ModelAndView getSuccessPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("SuccessPage");
		return mAndV;
	}
	
	@RequestMapping("/getError.do")
	public ModelAndView getErrorPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ErrorPage");
		return mAndV;
	}
	
	
	//User-Functionalities
	@RequestMapping("/viewStatement.do")
	public ModelAndView viewStatement() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/changeUserDetails.do")
	public ModelAndView changeUserDetails() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/raiseRequest.do")
	public ModelAndView raiseRequest() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/trackRequest.do")
	public ModelAndView trackRequest() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/fundsTransfer.do")
	public ModelAndView fundsTransfer() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/addPayee.do")
	public ModelAndView addPayee() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/changePassword.do")
	public ModelAndView changePassword() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	//Admin Functionalities
	@RequestMapping("/addNewAccount.do")
	public ModelAndView addNewAccount() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	@RequestMapping("/viewAllTransactions.do")
	public ModelAndView viewAllTransactions() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}

	
	
}
