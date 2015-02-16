/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.csupomona.cs480;

import edu.csupomona.cs480.data.SaveData;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookmarkTest 
{
   SaveData test = new SaveData();

   public BookmarkTest()
   {
      test.setBookmark("www.github.com");
      test.setCategory("Repository");     
   }
   @Test
   public void addBookmark()
   {
      assertTrue("www.github.com", test.getBookmark() == "www.github.com");
      assertTrue("Repository", test.getCategory() == "Repository");
   }
}
