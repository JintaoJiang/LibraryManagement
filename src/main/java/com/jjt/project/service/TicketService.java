package com.jjt.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.project.dao.TicketDAO;
import com.jjt.project.model.Ticket;


@Service
public class TicketService {
	@Autowired
	private TicketDAO ticketDAO;
	
	public void addTicket(Ticket ticket) {
		ticketDAO.addTicket(ticket);
	}
	
	public Ticket getTicket(int userId) {
		return ticketDAO.selectByUserId(userId);
	}
	
	public Ticket getTicket(String ticketContent) {
		return ticketDAO.selectByTicketContent(ticketContent);
	}
	
	public void deleteTicket(int id) {
		ticketDAO.deleteByTicketId(id);
	}
	
	public void deleteTicket(String ticketContent) {
		ticketDAO.deleteByTicketContent(ticketContent);
	}
}
