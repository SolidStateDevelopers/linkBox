package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic user object.
 */
public class SaveData {



    private String bookmark;
    private String category;
    
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
    
}
