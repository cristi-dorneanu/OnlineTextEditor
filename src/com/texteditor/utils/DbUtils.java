package com.texteditor.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	public static void closeConn(Connection conn, Statement stmt, ResultSet rs)
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				
			}
		}
	}
	
	public static String trim(String field) {
		return field == null ? "" : field.trim();
	}
}
