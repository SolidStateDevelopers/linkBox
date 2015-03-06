package edu.csupomona.cs480.data;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * This class handles the connection to the database, along with any queries to that database
 * the application requires.
 */
public class DataManager {

	public void addUser(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException
	{
		PasswordManager passManager = new PasswordManager();
	    String hashedPass = passManager.hashStringWithSHA256(password);
		String sql = "INSERT INTO users (userID, password, fname, lname) VALUES (\"" + userName + "\", \"" + hashedPass + "\", \"" + firstName + "\", \"" + lastName + "\");";
		executeInsertSQL(sql);
	}
	
	public void addLink(String userName, String newLink, String category)
	{
		if (newLink.substring(0, 7).equals("http://"))
		{
			newLink = newLink.substring(7, newLink.length());
		}
		else if (newLink.substring(0, 8).equals("https://"))
		{
			newLink = newLink.substring(8, newLink.length());
		}
		String sql = "INSERT INTO links (userID, link, category, date_added) VALUES (\"" + userName + "\", \"" + newLink + "\", \"" + category + "\", NOW());";
		executeInsertSQL(sql);
	}
	
	public boolean logInUser(String userName, String password) throws NoSuchAlgorithmException
	{
		boolean success = false;
		PasswordManager passManager = new PasswordManager();
		String hashedPass = passManager.hashStringWithSHA256(password);
		String sql = "SELECT * FROM users WHERE userID = \"" + userName + "\" AND password = \"" + hashedPass + "\";";
		ResultSet rs = executeSelectSQL(sql);
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
		executeDeleteSQL(sql);
	}
	
	public ArrayList<SaveData> getLinks(String userName, String sortType)
	{
		String sql = "SELECT link, category, public, date_added FROM links WHERE userID = \"" + userName + "\""; 
		switch (sortType)
		{
		case "Ascending":
		{
			sql += " ORDER BY link ASC;";
			break;
		}
		case "Descending":
		{
			sql += " ORDER BY link DESC;";
			break;
		}
		case "Oldest":
		{
			sql += " ORDER BY date_added ASC;";
			break;
		}
		case "Newest":
		{
			sql += " ORDER BY date_added DESC;";
			break;
		}
		}
		
		ResultSet rs = executeSelectSQL(sql);
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
				sd.setDate(rs.getString("date_added"));
				ls.add(sd);
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<SaveData> getPublicLinks(String userName)
	{
		String sql = "SELECT link, category, public FROM links WHERE userID = \"" + userName + "\" AND public = 1 ORDER BY category, link ASC;";
		ResultSet rs = executeSelectSQL(sql);
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
		String sql = "SELECT link, category, public FROM links WHERE userID = \"" + userName + "\" and category = \"" + category + "\" ORDER BY category, link ASC;";
		ResultSet rs = executeSelectSQL(sql);
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
	
	public ArrayList<SaveData> getCategories(String userName)
	{
		String sql = "SELECT DISTINCT category FROM links WHERE userID = \"" + userName + "\" ORDER BY category, link ASC;";
		ResultSet rs = executeSelectSQL(sql);
		ArrayList<SaveData> ls = new ArrayList<SaveData>();
		try
		{
			while(rs.next())
			{
				SaveData sd = new SaveData();
				//sd.setBookmark(rs.getString("link"));
				sd.setCategory(rs.getString("category"));
				sd.setId(userName);
				//sd.setPublic(rs.getBoolean("public"));
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
	
	/*public ArrayList<String> getCategories(String userName)
	{
		String sql = "SELECT DISTINCT category FROM links WHERE userID = \"" + userName + "\" ORDER BY category, link ASC;";
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
	}*/
	

	/*private ResultSet executeSQL(String sql, String type)
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
	*/
	
	private ResultSet executeSelectSQL(String sql)
	{
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			Connection con = ConnectionManager.getConnection();
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);

		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return rs;
	}
	
	private ResultSet executeInsertSQL(String sql)
	{
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			Connection con = ConnectionManager.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return rs;
	}
	
	private ResultSet executeDeleteSQL(String sql)
	{
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			Connection con = ConnectionManager.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return rs;
	}
}
