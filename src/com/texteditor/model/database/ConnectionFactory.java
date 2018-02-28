package com.texteditor.model.database;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://DESKTOP-F92U4QA:3306/online_text_editor";
    public static final String USER = "root";
    public static final String PASS = "Dorni593";
    
    public static Connection getConnection() {
    	try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database", e);
		}
    }
    
    /**

     * Test Connection

     */

    public static void main(String[] args) {

       ConnectionFactory.getConnection();

    }
}
