package com.texteditor.model.dao;

import java.util.List;

import com.texteditor.model.domain.File;

public interface FileDAO {
	public boolean saveFile(long userId, File file);
	public File getFile(long fileId);
	public File getFileByFilename(long userId, String filename);
	public List<File> getFilesFromUser(long userId);
	public boolean deleteFile(long userId, long fileId);
	public boolean updateFile(File file);
}
