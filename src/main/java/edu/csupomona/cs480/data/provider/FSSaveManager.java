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
		File bookmarkFile = ResourceResolver.getBookmarkFile();
        if (bookmarkFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
				saveMap = JSON.readValue(bookmarkFile, SaveMap.class);
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
            JSON.writeValue(ResourceResolver.getBookmarkFile(), saveMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public ArrayList<SaveData> getBookmark(String bookmark) {
		SaveMap saveMap = getSaveMap();
      return saveMap.get(bookmark);
	}

	@Override
	public void updateData(SaveData data) {
		SaveMap saveMap = getSaveMap();
      ArrayList<SaveData> bookmarkList = saveMap.get(data.getBookmark());
      if(bookmarkList != null)
      {
         bookmarkList.add(data);
         saveMap.put(data.getBookmark(), bookmarkList);
      }
      else
      {
         bookmarkList = new ArrayList<SaveData>();
         bookmarkList.add(data);
         saveMap.put(data.getBookmark(), bookmarkList);
         
      }
      
		persistSaveMap(saveMap);
	}

	@Override
	public void deleteData(SaveData data) {
		SaveMap saveMap = getSaveMap();
      ArrayList<SaveData> bookmarkList = saveMap.get(data.getBookmark());
      if(bookmarkList != null)
      {
         bookmarkList.remove(data);
         saveMap.put(data.getBookmark(), bookmarkList);
         persistSaveMap(saveMap);
      }
      else
      {
         System.out.println("Error");
      }
	}

	@Override
	public ArrayList<SaveData> listAllData() {
		SaveMap saveMap = getSaveMap();
		ArrayList<SaveData> totalBookmarks = new ArrayList<SaveData>();
      for(String key : saveMap.keySet())
      {
         totalBookmarks.addAll(saveMap.get(key));
      }
      return totalBookmarks;
	}
   
}
