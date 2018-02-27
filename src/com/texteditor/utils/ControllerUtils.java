package com.texteditor.utils;

import javax.servlet.http.HttpServletRequest;

import com.texteditor.model.config.Resource;
import com.texteditor.model.domain.User;

public class ControllerUtils {

	public static boolean isPath(String uri, Resource res) {
		return (uri.equalsIgnoreCase(res.url()) || uri.toUpperCase().endsWith(res.url().toUpperCase()));
	}
	
	public static User constructUserFromRequest(HttpServletRequest req) {
		User user = new User();
		
		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));
		user.setEmail(req.getParameter("email"));
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		
		return user;
	}
	
	public static boolean isValidUserLogin(User user) {
		if(user == null || user.getUsername() == null || user.getUsername().trim().isEmpty() ||
				user.getPassword() == null || user.getPassword().trim().isEmpty()) 
			{
				return false;
			}
		
		return true;
	}
	
	public static boolean isValidUserSignup(User user) {
		if(user == null || user.getUsername() == null || user.getUsername().trim().isEmpty() ||
				user.getPassword() == null || user.getPassword().trim().isEmpty()) 
			{
				return false;
			}
		
		return true;
	}
}
