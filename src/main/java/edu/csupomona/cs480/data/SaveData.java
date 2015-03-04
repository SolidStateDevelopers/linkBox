package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic user object.
 */
public class SaveData {


    private String id;
    private String bookmark;
    private String category;
    private short isPublic;
    
    public void setId(String id)
    {
       this.id = id;
    }
    
    public String getId()
    {
       return id;
    }
    public String getBookmark()
    {
       return bookmark;
    }
    
    public String getCategory()
    {
       return category;
    }
    public void setBookmark(String bookmark)
    {
       this.bookmark = bookmark;
    }
    public void setCategory(String category)
    {
       this.category = category;
    }
    
    public void setPublic(Short isPublic)
    {
    	this.isPublic = isPublic;
    }
    
    public boolean getPublic()
    {
    	if (isPublic == 0)
    		return false;
    	else
    		return true;
    }
}
