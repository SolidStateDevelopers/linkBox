package edu.csupomona.cs480.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	private static ConnectionManager cm = null;
	private static Connection con = null;
	
	private ConnectionManager()
	{
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			System.out.println("Connected to database");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		try
		{
			if(cm == null || con.isValid(5))
				cm = new ConnectionManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
