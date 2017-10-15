package com.cg.obs.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.service.IAdminService;
import com.cg.obs.service.IBankingService;
import com.cg.obs.service.IUserService;
import com.cg.obs.service.UserService;

@Controller
public class MainController {

	@Resource
	private IUserService service;
	//@Resource
	//private IBankingService bankService;
	@Resource
	private IAdminService adminService;
	
	
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
			mAndV.setViewName("TrackById");
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
	public ModelAndView getViewAllTransactionsPage(@RequestParam("req") int req) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewAllTransactions");
		
		
		if(req==1) {
			//mAndV.addObject("request","reqType");
			mAndV.addObject("req", 1);
			//mAndV.setViewName("TrackById");
		} else if(req==2) {
			//mAndV.addObject("request","accType");
			mAndV.addObject("req", 2);
			mAndV.addObject("yearList", getYearList());
			//mAndV.setViewName("TrackByAcc");
		} else if(req==3) {
			//mAndV.addObject("request","accType");
			mAndV.addObject("req", 3);
			mAndV.addObject("yearList", getYearList());
			//mAndV.setViewName("TrackByAcc");
		}else {
			mAndV.addObject("req", 0);
		}
		
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
	public ModelAndView trackRequestByAcc(@RequestParam("acc") long accNo) throws BankingException {
		
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
	
	@RequestMapping("/viewDailyTransactions.do")
	public ModelAndView viewAllTransactions(@RequestParam("res") String type) throws BankingException {
		ModelAndView mAndV = new ModelAndView("AllTransactions");
		System.out.println(type);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate tape = LocalDate.parse(type, format);
		Date dt = java.sql.Date.valueOf(tape);
		
		ArrayList<TransactionBean> transList = adminService.viewDailyTransactions(dt);
		mAndV.addObject("transList", transList);
		mAndV.addObject("t", 1);
		if(transList.isEmpty()){
			mAndV.addObject("msg", "No transactions on the this date");
			mAndV.addObject("t", 0);
		}
		
		return mAndV;
	}
	
	@RequestMapping("/viewMonthlyTransactions.do")
	public ModelAndView viewMonthlyTransactions(@RequestParam("month") String month,@RequestParam("year") String year) throws BankingException {
		ModelAndView mAndV = new ModelAndView("AllTransactions");
		System.out.println(month);
		System.out.println(year);
		
		ArrayList<TransactionBean> transList = adminService.viewMonthlyTransactions(month,year);
		mAndV.addObject("transList", transList);
		mAndV.addObject("t", 1);
		if(transList.isEmpty()){
			mAndV.addObject("msg", "No transactions in the this month");
			mAndV.addObject("t", 0);
		}
		
		return mAndV;
	}
	
	@RequestMapping("/viewYearlyTransactions.do")
	public ModelAndView viewYearlyTransactions(@RequestParam("year") String year) throws BankingException {
		ModelAndView mAndV = new ModelAndView("AllTransactions");
		
		ArrayList<TransactionBean> transList = adminService.viewYearlyTransactions(year);
		mAndV.addObject("transList", transList);
		mAndV.addObject("t", 1);
		if(transList.isEmpty()){
			mAndV.addObject("msg", "No transactions in this year");
			mAndV.addObject("t", 0);
		}
		return mAndV;
	}
	
	public ArrayList<Integer> getYearList(){
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=2017;i>=1969;i--){
			list.add(i);
		}
		return list;
	}
}
