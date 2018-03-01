package com.texteditor.model.business;

import com.texteditor.model.dao.FileDAO;
import com.texteditor.model.dao.FileDAOManager;
import com.texteditor.model.domain.File;
import com.texteditor.model.domain.User;
import static java.io.File.separator;

public class FileManager {
	public static final String SERVER_ROOT_PATH = System.getProperty("java.io.tmpdir");
	
	public static String generateServerRelativePath(User user) {
		return SERVER_ROOT_PATH + separator + user.getUsername() + separator;
	}
	
	public static boolean fileExists(User user, String fileName, FileDAO fileDao) {
		File file = fileDao.getFileByFilename(user.getId(), fileName);
		
		return file == null ? false : true;
	}
	
	public static boolean fileExists(User user, String fileName, FileDAOManager fileDao) {
		File file = new File();
		file.setFileName(fileName);
		file.setFilePath(user.getServerRelativePath());
		
		StringBuffer content = fileDao.getFileContents(file);
		
		return content == null ? false : true;
	}
	
}
