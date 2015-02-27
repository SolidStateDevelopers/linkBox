/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.csupomona.cs480.data.provider;

import edu.csupomona.cs480.data.SaveData;
import java.util.List;
import java.util.ArrayList;

public interface SaveManager {

	/**
	 * Get the user information object based on
	 * the given userId.
	 * <p>
	 * If the user does not exist, simply create
	 * one.
	 *
	 * @param userId
	 * @return the User object
	 */
	public ArrayList<SaveData> getBookmark(String bookmark);

	/**
	 * Update the given user object and persist it.
	 * <p>
	 * If the user does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param user object
	 */
	public void updateData(SaveData data);

	/**
	 * Delete the given user from the storage.
	 *
	 * @param userId
	 */
	public void deleteData(SaveData data);

	/**
	 * List all the current users in the storage.
	 *
	 * @return
	 */
	public ArrayList<SaveData> listAllData();   
}
