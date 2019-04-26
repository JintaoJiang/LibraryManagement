package com.jjt.project.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jjt.project.constants.BizConstants;
import com.jjt.project.model.Ticket;
import com.jjt.project.model.User;
import com.jjt.project.service.LoginService;
import com.jjt.project.service.TicketService;
import com.jjt.project.service.UserService;
import com.jjt.project.utils.CookieUtils;
import com.jjt.project.utils.HostUtils;

@Component
public class HostInterceptor implements HandlerInterceptor{
	Logger logger = Logger.getLogger(HostInterceptor.class);

	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String cookieContent = CookieUtils.getCookieContent(BizConstants.COOKIE_KEY, request);
		if(cookieContent != null) {
			Ticket ticket = ticketService.getTicket(cookieContent);
			if(ticket != null && ticket.getExpireDate().after(new Date())) {
				//set user if cookie is valid
				User user = userService.getUserById(ticket.getUserId());
				logger.info(Thread.currentThread() + ":" + user.getName() + " performing.");
				HostUtils.setUser(user);
				return true;
			}
		}
		//remove user if no cookie
		HostUtils.removeUser();
		return true;
	}
	
}






