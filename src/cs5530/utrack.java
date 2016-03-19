package cs5530;

import java.lang.*;
import java.sql.*;
import java.io.*;

public class utrack {

	/**
	 * @param args
	 */
	public static void mainMenu() {
		System.out.println("	Welcome to the UTrack System - Main Menu	");
   	 	System.out.println("1. Login");
   	 	System.out.println("2. New User Registration");
   	 	System.out.println("3. Exit\n");
   	 	System.out.println("Please enter your choice:");
	}
	
	public static String loginMenu(String userName, String userPassword, BufferedReader in, Connector con) throws Exception {
		System.out.println("	Login Menu	");
		System.out.println("Please enter your username:");
		while ((userName = in.readLine()) == null && userName.length() == 0);
		System.out.println("Please enter your password:");
		while ((userPassword = in.readLine()) == null && userPassword.length() == 0);
		
		Login login = new Login();
		return login.verifyLogin(userName, userPassword, con.stmt);
	}
	
	public static void registerMenu(Connector con, BufferedReader in) throws IOException {
		String login;
		String realName;
		String password1;
		String password2;
		
		String city;
		String state;
		String telephone;
		
		while(true) {
			System.out.println("	Registration Menu	");
			System.out.println("Please enter your desired login:");
			while ((login = in.readLine()) == null && login.length() == 0);			
			System.out.println("Please enter your First and Last Name, eg. John Smith:");
			while ((realName = in.readLine()) == null && realName.length() == 0);			
			System.out.println("Please enter your desired password:");
			while ((password1 = in.readLine()) == null && password1.length() == 0);			
			System.out.println("Verify your password:");
			while ((password2 = in.readLine()) == null && password2.length() == 0);			
			System.out.println("Please enter your city:");
			while ((city = in.readLine()) == null && city.length() == 0);			
			System.out.println("Please enter your state as a 2-letter abreviation eg. UT:");
			while ((state = in.readLine()) == null && state.length() == 0);
			System.out.println("Please enter your telephone with no spaces, or characters eg. 8015551234:");
			while ((telephone = in.readLine()) == null && telephone.length() == 0);
			
			if(password1 != password2) {
				System.out.println("Passwords to not match");
			}
			else {
				Register register = new Register();
				register.registerUser(login, realName, password1, city, state, telephone, con.stmt);
			}
		}
	}
	
	public static void adminMenu() {
		System.out.println("	Admin Menu	");
		System.out.println("1. New POI");
   	 	System.out.println("2. Modify Existing POI");
   	 	System.out.println("3. Awards");
   	 	System.out.println("4. Return to previous menu\n");
   	 	System.out.println("Please enter your choice:");
	}
	
	public static void userMenu() {
		System.out.println("	User Menu	");
		System.out.println("1. Browse POI");
   	 	System.out.println("2. Give Feedback");
   	 	System.out.println("3. Manage Favorite");
   	 	System.out.println("4. Record Visit");
	 	System.out.println("5. Manage Trusted Users\n");
   	 	System.out.println("Please enter your choice:");
	}
	
	public static void main(String[] args) {
		Connector con = null;
		String choice;
        String userName = null;
        String userPassword = null;
        String userType = null;
        String sql = null;
        int c = 0;

		try {
			//remember to replace the password
		 	con= new Connector();
		    System.out.println ("Database connection established");
		 
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    
		    while(true) {
		    	mainMenu();
		    	// get the users input
		    	while ((choice = in.readLine()) == null && choice.length() == 0);
	    	 	try {
	    	 		c = Integer.parseInt(choice);
	    	 	}
	    	 	catch (Exception e) {
	    	 		continue;
	    	 	}
		    	 	
		    	if (c < 1 || c > 3) {
		    		 continue;
		    	}
		    	if (c == 1) {
		    		userType = loginMenu(userName, userPassword, in, con); 
		    		if(userType == "false") {
		    			System.out.println("Passwords do not match, please try again");
		    		}
		    		else if(userType == "mismatch") {
		    			System.out.println("Login does not exist. Please register as a new user\n");
		    			continue;
		    		}
		    		else if(userType == "admin"){
		    			adminMenu();
		    		}
		    		else {
		    			userMenu();
		    		}
		    	}
		    	else if (c==2) {	 
		    		registerMenu(con, in);
		    		System.out.println("Thank you for registering. You may now login");
		    	}
		    	else {   
		    		System.out.println("Thank you for using UTrack");
		        	con.stmt.close(); 
		        	break;
		        	}
		        }
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println ("Either connection error or query execution error!");
		}
		finally {
			if (con != null) {
				try {
					con.closeConnection();
					System.out.println ("Database connection terminated");
				}
				catch (Exception e) { /* ignore close errors */ }
			}	 
		}
	}
}
