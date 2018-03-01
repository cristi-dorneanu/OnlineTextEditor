package com.texteditor.model.business;

import com.texteditor.model.dao.UserDAO;
import com.texteditor.model.domain.User;
import com.texteditor.utils.ControllerUtils;

public class Authenticator {
	public static boolean login(User user, UserDAO userDao) {
		if(!ControllerUtils.isValidUserLogin(user)) {
			return false;
		}
		
		User userFromDb = userDao.getUserByUsername(user.getUsername());
		if (user.getPassword().equals(userFromDb.getPassword())) {
			return true;
		}

		return false;
	}
	
	public static boolean signup(User user, UserDAO userDao) {
		if(!ControllerUtils.isValidUserSignup(user)) {
			return false;
		}
		
		String serverPath = FileManager.generateServerRelativePath(user);
		user.setServerRelativePath(serverPath);
		
		if(userDao.insert(user)) {
			return true;
		}
		
		return false;
	}
	
}
