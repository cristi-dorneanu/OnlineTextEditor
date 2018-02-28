package com.texteditor.model.dao.jdbc;

import java.util.List;

import com.texteditor.model.dao.FileDAO;
import com.texteditor.model.domain.File;

public class FileDAOImpl implements FileDAO{

	@Override
	public boolean saveFile(List<String> lines, String filename, String userRootPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File getFileFromUser(String userRootPath, String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFilesFromUser(String userRootPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFile(String userRoothPath, String filename) {
		// TODO Auto-generated method stub
		return false;
	}

}
