package com.texteditor.model.business;

import com.texteditor.model.dao.UserDAO;
import com.texteditor.model.domain.User;

public class Authenticator {
	public static boolean login(User user, UserDAO userDao) {
		if(!isValidUser(user)) {
			return false;
		}
		
		User userFromDb = userDao.getUserByUsername(user.getUsername());
		if (user.getPassword().equals(userFromDb.getPassword())) {
			return true;
		}

		return false;
	}
	
	public static boolean isValidUser(User user) {
		if(user == null || user.getUsername() == null || user.getUsername().trim().isEmpty() ||
				user.getPassword() == null || user.getPassword().trim().isEmpty()) 
			{
				return false;
			}
		
		return false;
	}
}
