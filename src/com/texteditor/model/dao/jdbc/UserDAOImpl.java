package com.texteditor.model.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import com.texteditor.model.dao.UserDAO;
import com.texteditor.model.database.ConnectionFactory;
import com.texteditor.model.database.DbUtils;
import com.texteditor.model.domain.User;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean insert(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("INSERT INTO user (first_name, last_name, email, username, password) VALUES(?, ?, ?, ?, ?)");
			
			int index = 1;
			stmt.setString(index++, DbUtils.trim(user.getFirstName()));
			stmt.setString(index++, DbUtils.trim(user.getLastName()));
			stmt.setString(index++, DbUtils.trim(user.getEmail()));
			stmt.setString(index++, DbUtils.trim(user.getUsername()));
			stmt.setString(index++, DbUtils.trim(user.getPassword()));
			
			stmt.executeUpdate();
			
			long id = this.getUserId(conn);
			user.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return true;
	}

	@Override
	public User getUserById(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM User WHERE id = ?");
			stmt.setLong(1, id);
			
			rs = stmt.executeQuery();
			
			User user = new User();
			
			if(rs.next()) {
				this.setUserProperties(rs, user);
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			
			User user = new User();
			
			if(rs.next()) {
				this.setUserProperties(rs, user);
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<User> userList = new ArrayList<>();
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM User");
			
			while(rs.next()) {
				User user = new User();
				this.setUserProperties(rs, user);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, rs);
		}
		
		return userList;
	}

	@Override
	public boolean updateUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("UPDATE User SET first_name = ?, last_name = ?, email = ?, username = ?, password = ? WHERE id = ?");
			
			int index = 1;
			stmt.setString(index++, user.getFirstName());
			stmt.setString(index++, user.getLastName());
			stmt.setString(index++, user.getEmail());
			stmt.setString(index++, user.getUsername());
			stmt.setString(index++, user.getPassword());
			stmt.setLong(index++, user.getId());
			
			int res = stmt.executeUpdate();	
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, null);
		}
		return false;
	}

	@Override
	public boolean removeUserById(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("DELETE FROM User WHERE id = ?");
			stmt.setLong(1, id);

			int res = stmt.executeUpdate();	
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, null);
		}
		return false;
	}

	@Override
	public boolean removeUserByUsername(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("DELETE FROM User WHERE username = ?");
			stmt.setString(1, username);

			int res = stmt.executeUpdate();	
			
			return res == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConn(conn, stmt, null);
		}
		return false;
	}

	private long getUserId(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		long userId = 0;
		
		try {
			String idQuery = "Select @@id as id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(idQuery);
			
			rs.next();
			
			userId = rs.getLong("id");
		} catch (SQLException e) {
			
		} finally {
			DbUtils.closeConn(null, stmt, rs);
		}
		
		return userId;
	}
	
	private void setUserProperties(ResultSet rs, User user) throws SQLException{
		if (rs == null) {
			return;
		}
		
		if(user == null) {
			user = new User();
		}
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
	}
}
