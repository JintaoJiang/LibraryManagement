package com.jjt.project.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jjt.project.model.Ticket;

@Mapper
public interface TicketDAO {
	String tablename = "tickets";
	String fixedField = "id";
	String variableField = "userId, ticketContent, expireDate";
	
	@Select({"select * from ", tablename, "where userId=#{userId}"})
	Ticket selectByUserId(int userId);
	
	@Select({"select * from ", tablename, "where ticketContent=#{ticketContent}"})
	Ticket selectByTicketContent(String ticketContent);
	
	@Delete({"delete from ", tablename, "where id=#{id}"})
	void deleteByTicketId(int id);
	
	@Delete({"delete from ", tablename, "where ticketContent=#{ticketContent}"})
	void deleteByTicketContent(String ticketContent);
	
	@Insert({"insert into ", tablename, " (", variableField, ") values ", "(#{userId}, #{ticketContent}, #{expireDate})"})
	void addTicket(Ticket ticket);
	
}
