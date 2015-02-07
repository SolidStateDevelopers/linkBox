package edu.csupomona.cs480.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;


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
        return "OK";
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
    User getUser(@PathVariable("userId") String userId, @PathVariable("password") String password) {
        System.out.println("Got here");
    	User user = userManager.getUser(userId, password);
        System.out.println("Now I'm here!");
        if(user.getPass().equals(password)){
            System.out.println("got it!");
            return user;
        } else {
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
    User updateUser(
    		@PathVariable("userName") String id,
                @RequestParam("password") String password,
                @RequestParam(value = "lastName", required = false) String Lname,
    		@RequestParam(value = "firstName", required = false) String Fname) {
    	User user = new User();
    	user.setId(id);
    	user.setLName(Lname);
    	user.setFName(Fname);
        user.setPass(password);
    	userManager.updateUser(user);
    	return user;
    }

    /**
     * This API deletes the user. It uses HTTP DELETE method.
     *
     * @param userId
     */
    @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
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

    /*********** Web UI Test Utility **********/
    /**
     * This method provide a simple web UI for you to test the different
     * functionalities used in this web service.
     */
    @RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
    ModelAndView getUserHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", listAllUsers());
        return modelAndView;
    }

}