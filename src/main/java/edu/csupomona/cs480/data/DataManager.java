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

	/**
	 * Add a user to the MySQL database
	 * @param userName The username the user will use to log into linkbox.
	 * @param password The plaintext user password. This will be hashed before stored
	 * @param firstName The user's first name.
	 * @param lastName The user's last name.
	 * @throws NoSuchAlgorithmException Exception that could occur while hashing the password
	 */
	public void addUser(String userName, String password, String firstName, String lastName) throws NoSuchAlgorithmException
	{
		PasswordManager passManager = new PasswordManager();
	    String hashedPass = passManager.hashStringWithSHA256(password);
		String sql = "INSERT INTO users (userID, password, fname, lname) VALUES (\"" + userName + "\", \"" + hashedPass + "\", \"" + firstName + "\", \"" + lastName + "\");";
		executeInsertSQL(sql);
	}
	
	/** 
	 * Add a link to the MySQL database
	 * @param userName The user that wants to store this link.
	 * @param newLink The actual URL that will be stored.
	 * @param category The user-chosen category that the link will be stored under.
	 */
	public void addLink(String userName, String newLink, String category)
	{
		//Trim the 'http://' or 'https://' from the URL. This will keep the program from adding 'http://' twice later on.
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
	
	/**
	 * Check to see if the user logged in successfully. A successful login is determined by a 
	 * matching userName and password.
	 * @param userName The user trying to log in.
	 * @param password The user's supposed password.
	 * @return Successful log in. If the user and password don't match, it will return false.
	 * @throws NoSuchAlgorithmException
	 */
	public boolean logInUser(String userName, String password) throws NoSuchAlgorithmException
	{
		boolean success = false;
		PasswordManager passManager = new PasswordManager();
		String hashedPass = passManager.hashStringWithSHA256(password);
		String sql = "SELECT * FROM users WHERE userID = \"" + userName + "\" AND password = \"" + hashedPass + "\";";
		ResultSet rs = executeSelectSQL(sql);
		try {
			//If there were no results, or the result set is null, the login failed.
			if (rs == null || !rs.next())
			{
				success = false;
			}
			//Otherwise, it succeeded.
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
	
	/**
	 * Retrieve the list of links that a user has saved. In addition, we sort the list before we return
	 * it based on the sortType.
	 * @param userName The user who's links we want to retrieve
	 * @param sortType The sorting string. This determines how we want the list sorted before we return it. 
	 * it has 4 potential options; 'Ascending', 'Descending', 'Oldest' and 'Newest'
	 * @return The sorted list of SaveData objects, containing each link the user has.
	 */
	public ArrayList<SaveData> getLinks(String userName, String sortType)
	{
		String sql = "SELECT link, category, public, date_added FROM links WHERE userID = \"" + userName + "\""; 
		//Tack on the appropriate sorting suffix.
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
			//Loop through the result set and make a SaveData object for every link.
			//Put those objects into a list that we'll return.
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
	
	//Similar to above, this gets all the links that a user has determined to be public.
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
	
	//Same thing as up there but only by the category
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
	
	//Return all the categories that a user has on their records.
	public ArrayList<SaveData> getCategories(String userName)
	{
		String sql = "SELECT DISTINCT category FROM links WHERE userID = \"" + userName + "\" ORDER BY category ASC;";
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
	
	/**
	 * Creates a connection to the MySQL database and returns the result set of the 
	 * query specified in the sql parameter.
	 * @param sql The full SQL statement to be executed against the database.
	 * @return The result set of the SQL statement given by the sql parameter.
	 */
	private ResultSet executeSelectSQL(String sql)
	{
		ResultSet rs = null;
		try
		{
			//Create a connection to the MySQL database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection con = DriverManager.getConnection("jdbc:mysql://cs480db.cyezs5priejv.us-west-2.rds.amazonaws.com:3306/cs480MySQL", "ec2user", "abcd1234");
			Connection con = ConnectionManager.getConnection();
			Statement st = con.createStatement();
			//Then execute the SQL statement.
			rs = st.executeQuery(sql);

		}
		catch (Exception E)
		{
			E.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Creates a connection to the MySQL database to execute an INSERT or UPDATE query
	 * given by the sql parameter.
	 * @param sql The full SQL statement to be executed. Must be either an INSERT or UPDATE statement or
	 * combination of statements.
	 */
	private void executeInsertSQL(String sql)
	{
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
	}
	
	/**
	 * Creates a connection to the MySQL database to execute a DELETE query.
	 * @param sql The full SQL statement to be executed. Must be a DELETE statement.
	 */
	private void executeDeleteSQL(String sql)
	{
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
	}
}
