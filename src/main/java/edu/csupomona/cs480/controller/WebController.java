package edu.csupomona.cs480.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Stopwatch;
import com.google.common.collect.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import PingTest.PingTester;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.DataManager;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.csupomona.cs480.data.provider.SQLLogin;


import edu.csupomona.cs480.data.SaveData;
import edu.csupomona.cs480.data.provider.SaveManager;
import java.security.NoSuchAlgorithmException;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private SaveManager saveManager;

    
    /**
     * My test HTTP API @curvejumper(michael ortiz)
     **/
    @RequestMapping(value = "/curvejumper", method = RequestMethod.GET)
    String curveCheck() {
    	return "Hey, it's Michael!";
    }
    
    
    /**
     * This is a simple example of how the HTTP API works.
     * It returns a String "OK" in the HTTP response.
     * To try it, run the web application locally,
     * in your web browser, type the link:
     * 	http://localhost:8080/cs480/ping
     */
    @RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
    String healthCheck() {
    	// You can replace this with other string,
    	// and run the application locally to check your changes
    	// with the URL: http://localhost:8080/
        PingTester test = PingTester.getInstance();
        return test.getPing();
    }
	@RequestMapping(value = "/cs480/dhreply", method = RequestMethod.GET)
    String dhreply() 
	{
        return "David Ho's webpage";
    }
    @RequestMapping(value = "/cs480/dalin1120", method = RequestMethod.GET)
    String tester()
    {
       return "Daniel Lin has edited this";
    }
    
    @RequestMapping(value = "/cs480/tjmadison", method = RequestMethod.GET)
    String tjmadsionRep()
    {
       return "Better late than never";
    }

    /**
     * This is a simple example of how to use a data manager
     * to retrieve the data and return it as an HTTP response.
     * <p>
     * Note, when it returns from the Spring, it will be
     * automatically converted to JSON format.
     * <p>
     * Try it in your web browser:
     * 	http://localhost:8080/cs480/user/user101
     */
    @RequestMapping(value = "/user/{userId}/{password}", method = RequestMethod.GET)
    ArrayList<SaveData> getUser(@PathVariable("userId") String userId, @PathVariable("password") String password) throws NoSuchAlgorithmException {
    	/*User user = userManager.getUser(userId, password);
        if(user.getPass().equals(password)){
            System.out.println("in userpass");
            return user;
        } else {
            return null;
        }*/
    	DataManager dm = new DataManager();
    	if (dm.logInUser(userId, password))
    	{
    		ArrayList<SaveData> al = dm.getLinks(userId);
    		return al;
    	}
    	else
    	{
    		try
    		{
    			double d = 1/0;
    		}
    		catch(Exception E)
    		{
    			throw E;
    		}
    		return null;
    	}
    }

    /**
     *
     * Note, the URL will not work directly in browser, because
     * it is not a GET request. You need to use a tool such as
     * curl.
     * 
     * The way this url is called is from userData.js
     * The ajax function will call the url '/user/{userName} and
     * then this mapping takes over.
     * 
     * The user information is then sent to the 'userManager' class which will 
     * add that user info to a JSON file. 
     * 
     *
     * @param id
     * @param Fname
     * @param Lname
     * @param password
     * @return
     */
    @RequestMapping(value = "/user/{userName}", method = RequestMethod.POST)
    void updateUser(
    		@PathVariable("userName") String id,
                @RequestParam("password") String password,
                @RequestParam(value = "lastName", required = false) String Lname,
    		@RequestParam(value = "firstName", required = false) String Fname) throws NoSuchAlgorithmException {
    	DataManager dm = new DataManager();
        System.out.println("id: " + id + " password: " + password + " Lname: " + Lname + " Fname: " + Fname);
        dm.addUser(id, password, Fname, Lname);
    }
    
    /*
     * Added a method that asks for user name and password
     * This method will be used for future MySQL login
     * 
     */
    @RequestMapping(value = "/login/access", method = RequestMethod.POST)
    String userLogin(@RequestParam("user") String userName,
    				@RequestParam("pass") String password)
    {	
    	try
    	{
    		SQLLogin sl = new SQLLogin();
    		sl = sl.getInfo();
    		String login = sl.getLogin();
    		String sqlPass = sl.getPassword();
    		String address = sl.getAddress();
    		String dbname = sl.getdbName();
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		Connection conn = DriverManager.getConnection("jdbc:mysql://"+address+":3306/"+dbname,login,sqlPass);
    		/*
    		 * Do link stuff here
    		 * Call link methods here
    		 */
    		
    		conn.close();
    	}
    	catch (Exception e)
    	{
    		return "Connection error";
    	}
    	
    	return "Login succesful!";
    }

    /**
     * This API deletes the user. It uses HTTP DELETE method.
     *
     * @param userId
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    void deleteUser(
    		@PathVariable("userId") String userId) {
    	userManager.deleteUser(userId);
    }

    /**
     * This API lists all the users in the current database.
     *
     * @return
     */
    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    List<User> listAllUsers() {
    	return userManager.listAllUsers();
    }
    
    @RequestMapping(value = "/cs480/dalin1120guava", method = RequestMethod.GET)
    String guavaTestMethod()
    {
       Stopwatch watchTester = Stopwatch.createStarted();
       return "Hello, the timer has been initialized but not started.";
       
    }
    /*********** Web UI Test Utility **********/
    /**
     * This method provide a simple web UI for you to test the different
     * functionalities used in this web service.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    ModelAndView getUserHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", listAllUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/public/{userId}", method = RequestMethod.GET)
    ModelAndView getPublicLinks(@PathVariable("userId") String userId) {
        DataManager dm = new DataManager();
        
        ModelAndView modelAndView = new ModelAndView("PublicLinks");
        modelAndView.addObject("bookmarks", dm.getPublicLinks(userId));
        return modelAndView;
    }
    
    //Placeholder for the user submit page
    @RequestMapping(value = "/cs480/submit/list", method = RequestMethod.GET)
    List<SaveData> listAllData() {
       return saveManager.listAllData();
    }
    
    @RequestMapping(value = "/cs480/BookmarkController/{userId}/{category}", method = RequestMethod.GET)
    ModelAndView getBookmarkPage(@PathVariable("userId") String userId, @PathVariable("category") String category) {
        DataManager dm = new DataManager();
        
        ModelAndView modelAndView = new ModelAndView("userLinks");
        modelAndView.addObject("bookmarks", dm.getLinksByCategory(userId, category));
        return modelAndView;
    }
    
    @RequestMapping(value = "/cs480/BookmarkController/{userId}", method = RequestMethod.GET)
    ModelAndView getBookmarkPage(@PathVariable("userId") String userId) {
        DataManager dm = new DataManager();
        
        ModelAndView modelAndView = new ModelAndView("BookmarkController");
        modelAndView.addObject("bookmarks", dm.getCategories(userId));
        return modelAndView;
    }
    

      
    @RequestMapping(value = "/cs480/BookmarkController/{userId}", method = RequestMethod.POST)
    public @ResponseBody ModelAndView handleBookmark(
         @PathVariable("userId") String userId,
         @RequestParam("Bookmark") String bookmark,
         @RequestParam("Category") String category) 
         {
            try
            {
            	System.out.println("This user ID is : " + userId);
            	System.out.println("This is the bookmark: " + bookmark);
            	System.out.println("This is the catgegory: " + category);
            	DataManager dm = new DataManager();
            	dm.addLink(userId, bookmark, category);
            	
               /*SaveData data = new SaveData();
               data.setBookmark(bookmark);
               data.setCategory(category);
               data.setId(userId);
               saveManager.updateData(data);
               */
               ModelAndView modelAndView = new ModelAndView("BookmarkController");
               modelAndView.addObject("bookmarks", dm.getLinks(userId));
               return modelAndView;
            }
            catch(Exception e)
            {
               DataManager dm = new DataManager();
               ModelAndView modelAndView = new ModelAndView("Error");
               modelAndView.addObject("bookmarks", dm.getLinks(userId));
               return modelAndView;
            }
         }
    
}