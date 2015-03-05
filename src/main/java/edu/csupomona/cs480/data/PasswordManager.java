package edu.csupomona.cs480.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * This class handles any encryption or decryption that needs to be done
 */
public class PasswordManager {
	
	/*
	 * This method takes any string and returns the result of a SHA 256 hash on that string.
	 */
	public String hashStringWithSHA256(String pass) throws NoSuchAlgorithmException
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
}
