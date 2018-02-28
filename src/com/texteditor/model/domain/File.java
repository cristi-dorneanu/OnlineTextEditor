package com.texteditor.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class File implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796414252549346415L;
	
	private long id;
	private long sizeInBytes;
	private String fileName;
	private String filePath;
	private Date dateAdded;
	private Date dateLastModified;

	public File() {
		this.id = 0;
		this.sizeInBytes = 0;
		this.fileName = "";
		this.filePath = "";
		this.dateAdded = new Date(0);
		this.dateLastModified = new Date(0);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	
}
