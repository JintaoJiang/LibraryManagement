package com.jjt.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.project.dao.UserDAO;
import com.jjt.project.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}
	
	public User getUserByName(String name) {
		return userDAO.selectUserByName(name);
	}
	
	public User getUserByEmail(String email) {
		return userDAO.selectUserByEmail(email);
	}
	
	public void updatePassword(User user) {
		userDAO.updatePassword(user);
	}
}





