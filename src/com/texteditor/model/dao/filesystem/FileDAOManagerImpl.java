package com.texteditor.model.dao.filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;

import com.texteditor.model.dao.FileDAOManager;
import com.texteditor.model.domain.File;

public class FileDAOManagerImpl implements FileDAOManager{

	@Override
	public boolean writeToDisk(StringBuffer content, File file){
		if(file == null) {
			return false;
		}
		
		if(content == null) {
			content = new StringBuffer("");
		}
		
		Path localFilePath = Paths.get(file.getFilePath(), file.getFileName());
		Path localDirectoryPath = Paths.get(file.getFilePath());
		
		try {
			if(!Files.exists(localDirectoryPath)) {
				Files.createDirectories(localDirectoryPath);
			}
			if(!Files.exists(localFilePath)) {
				Files.createFile(localFilePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try(BufferedWriter bw = Files.newBufferedWriter(localFilePath)) {
			bw.write(content.toString());
			BasicFileAttributes attr = Files.readAttributes(localFilePath, BasicFileAttributes.class);
			
			file.setSizeInBytes(attr.size());
			file.setDateAdded(new Date(attr.creationTime().toMillis()));
			file.setDateLastModified(new Date(attr.lastModifiedTime().toMillis()));
				
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public boolean deleteFromDisk(File file) {
		if(file == null) {
			return false;
		}
		
		Path localFilePath = Paths.get(file.getFilePath(), file.getFileName());
		
		try {
			if(Files.exists(localFilePath)) {
				Files.delete(localFilePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return !Files.exists(localFilePath);
	}

	@Override
	public StringBuffer getFileContents(File file) {
		if(file == null) {
			return null;
		}
		
		Path localFilePath = Paths.get(file.getFilePath(), file.getFileName());
		
		StringBuilder sb = new StringBuilder("");
		StringBuffer content = null;
		
		try(BufferedReader br = Files.newBufferedReader(localFilePath)) {
			if(Files.exists(localFilePath)) {
				BasicFileAttributes attr = Files.readAttributes(localFilePath, BasicFileAttributes.class);
				
				file.setSizeInBytes(attr.size());
				file.setDateAdded(new Date(attr.creationTime().toMillis()));
				file.setDateLastModified(new Date(attr.lastModifiedTime().toMillis()));
				
				String line = br.readLine();
				
				while(line != null) {
					sb.append(line);
					line = br.readLine();
				}
				
				content = new StringBuffer(sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}

	
}
