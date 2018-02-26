package com.texteditor.model.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtils {
	public static void closeConn(Connection conn, Statement stmt, ResultSet rs)
	{
		
	}
	
	public static String trim(String field) {
		return field == null ? "" : field.trim();
	}
}
