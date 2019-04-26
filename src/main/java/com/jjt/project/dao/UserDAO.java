package com.jjt.project.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jjt.project.model.User;

@Mapper
public interface UserDAO {
	String tablename = "users";
	String fixedField = "id";
	String variableField = "name, email, password";
	
	@Select({"select * from ", tablename, "where id=#{id}"})
	User selectUserById(int id);
	
	@Select({"select * from ", tablename, "where name=#{name}"})
	User selectUserByName(String name);
	
	@Select({"select * from ", tablename, "where email=#{email}"})
	User selectUserByEmail(String email);
	
	@Insert({"insert into ", tablename, " (", variableField, ") values ", "(#{name}, #{email}, #{password})"})
	void addUser(User user);
	
	@Update({"update ", tablename, " set password=#{password} where id=#{id}"})
	void updatePassword(User user);
}
