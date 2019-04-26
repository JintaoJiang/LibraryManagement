package com.jjt.project.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jjt.project.constants.BizConstants;
import com.jjt.project.model.Ticket;
import com.jjt.project.service.TicketService;
import com.jjt.project.utils.CookieUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor{	
	@Autowired
	TicketService ticketService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String cookieContent = CookieUtils.getCookieContent(BizConstants.COOKIE_KEY, request);
		if(cookieContent != null) {
			Ticket ticket = ticketService.getTicket(cookieContent);
			if(ticket != null && ticket.getExpireDate().after(new Date())) {
				return true;
			}
		}
		
		response.sendRedirect("/userAPI/loginPage");
		return false;
	}

}
