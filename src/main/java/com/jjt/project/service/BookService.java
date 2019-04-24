package com.jjt.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.project.dao.BookDAO;
import com.jjt.project.model.Book;
import com.jjt.project.model.enums.BookStatusEnum;

@Service
public class BookService {
	@Autowired
	private BookDAO bookDAO;
	
	public List<Book> getAllBook() {
		return bookDAO.selectAllBook();
	}
	
	public void addBook(Book book) {
		bookDAO.insertBook(book);
	}
	
	public void borrowBook(int id) {
		bookDAO.updateBookStatus(id, BookStatusEnum.BORROWED.getValue());
	}
	
	public void returnBook(int id) {
		bookDAO.updateBookStatus(id, BookStatusEnum.AVAILABLE.getValue());
	}
}





