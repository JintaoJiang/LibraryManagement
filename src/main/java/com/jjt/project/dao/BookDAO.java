package com.jjt.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jjt.project.model.Book;

@Mapper
public interface BookDAO {
	
	String tablename = "books";
	String fixedField = "id, status";
	String variableField = "title, author, price";
	
	@Select({"select * from ", tablename})
	List<Book> selectAllBook();
	
	@Insert({"insert into ", tablename, " (", variableField, ") values ", "(#{title}, #{author}, #{price})"})
	void insertBook(Book book);
	
	@Update({"update ", tablename, " set status=#{status} where id=#{id}"})
	void updateBookStatus(@Param("id") int id, @Param("status") int status);
	
}




