package edu.csupomona.cs480.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

	public void addUser(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException
	{
		String hashedPass = hashPass(password);
		String sql = "INSERT INTO users (userID, password, fname, lname) VALUES (\"" + userName + "\", \"" + hashedPass + "\", \"" + firstName + "\", \"" + lastName + "\");";
		executeSQL(sql, "INSERT");
	}
	
	public void addLink(String userName, String newLink, String category)
	{
		String sql = "INSERT INTO links (userID, link, category) VALUES (\"" + userName + "\", \"" + newLink + "\", \"" + category + "\");";
		executeSQL(sql, "INSERT");
	}
	
	public boolean logInUser(String userName, String password) throws NoSuchAlgorithmException
	{
		boolean success = false;
		String hashedPass = hashPass(password);
		String sql = "SELECT * FROM users WHERE userID = \"" + userName + "\" AND password = \"" + hashedPass + "\";";
		ResultSet rs = executeSQL(sql, "SELECT");
		try {
			if (rs == null || !rs.next())
			{
				success = false;
			}
			else 
			{
				success = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	public void deleteLink(String userName, String link)
	{
		String sql = "DELETE FROM links WHERE userID = \"" + userName + "\" AND link = \"" + link + "\";";
		executeSQL(sql, "DELETE");
	}
	
	public ArrayList<SaveData> getLinks(String userName)
	{
		String sql = "SELECT link, category FROM links WHERE userID = \"" + userName + "\" ORDER BY category, link ASC;";
		ResultSet rs = executeSQL(sql, "SELECT");
		ArrayList<SaveData> ls = new ArrayList<SaveData>();
		try
		{
			while(rs.next())
			{
				SaveData sd = new SaveData();
				sd.setBookmark(rs.getString("link"));
				sd.setCategory(rs.getString("category"));
				sd.setId(userName);
				sd.setPublic(rs.getBoolean("public"));
				//ls.add(rs.getString("link"));
				ls.add(sd);
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		return ls;
	}
	
	
	public ArrayList<SaveData> getLinksByCategory(String userName, String category)
	{
		String sql = "SELECT link, category FROM links WHERE userID = \"" + userName + "\" and category = \"" + category + "\" ORDER BY category, link ASC;";
		ResultSet rs = executeSQL(sql, "SELECT");
		ArrayList<SaveData> ls = new ArrayList<SaveData>();
		try
		{
			while(rs.next())
			{
				SaveData sd = new SaveData();
				sd.setBookmark(rs.getString("link"));
				sd.setCategory(rs.getString("category"));
				sd.setId(userName);
				sd.setPublic(rs.getBoolean("public"));
				//ls.add(rs.getString("link"));
				ls.add(sd);
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<String> getCategories(String userName)
	{
		String sql = "SELECT link, category FROM links WHERE userID = \"" + userName + "\" ORDER BY category, link ASC;";
		ResultSet rs = executeSQL(sql, "SELECT");
		ArrayList<String> ls = new ArrayList<String>();
		try
		{
			while(rs.next())
			{
				ls.add(rs.getString("category"));
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		return ls;
	}
	
	private String hashPass(String pass) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		StringBuffer hashedPass = new StringBuffer();
		byte[] hash = md.digest(pass.getBytes());
		for (int i = 0; i < hash.length; i++)
		{
			hashedPass.append(Integer.toHexString(0xFF & hash[i]));
		}
		return hashedPass.toString();
	}
	private ResultSet executeSQL(String sql, String type)
	{
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			Statement st = con.createStatement();
			if (type.equals("SELECT"))
			{
				rs = st.executeQuery(sql);
			}
			else if (type.equals("INSERT") || type.equals("DELETE"))
			{
				st.executeUpdate(sql);
			}
		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return rs;
	}
}
