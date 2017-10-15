package com.cg.obs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.obs.bean.ServiceTrackerBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.service.BankingService;
import com.cg.obs.service.UserService;

public class MainController {

	@Resource
	private UserService service;
	
	@Resource
	private BankingService bankService;
	
	//Initial Pages
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
	
	@RequestMapping(value="/forgotPassword.do", method=RequestMethod.POST)
	public ModelAndView forgotPasswordPage(@RequestParam("userId") int userId, 
			@RequestParam("secretQuestion") String secretQuestion,
			@RequestParam("secretAns") String secretAns
			) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		
		if(bankService.validateUserId(userId)){
			if(bankService.validateSecretQuestion(userId, secretQuestion)){
				if(bankService.validateSecretAns(userId, secretAns)){
					bankService.forgotPassword(userId);
					mAndV.setViewName("UserLogin");
					mAndV.addObject("message", "Password reset! New password is 'sbq500#' Please login again ");
				}else{
					mAndV.setViewName("ForgotPassword");
					mAndV.addObject("message", "worng Secret Answer!");
				}
			}
				else{
					mAndV.setViewName("ForgotPassword");
					mAndV.addObject("message", "worng Secret Question!");
				}
			}
				else{
					mAndV.setViewName("ForgotPassword");
					mAndV.addObject("message", "worng User Id!");
				}
			
		
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
		
		ServiceTrackerBean bean = new ServiceTrackerBean(); // command obj (in terms of springMVC)
		mAndV.addObject("raiseReq", bean);
		
		return mAndV;
	}
	
	@RequestMapping("/getTrackRequest.do")
	public ModelAndView getTrackRequestPage(@RequestParam("req") String req) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("TrackRequest");
		
		
		if("reqId".equals(req)) {
			mAndV.addObject("request","reqType");
			mAndV.addObject("res", "Req");
			mAndV.setViewName("TrackByID");
		} else if("accNm".equals(req)) {
			mAndV.addObject("request","accType");
			mAndV.addObject("res", "Acc");
			mAndV.setViewName("TrackByAcc");
		} else {
			mAndV.addObject("res", null);
		}
		
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
	
	//changing password
	@RequestMapping("/getChangePassword.do")
	public ModelAndView getChangePasswordPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangePassword");
		return mAndV;
	}
	
	@RequestMapping(value="/changePassword.do", method=RequestMethod.POST)
	public ModelAndView changePasswordPage(@RequestParam("userId") int userId,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword
			) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangePassword");
		
		if(service.changePassword(userId, oldPassword, newPassword)){
			mAndV.addObject("message", "Password successfully changed");
			
		}else{
			mAndV.addObject("message", "wrong Password entered!");
		}
		
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
	public String raiseRequest(@ModelAttribute("raiseReq")ServiceTrackerBean reqBean, Model mAndV) throws BankingException {
		
		reqBean.setStatus("OPEN");
		
		service.raiseRequest(reqBean);
		/*mAndV = new ModelAndView();*/
		mAndV.addAttribute("Request", reqBean);
		mAndV.addAttribute("pageHead", "Request Raised Successfully");
		
		return "SuccessPage";
	}
	
	@RequestMapping("/trackRequestById.do")
	public ModelAndView trackRequestById(@RequestParam("id") int requestId) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		reqList = service.trackRequest(requestId, 0);
		
		mAndV.addObject("requstLst", reqList);
		mAndV.setViewName("SuccessPage");
		
		return mAndV;
	}
	
	@RequestMapping("/trackRequestByAcc.do")
	public ModelAndView trackRequestByAcc(@RequestParam("acc") int accNo) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		reqList = service.trackRequest(0, accNo);
		
		mAndV.addObject("requstLst", reqList);
		mAndV.setViewName("SuccessPage");
		
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
