package com.jjt.project.utils;

import org.joda.time.DateTime;

import com.jjt.project.model.Ticket;

public class TicketUtils {
	public static Ticket next(int userId) {
		
		Ticket ticket = new Ticket();
		ticket.setUserId(userId);
		ticket.setTicketContent(UUIDUtils.next());
		DateTime nowDate = new DateTime();
		DateTime expireDate = nowDate.plusDays(1);
		ticket.setExpireDate(expireDate.toDate());
		
		return ticket;
	}
}
