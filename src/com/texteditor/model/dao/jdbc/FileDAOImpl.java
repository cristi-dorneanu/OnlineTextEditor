package com.texteditor.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.texteditor.model.dao.FileDAO;
import com.texteditor.model.database.ConnectionFactory;
import com.texteditor.model.domain.File;
import com.texteditor.utils.DbUtils;

public class FileDAOImpl implements FileDAO{

	@Override
	public boolean saveFile(long userId, File file) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtMap = null;
		ResultSet rs = null;
		
		if(this.getFileByFilename(userId, file.getFileName()) != null) {
			return this.updateFile(file);
		}
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("INSERT INTO FILES (file_name, file_path, size_in_bytes, date_added, date_last_modified) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			int index = 1;
			stmt.setString(index++, DbUtils.trim(file.getFileName()));
			stmt.setString(index++, DbUtils.trim(file.getFilePath()));
			stmt.setLong(index++, file.getSizeInBytes());
			stmt.setDate(index++, file.getDateAdded());
			stmt.setDate(index++, file.getDateLastModified());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			rs.next();
			
			file.setId(rs.getInt(1));
			
			stmtMap = conn.prepareStatement("INSERT INTO USER_TO_FILES (id_user, id_file) VALUES (?, ?)");
			index = 1;
			stmtMap.setLong(index++, userId);
			stmtMap.setLong(index++, file.getId());
			
			int res = stmtMap.executeUpdate();
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
			DbUtils.closeConn(null, stmtMap, null);
		}
		
		return false;
	}

	@Override
	public File getFile(long fileId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		File file = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM FILES WHERE id = ?");
			stmt.setLong(1, fileId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				file = this.setFileProperties(file, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return file;
	}
	
	@Override
	public File getFileByFilename(long userId, String filename) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		File file = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM USER_TO_FILES JOIN FILES on id_file = id WHERE id_user = ? and file_name = ?");
			stmt.setLong(1, userId);
			stmt.setString(2, filename);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				this.setFileProperties(file, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return file;
	}

	@Override
	public List<File> getFilesFromUser(long userId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<File> resultFiles = new ArrayList<>();
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM USER_TO_FILES JOIN FILES ON id_file = id WHERE id_user = ?");
			stmt.setLong(1, userId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				File file = new File();
				this.setFileProperties(file, rs);
				
				resultFiles.add(file);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return resultFiles;
	}

	@Override
	public boolean deleteFile(long userId, long fileId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtMap = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmtMap = conn.prepareStatement("DELETE FROM USER_TO_FILES WHERE id_user = ? and id_file = ?");
			stmtMap.setLong(1, userId);
			stmtMap.setLong(2, fileId);
			int res = stmtMap.executeUpdate();
			
			stmt = conn.prepareStatement("DELETE FROM FILES WHERE id = ?");
			stmt.setLong(1, fileId);
			res = stmt.executeUpdate();
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, null);
			DbUtils.closeConn(null, stmtMap, null);
		}
		
		return false;
	}
	
	@Override
	public boolean updateFile(File file) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("UPDATE FILES SET file_name = ?, file_path = ?, size_in_bytes = ?, date_added = ?, date_last_modified = ? WHERE id = ?");
			
			int index = 1;
			stmt.setString(index++, DbUtils.trim(file.getFileName()));
			stmt.setString(index++, DbUtils.trim(file.getFilePath()));
			stmt.setLong(index++, file.getSizeInBytes());
			stmt.setDate(index++, file.getDateAdded());
			stmt.setDate(index++, file.getDateLastModified());
			stmt.setLong(index++, file.getId());
			
			int res = stmt.executeUpdate();
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, null);
		}
		
		return false;
	}

	private File setFileProperties(File file, ResultSet rs) throws SQLException{
		if(rs == null) {
			return new File();
		}
		
		if(file == null) {
			file = new File();
		}
		
		file.setId(rs.getLong("id"));
		file.setFileName(rs.getString("file_name"));
		file.setFilePath(rs.getString("file_path"));
		file.setSizeInBytes(rs.getLong("size_in_bytes"));
		file.setDateAdded(rs.getDate("date_added"));
		file.setDateLastModified(rs.getDate("date_last_modified"));

		return file;
	}
	
}
