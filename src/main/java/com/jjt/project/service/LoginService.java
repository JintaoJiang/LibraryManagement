package com.jjt.project.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.project.exception.LoginException;
import com.jjt.project.model.Ticket;
import com.jjt.project.model.User;
import com.jjt.project.utils.HostUtils;
import com.jjt.project.utils.MD5Utils;
import com.jjt.project.utils.TicketUtils;

@Service
public class LoginService {
	Logger logger = Logger.getLogger(LoginService.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	public void register(User user) throws Exception {
		//check if exist
		if(userService.getUserByEmail(user.getEmail()) != null) {
			throw new LoginException("Email exists!");
		}
		//create user
		user.setPassword(MD5Utils.next(user.getPassword()));
		userService.addUser(user);
		//create ticket
		Ticket ticket = TicketUtils.next(user.getId());
		ticketService.addTicket(ticket);		
	}
	
	public Ticket login(User user) throws Exception {
		//check if exist
		User userExist = userService.getUserByEmail(user.getEmail());
		if(userExist == null) {
			throw new LoginException("Email doesn't exist!");
		}else if(!userExist.getPassword().equals(MD5Utils.next(user.getPassword()))) {
			throw new LoginException("Password is incorrect!");
		}
		//get ticket
		Ticket ticket = ticketService.getTicket(userExist.getId());
		if(ticket == null) {
			ticket = TicketUtils.next(userExist.getId());
			ticketService.addTicket(ticket);
		}else if(ticket.getExpireDate().before(new Date())) {
			ticketService.deleteTicket(ticket.getId());
			ticket = TicketUtils.next(userExist.getId());
			ticketService.addTicket(ticket);
		}		
		logger.info(Thread.currentThread() + ":" + userExist.getName() + " login.");
		
		return ticket;
	}
	
	public void logout(String ticketContent) {
		User user = HostUtils.getUser();
		logger.info(Thread.currentThread() + ":" + user.getName() + " logout.");
		ticketService.deleteTicket(ticketContent);
	}
	
}





