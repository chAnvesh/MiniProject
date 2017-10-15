package com.cg.onlineBanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.onlineBanking.bean.AccountMasterBean;
import com.cg.onlineBanking.bean.TransactionBean;
import com.cg.onlineBanking.exception.BankingException;
import com.cg.onlineBanking.service.IAdminService;
import com.cg.onlineBanking.service.IUserService;

@Controller
public class MainController {

	@Resource
	IUserService service;
	@Resource
	IAdminService adminService;
	//
	@RequestMapping("/getHomePage.do")
	public ModelAndView getHomePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("Home");
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
		
		List<AccountMasterBean> accList = new ArrayList<>();
		ModelAndView mAndV = new ModelAndView("ViewStatement");
		accList =  service.getAccountDetails(10001);
		mAndV.addObject("accList", accList);
		System.out.println(accList);
		return mAndV;
	}
	
	@RequestMapping("/getChangeDetails.do")
	public ModelAndView getChangeDetailsPage() throws BankingException {
		String oldAddress  = service.getAddress(10001);
		
		ModelAndView mAndV = new ModelAndView("ChangeDetails");
		mAndV.addObject("oldAddress",oldAddress);
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
		
		ModelAndView mAndV = new ModelAndView("FundsTransfer");
		return mAndV;
	}
	
	@RequestMapping("/getSelfTransfer.do")
	public ModelAndView getSelfTransferPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("SelfTransfer");
		return mAndV;
	}
	
	@RequestMapping("/getInterTransfer.do")
	public ModelAndView getInterTransferPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("InterTransfer");
		return mAndV;
	}
	
	@RequestMapping("/getAddPayee.do")
	public ModelAndView getAddPayeePage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AddPayee");
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
	public ModelAndView viewStatement(HttpServletRequest request) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		if(!request.getParameter("accNo").equals("Nothing")){
		Long accNo = Long.parseLong(request.getParameter("accNo"));
		System.out.println(accNo);
		String viewStat = request.getParameter("option");
		
		
		List<TransactionBean> transactionList = new ArrayList<>();
		if(viewStat.equals("Mini")){
			transactionList = service.getMiniTransactionDetails(accNo);
			mAndV.addObject("transactionList", transactionList);
			mAndV.addObject("flag", 1);
		}
		
		if(viewStat.equals("Detailed")){
			mAndV.addObject("flag", 2);
			mAndV.addObject("accNo", accNo);
		}
		
		
		}
		else{
			
			mAndV.addObject("message", "Select an Account Number");
		}
		List<AccountMasterBean> accList = new ArrayList<>();
		accList =  service.getAccountDetails(10001);
		mAndV.addObject("accList", accList);
		mAndV.setViewName("ViewStatement");
		return mAndV;
	}
	
	@RequestMapping("/viewFinalStatement.do")
	public ModelAndView viewFinalStatement(HttpServletRequest request) throws BankingException {
		
		System.out.println("In ViewFinal Method");
		Long accNo = Long.parseLong(request.getParameter("accNo"));
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		
		System.out.println("printing...");
		SimpleDateFormat dff  = new SimpleDateFormat("yyyy-MM-dd");
		dff.setLenient(false);
		Date startDate = null;
		Date endDate = null;
		boolean var = false;
		String str= null;
		try {
			str = "Start Date";
			startDate = dff.parse(sDate);
			str="End Date";
			endDate = dff.parse(eDate);
		} catch (ParseException e) {
			var =true;
		}
		
		ModelAndView mAndV = new ModelAndView();
		if(var){
			mAndV.addObject("message", "Enter Correct " + str);
			mAndV.addObject("flag", 2);
		}
		else{
		List<TransactionBean> transactionList = new ArrayList<>();
		transactionList = service.getDetailedTransactionDetails(accNo, startDate, endDate);
		
		mAndV.addObject("flag", 3);
		mAndV.addObject("transactionList", transactionList);
		}
		
		List<AccountMasterBean> accList = new ArrayList<>();
		accList =  service.getAccountDetails(10001);
		mAndV.addObject("accList", accList);
		mAndV.addObject("accNo", accNo);
		mAndV.setViewName("ViewStatement");
		return mAndV;
	}
	
	@RequestMapping("/changeUserDetails.do")
	public ModelAndView changeUserDetails(HttpServletRequest request) throws BankingException {
		String address= request.getParameter("address");
	
		service.updateAddress(10001, address);
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("message", "Successfully Updated");
		mAndV.setViewName("Success");
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
	public ModelAndView addNewAccount(HttpServletRequest request) throws BankingException {
		String accType = request.getParameter("typeAcc");
		Long opBal     = Long.parseLong(request.getParameter("opBal"));
		java.util.Date date = java.sql.Date.valueOf(LocalDate.now());
		AccountMasterBean bean = new AccountMasterBean(10002, accType, opBal, date);
		adminService.addAccount(bean);
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("message","New Account Added Successfully");
		mAndV.setViewName("Success");
		return mAndV;
	}
	
	@RequestMapping("/viewAllTransactions.do")
	public ModelAndView viewAllTransactions() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
}
