package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic user object.
 */
public class User {

	/** The unique user Id */
    private String id;
    /** The user First Name */
    private String Fname;
    /** The user Last Name */
    private String Lname;
    /** The unique user password */
    private String password;
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getFName() {
		return Fname;
	}
        
        public String getLName() {
		return Lname;
	}

	public void setFName(String First_name) {
		this.Fname = First_name;
	}
        
        public void setLName(String Last_name) {
		this.Lname = Last_name;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
}
