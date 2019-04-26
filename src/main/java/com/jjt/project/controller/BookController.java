package com.jjt.project.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jjt.project.model.Book;
import com.jjt.project.model.User;
import com.jjt.project.service.BookService;
import com.jjt.project.service.LoginService;
import com.jjt.project.utils.HostUtils;

@Controller
@RequestMapping("/bookAPI")
public class BookController {
	Logger logger = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	
	@RequestMapping(path = {"/bookshelfPage"}, method = {RequestMethod.GET})
	public String bookShelfPage(Model model) {
		User host = HostUtils.getUser();
		if(host != null) {
			logger.info(Thread.currentThread() + ":" + host.getName() + " browsing.");
			model.addAttribute("host", host);
		}
		model.addAttribute("books", bookService.getAllBook());
		return "book/bookshelf";
	}
	
	@RequestMapping(path = {"/addBookPage"}, method = {RequestMethod.GET})
	public String addBookPage() {
		return "book/addBook";
	}
	
	@RequestMapping(path = {"/addBook"}, method = {RequestMethod.POST})
	public String addBook(@RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("price") String price) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		bookService.addBook(book);
		return "redirect:/bookAPI/bookshelfPage";
	}
	
	@RequestMapping(path = {"/books/{bookId:[0-9]+}/borrowBook"}, method = {RequestMethod.GET})
	public String borrowBook(@PathVariable("bookId") int bookId) {
		bookService.borrowBook(bookId);
		return "redirect:/bookAPI/bookshelfPage";
	}
	
	@RequestMapping(path = {"/books/{bookId:[0-9]+}/returnBook"}, method = {RequestMethod.GET})
	public String returnBook(@PathVariable("bookId") int bookId) {
		bookService.returnBook(bookId);
		return "redirect:/bookAPI/bookshelfPage";
	}
	
}
