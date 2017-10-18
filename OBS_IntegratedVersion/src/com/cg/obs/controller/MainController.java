package com.cg.obs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.CustomerBean;
import com.cg.obs.entities.FundTransferBean;
import com.cg.obs.entities.PayeeBean;
import com.cg.obs.entities.ServiceTrackerBean;
import com.cg.obs.entities.TransactionBean;
import com.cg.obs.entities.UserBean;
import com.cg.obs.exception.BankingException;
import com.cg.obs.service.IAdminService;
import com.cg.obs.service.IBankingService;
import com.cg.obs.service.IUserService;


@Controller
public class MainController {

	@Resource
	private IUserService userService;
	@Resource
	private IBankingService bankService;
	@Resource
	private IAdminService adminService;
	
		@RequestMapping("/validatePassAndAmt.do")
		public ModelAndView validatePassAndAmt(@RequestParam("accNo") String payeeAccNumber,@RequestParam("transAmount") String transAmount,@RequestParam("desc") String desc,
				@RequestParam("transPassword") String transPassword, HttpSession session) throws BankingException{
			
			long transAmt=Long.parseLong(transAmount);
			long payeeAccNo=Long.parseLong(payeeAccNumber);
			long accNo=(Long)session.getAttribute("transAccNo");
	        
			int userId = (Integer)session.getAttribute("userId");
			ModelAndView mAndV ;
			if(userService.validateBalance(accNo, transAmt, userId, payeeAccNo))
			{
				if(userService.validateTransPassword(userId, transPassword))
				{
					java.util.Date date = java.sql.Date.valueOf(LocalDate.now());
					FundTransferBean fundTrans = new FundTransferBean(accNo,payeeAccNo,date,transAmt);
					TransactionBean credit = new TransactionBean(desc,date,"C",transAmt,payeeAccNo);
					TransactionBean debit = new TransactionBean(desc,date,"D",transAmt,accNo);
					userService.addFundTransfer(fundTrans);
					userService.addTransForCredit(credit, payeeAccNo);
					int transId=userService.addTransForDebit(debit);
					
					mAndV=	new ModelAndView("SuccessFundTrans");
					mAndV.addObject("transId", transId);
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
	
	
	//Initial Pages
	@RequestMapping("/getHomePage.do")
	public ModelAndView getHomePage() throws BankingException {
		bankService.setInitLockStatus();
		ModelAndView mAndV = new ModelAndView("Home");
		return mAndV;
	}
	
	
	@RequestMapping("/getAdminLogin.do")
	public ModelAndView getAdminLoginPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("AdminLogin");
		return mAndV;
	}
	
	@RequestMapping(value="/getAdminHome.do", method=RequestMethod.POST)
	public ModelAndView getAdminHomePage(@RequestParam("adminId") int adminId,@RequestParam("password") String password, HttpSession session) throws BankingException {
		
		boolean flagu =false;
		ModelAndView mAndV = new ModelAndView();
		if(adminId==007) {
			flagu = true;
		}
		if(flagu){
				
				if("bond".equals(password)) {
					mAndV.setViewName("AdminHomeNew");
					session.setAttribute("adminId", adminId);
				}
				
				else{
					
					mAndV.setViewName("AdminLogin");
					mAndV.addObject("loginErr", "Invalid Credentials");
					
				}
		}
		else{
			mAndV.setViewName("AdminLogin");
			mAndV.addObject("loginErr", "Invalid Credentials");
		}
		
		return mAndV;
	}
	
	@RequestMapping("/getUserLogin.do")
	public ModelAndView getUserLoginPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("UserLogin");
		return mAndV;
	}
	



@RequestMapping(value="/getUserHome.do", method=RequestMethod.POST)
	public ModelAndView getUserHomePage(@RequestParam("userId") int userId,@RequestParam("password") String password,HttpServletRequest req) throws BankingException {
		boolean flagu;
		//bankService.setInitLockStatus();
		ModelAndView mAndV = new ModelAndView();
		
		flagu= bankService.validateUserId(userId);
		
		if(flagu){
				
				UserBean userDet = bankService.validatePassword(password, userId);
				
				if("wrong".equals(userDet.getLoginPassword())){
					
					mAndV.setViewName("UserLogin");
					mAndV.addObject("loginErr", "Invalid Credentials");
					
					if(userDet.getLockStatus()>=3){
						mAndV.addObject("loginErr", "Your Account Has Been Locked please try again later");
						mAndV.setViewName("UserLogin");
					}
				}else{
					mAndV.setViewName("UserAccountDetails");
					System.out.println(userId);
					
					mAndV.addObject("accDet", userService.getAccountDetails(userId));
					CustomerBean custDet = bankService.getCustDetails(userId);
					HttpSession session = req.getSession(true);
					System.out.println(req.getParameter("abc"));
					
					
					session.setAttribute("test", 1);
					session.setAttribute("userId", userId);
					session.setAttribute("userDetails", userDet);
					session.setAttribute("custDetails", custDet);
					
				}
		}
		else{
			mAndV.setViewName("UserLogin");
			mAndV.addObject("loginErr", "Invalid User");
		}
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
					mAndV.addObject("message", "Wrong Secret Answer!");
				}
			}
				else{
					mAndV.setViewName("ForgotPassword");
					mAndV.addObject("message", "Wrong Secret Question!");
				}
			}
				else{
					mAndV.setViewName("ForgotPassword");
					mAndV.addObject("message", "Wrong User Id!");
				}
			
		
		return mAndV;
		}
	
	
	
	
	//User Functionality pages
	@RequestMapping("/viewStatement.do")
	public ModelAndView viewStatement(HttpServletRequest request,HttpSession session) throws BankingException {
		
		int userId=(Integer)session.getAttribute("userId");
		ModelAndView mAndV = new ModelAndView();
		if(!request.getParameter("accNo").equals("Nothing")){
		Long accNo = Long.parseLong(request.getParameter("accNo"));
		System.out.println(accNo);
		String viewStat = request.getParameter("option");
		
		
		List<TransactionBean> transactionList = new ArrayList<>();
		if(viewStat.equals("Mini")){
			transactionList = userService.getMiniTransactionDetails(accNo);
			int marker = 0;
			if(!(transactionList.isEmpty())){
				marker = 1;
				mAndV.addObject("transactionList", transactionList);
				mAndV.addObject("flag", 1);
				mAndV.addObject("marker",marker);
			}else{
				marker = 0;
				mAndV.addObject("marker",marker);
				mAndV.addObject("message", "No Transactions found");
				mAndV.addObject("flag", 1);
			}
			
		}
		
		if(viewStat.equals("Detailed")){
			mAndV.addObject("flag", 2);
			mAndV.addObject("accNo", accNo);
		}
		
		mAndV.addObject("accNo", accNo);
		}
		else{
			
			mAndV.addObject("message", "Select an Account Number");
			mAndV.addObject("flag", 0);
		}
		List<AccountMasterBean> accList = new ArrayList<>();
		accList =  userService.getAccountDetails(userId);
		mAndV.addObject("accList", accList);
		
		mAndV.setViewName("ViewStatement");
		return mAndV;
	}
	
	@RequestMapping("/viewFinalStatement.do")
	public ModelAndView viewFinalStatement(HttpServletRequest request,HttpSession session) throws BankingException {
		
		int userId = (Integer)session.getAttribute("userId");
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
			str = "Enter Correct Start Date";
			startDate = dff.parse(sDate);
			str="Enter Correct End Date";
			endDate = dff.parse(eDate);
			if(startDate.after(endDate))
				{
				str= "Start Date should not be after endDAte";
				throw new BankingException();}
			java.util.Date date = java.sql.Date.valueOf(LocalDate.now());
			if(endDate.after(date))
			{
			str= "Dates cannot be after Present Date";
			throw new BankingException();}
		} catch (ParseException e) {
			var =true;
		} catch (Throwable ex){
			var =true;
		}
		
		ModelAndView mAndV = new ModelAndView();
		if(var){
			mAndV.addObject("message",  str);
			mAndV.addObject("flag", 2);
		}
		else{
		List<TransactionBean> transactionList = new ArrayList<>();
		transactionList = userService.getDetailedTransactionDetails(accNo, startDate, endDate);
		
		if(transactionList.isEmpty())
		{
			mAndV.addObject("flag", 2);
			mAndV.addObject("message", "No Transactions found between "+startDate + "and " +  endDate);
		}
		else{
		mAndV.addObject("flag", 3);
		mAndV.addObject("transactionList", transactionList);
		}
		}
		
		List<AccountMasterBean> accList = new ArrayList<>();
		accList =  userService.getAccountDetails(userId);
		mAndV.addObject("accList", accList);
		mAndV.addObject("accNo", accNo);
		mAndV.setViewName("ViewStatement");
		return mAndV;
	}
	
	
	@RequestMapping("/getChangeDetails.do")
	public ModelAndView getChangeDetailsPage(HttpSession session) throws BankingException {
		
		int userId = (Integer)session.getAttribute("userId");
		String oldAddress  = userService.getAddress(userId);
		
		ModelAndView mAndV = new ModelAndView("ChangeDetails");
		mAndV.addObject("oldAddress",oldAddress);
		return mAndV;
	}
	
	@RequestMapping("/getRaiseRequest.do")
	public ModelAndView getRaiseRequestPage(HttpSession session) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("RaiseRequest");
		
		ServiceTrackerBean bean = new ServiceTrackerBean(); // command obj (in terms of springMVC)
		
		mAndV.addObject("raiseReq", bean);
		List<Long> reqList = new ArrayList<>();
		reqList = userService.getAccNoList((Integer)session.getAttribute("userId"));
		mAndV.addObject("reqList", reqList);
		return mAndV;
	}
	
/*	@RequestMapping("/getTrackRequest.do")
	public ModelAndView getTrackRequestPage(@RequestParam("req") String req,HttpSession session) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("TrackRequest");
		
		
		if("reqId".equals(req)) {
			mAndV.addObject("request","reqType");
			mAndV.addObject("res", "Req");
			mAndV.setViewName("TrackById");
		} else if("accNm".equals(req)) {
			mAndV.addObject("request","accType");
			mAndV.addObject("res", "Acc");
			List<Long> accList = userService.getAccNoList((Integer)session.getAttribute("userId"));
			mAndV.addObject("accLst", accList);
			System.out.println(accList);
			mAndV.addObject("flag", 1);
			mAndV.setViewName("TrackByAcc");
		} else {
			mAndV.addObject("res", null);
		}
		
		return mAndV;
	}*/
	
	@RequestMapping("/getTrackByAcc.do")
	public ModelAndView getTrackByAcc(HttpSession session) throws BankingException {
		
			ModelAndView mAndV = new ModelAndView();
		
			
			List<Long> accList = userService.getAccNoList((Integer)session.getAttribute("userId"));
			mAndV.addObject("accLst", accList);
			mAndV.addObject("flag", 1);
			mAndV.setViewName("TrackByAcc");
		

		
		return mAndV;
	}
	@RequestMapping("/getTrackById.do")
	public ModelAndView getTrackById(HttpSession session) throws BankingException {
		
			ModelAndView mAndV = new ModelAndView();
			mAndV.setViewName("TrackById");
	
		return mAndV;
	}
	@RequestMapping("/getFundsTransfer.do")
	public ModelAndView getFundsTransferPage(HttpSession session) throws BankingException {
		
		int userId = (Integer)session.getAttribute("userId");
		
		ModelAndView mAndV = new ModelAndView("SelectAccount");
		
		List<Long> accNoList = userService.getAccountNoList(userId);
		
		mAndV.addObject("accList",accNoList);
		return mAndV;
	}
	
	
	@RequestMapping("/getPayeeList.do")
	public ModelAndView getSelfTransferPage(HttpServletRequest request,@RequestParam("accNo") String accNumber,@RequestParam("transType") String transType, HttpSession session) throws BankingException {
		
		Long accNo=Long.parseLong(accNumber);
		session.setAttribute("transAccNo", accNo);
		int userId = (Integer)session.getAttribute("userId");
		System.out.println(accNo);
		ModelAndView  mAndV ;
	
		if(transType.equals("SelfBank"))
		{
			mAndV = new ModelAndView("FundTransferSelf");
			List payeeList = userService.getAccountNoList(userId);
			System.out.println(payeeList);
			mAndV.addObject("payeeList", payeeList);
			if(payeeList.size()==1)
			{
				mAndV.addObject("flag", 0);
			}
			else
			{
				mAndV.addObject("flag", 1);
			mAndV.addObject("selfAccNo", accNo);
			}
		}
		else{
		
			mAndV = new ModelAndView("FundTransferInter");
			List payeeList=userService.showPayeeList(accNo);
			System.out.println(payeeList);
			if(payeeList.isEmpty())
			{
				mAndV.addObject("flag", 0);
			}
			else
			{
				mAndV.addObject("flag", 1);
				mAndV.addObject("payeeList", payeeList);
			}
		
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
	
	@RequestMapping("/getChangePassword.do")
	public ModelAndView getChangePasswordPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangePassword");
		return mAndV;
	}
	
	@RequestMapping(value="/changePassword.do", method=RequestMethod.POST)
	public ModelAndView changePasswordPage(
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("newPasswordAgain") String newPasswordAgain,
			HttpSession session) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ChangePassword");
		
		int userId = (Integer)session.getAttribute("userId");
		
		if(newPassword.equals(newPasswordAgain)){
			
			
			if(userService.changePassword(userId, oldPassword, newPassword)){
				mAndV.addObject("message", "Password successfully changed!");
				
			}else{
				mAndV.addObject("message", "Wrong Password entered!");
			}
		}else{
			mAndV.addObject("message", "Password Mismatch");
			
		
		}
		return mAndV;
	}
	
	
	
	
	//Admin Functionality pages
	@RequestMapping("/getAddNewAccount.do")
	public ModelAndView getAddNewAccountPage() throws BankingException {
		List<Integer> userList =new ArrayList<>();
		userList = bankService.getUserIdList();
		
		ModelAndView mAndV = new ModelAndView("AddNewAccount");
		mAndV.addObject("userList", userList);
		return mAndV;
	}
	
	@RequestMapping("/getViewDailyPage.do")
	public ModelAndView getViewDailyTransactions(HttpServletRequest request) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewAll");
		String date=null;
		mAndV.addObject("rest", 0);
		if(request.getParameter("test")==null){
			
			
				if(request.getParameter("res")!=null){
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 date = (String)request.getParameter("res");
					
					LocalDate tape = LocalDate.parse(date, format);
					Date dt = java.sql.Date.valueOf(tape);
					
					String msg=null;
					int flag=1;
					ArrayList<TransactionBean> transList = adminService.viewDailyTransactions(dt);
					System.out.println("in date method after service");
					
					if(transList.isEmpty()){
						msg="No transactions available on "+date;
					}else{
						mAndV.addObject("transList", transList);
						msg = "Transactions on "+date;
						mAndV.addObject("t", 1);
					}
					mAndV.addObject("msg", msg);
					mAndV.addObject("flag",flag);
					mAndV.addObject("retain",date);
					
				}
				
			}else{
			int rest= Integer.parseInt(request.getParameter("test"));
			
			if(rest==0){
				mAndV.setViewName("ViewAll");
				mAndV.addObject("retain",date);
				
			}
		
		}
		return mAndV;
	}
	
	@RequestMapping("/getViewMonthlyPage.do")
	public ModelAndView getViewMonthlyTransactions(HttpServletRequest request) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewAll");
		String date=null;
		mAndV.addObject("rest", 1);
		System.out.println(getYearList());
		mAndV.addObject("yearList", getYearList());
		
		if(request.getParameter("test")==null){
			
			mAndV.addObject("test", 1);
			if(request.getParameter("month")!=null&&request.getParameter("year")!=null){
				
				String month = request.getParameter("month");
				String year = request.getParameter("year");
				
				ArrayList<TransactionBean> transList = adminService.viewMonthlyTransactions(month,year);
				System.out.println("in month method after service");
				mAndV.addObject("transList", transList);
				
				String msg=null;
				
				if(transList.isEmpty()){
					mAndV.addObject("msg", "No transactions in "+month+","+year);
					mAndV.addObject("t", 0);
				}else{
					mAndV.addObject("transList", transList);
					msg = "Transactions in "+month+","+year;
					mAndV.addObject("t", 1);
				}
			}
				
			}else{
			int rest= Integer.parseInt(request.getParameter("test"));
			
			if(rest==1){
				mAndV.setViewName("ViewAll");
				mAndV.addObject("retain",date);
				
				
			}
		
		}
		return mAndV;
	}
	
	
	@RequestMapping("/getViewYearlyPage.do")
	public ModelAndView getViewYearlyTransactions(HttpServletRequest request) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewAll");
		String date=null;
		mAndV.addObject("rest", 2);
		System.out.println(getYearList());
		mAndV.addObject("yearList", getYearList());
		if(request.getParameter("test")==null){
			
			mAndV.addObject("test", 2);
			
			if(request.getParameter("year")!=null){
				
				String year = request.getParameter("year");
				
				ArrayList<TransactionBean> transList = adminService.viewYearlyTransactions(year);
				System.out.println("in month method after service");
				mAndV.addObject("transList", transList);
				
				String msg=null;
				
				if(transList.isEmpty()){
					mAndV.addObject("msg", "No transactions in "+year);
					mAndV.addObject("t", 0);
				}else{
					mAndV.addObject("transList", transList);
					msg = "Transactions in "+year;
					mAndV.addObject("t", 1);
				}
			}
				
			}else{
			int rest= Integer.parseInt(request.getParameter("test"));
			
			if(rest==2){
				mAndV.setViewName("ViewAll");
				mAndV.addObject("retain",date);
			
			}
		
		}
		return mAndV;
	}
	
	@RequestMapping("/getUserHomeFinal.do")
	public ModelAndView getUserHomeFinal(HttpSession session) throws BankingException {
	
		int userId = (Integer)session.getAttribute("userId");
		
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("accDet", userService.getAccountDetails(userId));
		mAndV.setViewName("UserAccountDetails");
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
	@RequestMapping("/getViewStatement.do")
	public ModelAndView viewStatement() throws BankingException {
		List<AccountMasterBean> accList = new ArrayList<>();
		ModelAndView mAndV = new ModelAndView("ViewStatement");
		accList =  userService.getAccountDetails(10001);
		mAndV.addObject("accList", accList);
		mAndV.addObject("flag", 0);
		return mAndV;
	}
	
	
	@RequestMapping("/changeUserDetails.do")
	public ModelAndView changeUserDetails(HttpServletRequest request,HttpSession session) throws BankingException {
		String address= request.getParameter("address");
	
		int userId = (Integer)session.getAttribute("userId");
		userService.updateAddress(userId, address);
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("message", "Address has been successfully Updated");
		mAndV.addObject("accDet", userService.getAccountDetails(userId));
		mAndV.setViewName("UserAccountDetails");
		return mAndV;
	}
	
	@RequestMapping("/raiseRequest.do")
	public String raiseRequest(@ModelAttribute("raiseReq")ServiceTrackerBean reqBean, Model mAndV) throws BankingException {
		
		reqBean.setStatus("OPEN");
		
		userService.raiseRequest(reqBean);
		/*mAndV = new ModelAndView();*/
		mAndV.addAttribute("Request", reqBean);
		mAndV.addAttribute("pageHead", "Request Raised Successfully");
		
		return "SuccessPage";
	}

	@RequestMapping("/trackRequestById.do")
	public ModelAndView trackRequestById(@RequestParam("id") int requestId) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		reqList = userService.trackRequest(requestId, 0);
		
		mAndV.addObject("requstLst", reqList);
		mAndV.setViewName("SuccessPage");
		
		return mAndV;
	}
	
	@RequestMapping("/trackRequestByAcc.do")
	public ModelAndView trackRequestByAcc(@RequestParam("acc") long accNo) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		reqList = userService.trackRequest(0, accNo);
		System.out.println(reqList);
		mAndV.addObject("requstLst", reqList);
		mAndV.setViewName("SuccessPage");
		
		return mAndV;
	}
	
	
	@RequestMapping(value="/getAddPayee.do", method=RequestMethod.POST)
	public ModelAndView addPayeePage(@ModelAttribute("PayeeBean") PayeeBean payee, HttpSession session) throws BankingException {
		//long accNo=Long.parseLong(request.getParameter("accNo"));
		//long accNo=2000000000;
		int userId=(Integer)session.getAttribute("userId");
		payee.setAccountId((Long)session.getAttribute("transAccNo"));
		ModelAndView mAndV;
		System.out.println(payee);
		if(userService.validatePayeeAccountNo(payee.getPayeeAccountId()))
		{	
			
			if(userService.getAccountNoList(userId).contains(payee.getPayeeAccountId()))
			{
				mAndV= new ModelAndView("AddPayee");
				mAndV.addObject("errMsg", "Enter an Account number different from your's to be added as Payee");
			}
			else{
				
					if(userService.validatePayeeSelf(payee.getPayeeAccountId(), payee.getAccountId()))
					{
						mAndV= new ModelAndView("AddPayee");
						mAndV.addObject("errMsg", "Payee Already Exists");
					}
					else
					{
					System.out.println("in if maincontroller");
					userService.addPayee(payee);
					  mAndV = new ModelAndView("SuccesPayeeAddedPage");
					  mAndV.addObject("payeeBean",payee);
					}
			}
			
			
		}
		else
		{
		
			mAndV= new ModelAndView("AddPayee");
			mAndV.addObject("errMsg", "Invalid Payee Account Number");
		}
		return mAndV;
	}
	
	@RequestMapping("/changePassword.do")
	public ModelAndView changePassword() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	//Admin Functionalities
	@RequestMapping("/addNewAccount.do")
	public ModelAndView addNewAccount(HttpServletRequest request,HttpSession session) throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		if(request.getParameter("userId").equals("nothing"))
		{	
			List<Integer> userList =new ArrayList<>();
		userList = bankService.getUserIdList();
	
		mAndV.addObject("userList", userList);
			mAndV.addObject("message", "Select a userId");
			mAndV.setViewName("AddNewAccount");
		
		}
		else{
			
		int userId = Integer.parseInt(request.getParameter("userId"));
		String accType = request.getParameter("typeAcc");
		Long opBal     = Long.parseLong(request.getParameter("opBal"));
		
		if(accType.equals("Salary") && opBal <1000){
			mAndV.addObject("message","Opening Balance for Salary account should be atleast 1000");
			mAndV.setViewName("AddNewAccount");
		}
		else{
		java.util.Date date = java.sql.Date.valueOf(LocalDate.now());
		AccountMasterBean bean = new AccountMasterBean(userId, accType, opBal, date);
		adminService.addAccount(bean);
		
		mAndV.addObject("message","New Account Created Successfully");
		mAndV.setViewName("Success");
		}
		}
		return mAndV;
	}
	
	@RequestMapping("/viewAllTransactions.do")
	public ModelAndView viewAllTransactions() throws BankingException {
		ModelAndView mAndV = new ModelAndView();
		return mAndV;
	}
	
	//common functionality
	@RequestMapping("/logOut.do")
	public ModelAndView logOut(HttpSession session,HttpServletResponse response) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		int uId = (Integer)session.getAttribute("userId");
		if(uId == 007) {
			session.invalidate();
			mAndV.setViewName("HomePage");
		} else {
		userService.resetLockStatus(uId);
		session.invalidate();
		
		/*response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires",0);*/
		
		mAndV.setViewName("HomePage");
		
		}
		return mAndV;
	}

	//Miscellaneous
	public ArrayList<Integer> getYearList(){
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=2017;i>=1969;i--){
			list.add(i);
		}
		return list;
	}
	
}
