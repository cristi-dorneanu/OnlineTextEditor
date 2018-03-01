package com.texteditor.model.dao;

import com.texteditor.model.domain.File;

public interface FileDAOManager {
	public boolean writeToDisk(StringBuffer content, File file);
	public boolean deleteFromDisk(File file);
	public StringBuffer getFileContents(File file);
}
