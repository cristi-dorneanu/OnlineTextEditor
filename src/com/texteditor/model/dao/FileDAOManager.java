package com.texteditor.model.dao;

import java.io.IOException;

import com.texteditor.model.domain.File;

public interface FileDAOManager {
	public boolean writeToDisk(StringBuffer content, File file) throws IOException;
	public boolean deleteFromDisk(File file) throws IOException;
}
