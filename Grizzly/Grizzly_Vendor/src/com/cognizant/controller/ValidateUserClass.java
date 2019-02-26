package com.cognizant.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateUserClass {
	static Connection connection;
	public static boolean validateUser(String user,String pass){
			
		boolean status=false;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cognizant");
		
		
		PreparedStatement statement = 
		connection.prepareStatement("select NAME,PASSWORD from VENDOR_LOGIN where NAME=? and PASSWORD=? and STATUS='U'");
		statement.setString(1,user);
		statement.setString(2,pass);
		
		
		ResultSet result=statement.executeQuery();
		status=result.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	static void lock(String user)
	{
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("update STOREUSERS set STATUS='L' where NAME=?");
			statement.setString(1,user);
			int rows=statement.executeUpdate();
			if(rows>0)
			{
				System.out.println("Lock");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
