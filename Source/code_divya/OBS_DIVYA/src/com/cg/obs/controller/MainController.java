package com.cg.obs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.obs.exception.BankingException;
import com.cg.obs.service.IUserService;

@Controller
public class MainController {

	@Resource
	IUserService service;
	//
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
		String address= request.getParameter("address");
	//	service.updateAddress(accNo, address);
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
