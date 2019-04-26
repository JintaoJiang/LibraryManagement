package com.jjt.project.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static void createCookie(String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
	}
	
	public static String getCookieContent(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}			
		return null;
	}
	
	public static void removeCookie(String key, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(key)) {
					Cookie newCookie = new Cookie(key, "");
					newCookie.setPath("/");
					newCookie.setMaxAge(0);
					response.addCookie(newCookie);
				}
			}
		}
	}
}
