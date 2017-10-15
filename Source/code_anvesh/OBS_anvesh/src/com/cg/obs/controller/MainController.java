package com.cg.obs.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.obs.entities.AccountMasterBean;
import com.cg.obs.entities.ServiceTrackerBean;
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
	
	@RequestMapping(value="/getAdminHome.do", method=RequestMethod.POST)
	public ModelAndView getAdminHomePage(@RequestParam("id") int userId,@RequestParam("password") String password, HttpSession session) throws BankingException {
		
		boolean flagu =false;
		ModelAndView mAndV = new ModelAndView();
		if(userId==007) {
			flagu = true;
		}
		if(flagu){
				
				if("bond".equals(password)) {
					mAndV.setViewName("AdminHome");
					session.setAttribute("admin", userId);
				}
				
				else{
					
					mAndV.setViewName("AdminLogin");
					mAndV.addObject("msg", "Invalid Credentials");
					
				}
		}
		else{
			mAndV.setViewName("AdminLogin");
			mAndV.addObject("msg", "Invalid Credentials");
		}
		
		return mAndV;
	}
	
	@RequestMapping("/getUserLogin.do")
	public ModelAndView getUserLoginPage() throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("UserLogin");
		return mAndV;
	}
	
	@RequestMapping(value="/getUserHome.do", method=RequestMethod.POST)
	public ModelAndView getUserHomePage(@RequestParam("id") int userId,@RequestParam("password") String password, HttpSession session) throws BankingException {
		boolean flagu;
		//int loginAttempts = 0;
		ModelAndView mAndV = new ModelAndView();
		
		/*flagu = bankService.validateUserId(userId);
		
		if(flagu){
			UserBean userDet = bankService.validatePassword(password, userId);
			do {
			if(userDet==null){
				loginAttempts++;
				System.out.println(loginAttempts);
				mAndV.setViewName("UserLogin");
				mAndV.addObject("msg", "Invalid Credentials");
			}else{
				System.out.println("testing......");
				mAndV.setViewName("UserHome");
				session.setAttribute("userId", userId);
				session.setAttribute("userDetails", userDet);
			}
			} while(loginAttempts>3);
			if(loginAttempts>=3) {
				mAndV.addObject("msg", "Your Account Has Been Locked please try again later");
				mAndV.setViewName("UserLogin");
			}
		}else{
			mAndV.setViewName("UserLogin");
			mAndV.addObject("msg", "Invalid User");
		}*/
		
		flagu= bankService.validateUserId(userId);
		
		if(flagu){
				
				UserBean userDet = bankService.validatePassword(password, userId);
				
				if("wrong".equals(userDet.getLoginPassword())){
					
					mAndV.setViewName("UserLogin");
					mAndV.addObject("msg", "Invalid Credentials");
					
					if(userDet.getLockStatus()>=3){
						mAndV.addObject("msg", "Your Account Has Been Locked please try again later");
						mAndV.setViewName("UserLogin");
					}
				}else{
					mAndV.setViewName("UserHome");
					session.setAttribute("userId", userId);
					session.setAttribute("userDetails", userDet);
				}
		}
		else{
			mAndV.setViewName("UserLogin");
			mAndV.addObject("msg", "Invalid User");
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
	public ModelAndView getViewStatementPage(HttpSession session) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView("ViewStatement");
		ArrayList<AccountMasterBean> list = userService.getAccountDetails((Integer)session.getAttribute("userId"));
		
		System.out.println(list);
		mAndV.addObject("accList", list);
		
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
				mAndV.addObject("message", "Password successfully changed");
				
			}else{
				mAndV.addObject("message", "wrong Password entered!");
			}
		}else{
			mAndV.addObject("message", "Re-entered password didn't match");
			
		
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
		
		///service.raiseRequest(reqBean);
		/*mAndV = new ModelAndView();*/
		mAndV.addAttribute("Request", reqBean);
		mAndV.addAttribute("pageHead", "Request Raised Successfully");
		
		return "SuccessPage";
	}
	
	@RequestMapping("/trackRequestById.do")
	public ModelAndView trackRequestById(@RequestParam("id") int requestId) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		//reqList = userService.trackRequest(requestId, 0);
		
		mAndV.addObject("requstLst", reqList);
		mAndV.setViewName("SuccessPage");
		
		return mAndV;
	}
	
	@RequestMapping("/trackRequestByAcc.do")
	public ModelAndView trackRequestByAcc(@RequestParam("acc") long accNo) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		ArrayList<ServiceTrackerBean> reqList = new ArrayList<>();
		//reqList = userService.trackRequest(0, accNo);
		
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
	
	//common functionality
	@RequestMapping("/logOut.do")
	public ModelAndView logOut(HttpSession session) throws BankingException {
		
		ModelAndView mAndV = new ModelAndView();
		int uId = (Integer)session.getAttribute("userId");
		if(uId == 007) {
			session.invalidate();
			mAndV.setViewName("HomePage");
		} else {
		userService.resetLockStatus(uId);
		session.invalidate();
		mAndV.setViewName("HomePage");
		}
		return mAndV;
	}
	
}
