/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csupomona.cs480;

import edu.csupomona.cs480.data.User;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author michael
 */
public class UserTest {
    
    User testUser = new User();
    
    public UserTest() {
        testUser.setId("curvejumper");
        testUser.setFName("mike");
        testUser.setLName("ortiz");
        testUser.setPass("12345");
        
        
    }
    
    @Test
    public void addUser() {
        assertEquals("curvejumper", testUser.getId());
        assertEquals("mike", testUser.getFName());
        assertEquals("ortiz", testUser.getLName());
        assertEquals("12345", testUser.getPass());
    }
}
