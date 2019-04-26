package com.jjt.project.utils;

import com.jjt.project.model.User;

public class HostUtils {
	private static ThreadLocal<User> host = new ThreadLocal();
	
	public static User getUser() {
		return host.get();
	}
	
	public static void setUser(User user) {
		host.set(user);
	}
	
	public static void removeUser() {
		host.remove();
	}
	
}
