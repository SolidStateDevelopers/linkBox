/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.SaveData;
import edu.csupomona.cs480.data.SaveMap;
import edu.csupomona.cs480.util.ResourceResolver;




public class FSSaveManager implements SaveManager
{
   private static final ObjectMapper JSON = new ObjectMapper();
   
	private SaveMap getSaveMap() {
		SaveMap saveMap = null;
		File userFile = ResourceResolver.getUserFile();
        if (userFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
				saveMap = JSON.readValue(userFile, SaveMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	saveMap = new SaveMap();
        }
        return saveMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistSaveMap(SaveMap saveMap) {
		try {
			// convert the user object to JSON format
            JSON.writeValue(ResourceResolver.getUserFile(), saveMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public SaveData getBookmark(String bookmark) {
		SaveMap saveMap = getSaveMap();
        return saveMap.get(bookmark);
	}

	@Override
	public void updateData(SaveData data) {
		SaveMap saveMap = getSaveMap();
		saveMap.put(data.getBookmark(), data);
		persistSaveMap(saveMap);
	}

	@Override
	public void deleteData(String bookmark) {
		SaveMap saveMap = getSaveMap();
		saveMap.remove(bookmark);
		persistSaveMap(saveMap);
	}

	@Override
	public List<SaveData> listAllData() {
		SaveMap saveMap = getSaveMap();
		return new ArrayList<SaveData>(saveMap.values());
	}
}
