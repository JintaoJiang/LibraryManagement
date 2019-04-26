package com.jjt.project.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jjt.project.constants.BizConstants;
import com.jjt.project.model.Ticket;
import com.jjt.project.model.User;
import com.jjt.project.service.LoginService;
import com.jjt.project.utils.CookieUtils;

@Controller
@RequestMapping("/userAPI")
public class LoginController {
	@Autowired
	private LoginService loginService;
		
	@RequestMapping(path = {"/registerPage"}, method = {RequestMethod.GET})
	public String registerPage() {
		return "login/register";
	}
	
	@RequestMapping(path = {"/register"}, method = {RequestMethod.POST})
	public String register(Model model, HttpServletResponse response, User user) {
		try {
			loginService.register(user);
			return "redirect:/userAPI/loginPage";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "404";
		}		
	}
	
	@RequestMapping(path = {"/loginPage"}, method = {RequestMethod.GET})
	public String loginPage() {
		return "login/login";
	}
	
	@RequestMapping(path = {"/login"}, method = {RequestMethod.POST})
	public String login(Model model, HttpServletResponse response, User user) {
		try {
			Ticket ticket = loginService.login(user);
			//Create cookie after login.
			CookieUtils.createCookie(BizConstants.COOKIE_KEY, ticket.getTicketContent(), response);
			return "redirect:/bookAPI/bookshelfPage";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "404";
		}
	}
	
	@RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
	public String logout(@CookieValue(BizConstants.COOKIE_KEY) String ticketContent) {
		loginService.logout(ticketContent);
		return "redirect:/bookAPI/bookshelfPage";
	}
	
}
