package edu.csupomona.cs480.data.provider;

import java.io.*;

public class SQLLogin 
{
	private String login;
	private String password;
	private String dbName;
	private String address;
	
	public SQLLogin getInfo()
	{
		SQLLogin sl = new SQLLogin();
		try
		{
			FileReader fr = new FileReader("SQLInfo.txt");
			BufferedReader br = new BufferedReader(fr);
			
			login = br.readLine();
			password = br.readLine();
			dbName = br.readLine();
			address = br.readLine();
		}
		catch(Exception e)
		{
			System.out.println("SQL info not found.");
			return null;
		}
		return sl;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getdbName()
	{
		return dbName;
	}
	
	public String getAddress()
	{
		return address;
	}
}
