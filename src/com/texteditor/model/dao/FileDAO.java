package com.texteditor.model.dao;

import java.util.List;

import com.texteditor.model.domain.File;

public interface FileDAO {
	public boolean saveFile(List<String> lines, String filename, String userRootPath);
	public File getFileFromUser(String userRootPath, String filename);
	public File getFilesFromUser(String userRootPath);
	public boolean deleteFile(String userRoothPath, String filename);
}
