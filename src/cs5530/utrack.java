package cs5530;

import java.io.*;

public class utrack {

	/**
	 * 
	 */
	public static void mainMenu() {
		System.out.println("	Welcome to the UTrack System - Main Menu	");
   	 	System.out.println("1. Login");
   	 	System.out.println("2. New User Registration");
   	 	System.out.println("3. Exit\n");
   	 	System.out.println("Please enter your choice:");
	}
	
	/**
	 * 
	 * @param userName
	 * @param userPassword
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public static String[] loginMenu(String userName, String userPassword, Connector con) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("	Login Menu	");
		System.out.println("Please enter your username:");
		while ((userName = in.readLine()) == null && userName.length() == 0);
		System.out.println("Please enter your password:");
		while ((userPassword = in.readLine()) == null && userPassword.length() == 0);
		
		Login login = new Login();
		String type = login.verifyLogin(userName, userPassword, con.stmt);
		
		String[] userArr = {userName, type};
		return userArr;
	}
	
	/**
	 * 
	 * @param con
	 * @return
	 * @throws IOException
	 */
	public static String registerMenu(Connector con) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String login, realName, password1, password2, city, state, telephone;		
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

			if(!(password1.equals(password2))) {
				System.out.println("Passwords to not match");
			}
			else {
				Register register = new Register();
				return register.registerUser(login, realName, password1, city, state, telephone, con.stmt);
			}
		}
	}
	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void adminMenu(Connector con) throws Exception {
		int c;
		String choice;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		admin: while(true) {
			System.out.println("	Admin Menu	");
			System.out.println("1. New POI");
	   	 	System.out.println("2. Modify Existing POI");
	   	 	System.out.println("3. Awards");
	   	 	System.out.println("4. Return to previous menu\n");
	   	 	System.out.println("Please enter your choice:");
	   	 	
		   	while ((choice = in.readLine()) == null && choice.length() == 0);
	 	 	try {
	 	 		c = Integer.parseInt(choice);
	 	 	}
	 	 	catch (Exception e) {
	 	 		continue;
	 	 	}
	 	 	
	 	 	switch(c) {
	 	 	case(1):
	 	 		newPOI(con);
	 	 		break;
	 	 	case(2):
	 	 		modPOI(con);
	 	 		break;
	 	 	case(3):
	 	 		awards(con);
	 	 		break;
	 	 	case(4):
	 	 	default:
	 	 		break admin;
	 	 	}
		}
	}
	
	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void newPOI(Connector con) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String name, city, state, url, telephone, yearest, hours, price, category;
		while(true) {
			System.out.println("	Add New POI	");
			System.out.println("Enter POI Name:");
			while ((name = in.readLine()) == null && name.length() == 0);
			System.out.println("Enter POI City:");
			while ((city = in.readLine()) == null && city.length() == 0);			
			System.out.println("Enter POI state as a 2-letter abreviation eg. UT:");
			while ((state = in.readLine()) == null && state.length() == 0);			
			System.out.println("Enter POI URL eg. www.somedomain.com:");
			while ((url = in.readLine()) == null && url.length() == 0);			
			System.out.println("Enter POI telephone with no spaces, or characters eg. 8015551234:");
			while ((telephone = in.readLine()) == null && telephone.length() == 0);			
			System.out.println("Enter 4 digit year established eg. 2015:");
			while ((yearest = in.readLine()) == null && yearest.length() == 0);			
			System.out.println("Enter hours of operation, separated by a dash, eg. 8-5, 9-6:");
			while ((hours = in.readLine()) == null && hours.length() == 0);			
			System.out.println("Enter avg price of a transaction:");
			while ((price = in.readLine()) == null && price.length() == 0);			
			System.out.println("Enter single word POI category, eg. Grocery, Services, Restaurant:");
			while ((category = in.readLine()) == null && category.length() == 0);

			POI poi = new POI();
			if(poi.newPOI(name, city, state, url, telephone, yearest, hours, price, category, con.stmt)) {
				System.out.println("New POI added successfully\n");
				break;
			}
			else {
				System.out.println("New POI NOT ADDED. Please try again\n");
			}
		}
	}
	
	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void modPOI(Connector con) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String updateField = null; 
		String name = null;
		String choice = null;
		String updateVar = null;
		int c = 0;
		
		mod: while(true) {
			System.out.println("	Add New POI	");
			System.out.println("Enter POI Name to modify:");
			while ((name = in.readLine()) == null && name.length() == 0);
			
			System.out.println("Choose field to modify:");
	   	 	System.out.println("1. POI City");
	   	 	System.out.println("2. POI State");
	   	 	System.out.println("3. POI URL");
	   	 	System.out.println("4. POI Telephone");
	   	 	System.out.println("5. POI Year Established");
	   	 	System.out.println("6. POI Hours of Operation");
	   	 	System.out.println("7. POI Avg. Price");
	   	 	System.out.println("8. POI Category");
	   	 	System.out.println("9. Return to Previous Menu");
	   	 	
	   	 	while ((choice = in.readLine()) == null && choice.length() == 0);
		 	try {
		 		c = Integer.parseInt(choice);
		 	}
		 	catch (Exception e) {
		 		continue;
		 	}
		 	
		 	switch(c) {
	 	 	case(1):
	 	 		updateField = "city";
	 	 		break;
	 	 	case(2):
	 	 		updateField = "state";
	 	 		break;
	 	 	case(3):
	 	 		updateField = "url";
	 	 		break;
	 	 	case(4):
	 	 		updateField = "telephone";
	 	 		break;
	 	 	case(5):
	 	 		updateField = "yearest";
	 	 		break;
	 	 	case(6):
	 	 		updateField = "hours";
	 	 		break;
	 	 	case(7):
	 	 		updateField = "price";
	 	 		break;
	 	 	case(8):
	 	 		updateField = "category";
	 	 		break;
	 	 	case(9):
	 	 	default:
	 	 		break mod;
	 	 	}
		 	
		 	System.out.println("Enter new information:");
			while ((updateVar = in.readLine()) == null && updateVar.length() == 0);
			
		 	POI poi = new POI();
			if(poi.updatePOI(name, updateField, updateVar, con.stmt)) {
				System.out.println("POI updated successfully\n");
				break;
			}
			else {
				System.out.println("POI NOT CHANGED. Please try again\n");
				break;
			}
		}		
	}
	
	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void awards(Connector con) throws Exception{
		
	}
	
	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public static void userMenu(String userName, Connector con) throws Exception {
		int c;
		String choice;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		user: while(true) {
			System.out.println("	User Menu	");
			System.out.println("1. Browse POI");
	   	 	System.out.println("2. Give Feedback");
	   	 	System.out.println("3. Manage Favorite");
	   	 	System.out.println("4. Record Visit");
		 	System.out.println("5. Manage Trusted Users");
		 	System.out.println("6. Return to Previous Menu\n");
	   	 	System.out.println("Please enter your choice:");
	   	 	
	   	 	while ((choice = in.readLine()) == null && choice.length() == 0);
		 	try {
		 		c = Integer.parseInt(choice);
		 	}
		 	catch (Exception e) {
		 		continue;
		 	}
		 	
		 	switch(c) {
	 	 	case(1):
	 	 		browsePOI();
	 	 		break;
	 	 	case(2):
	 	 		feedback();
	 	 		break;
	 	 	case(3):
	 	 		favorites(userName, con);
	 	 		break;
	 	 	case(4):
	 	 		visit(con);
	 	 	case(5):
	 	 		trusted(con);
	 	 	case(6):
	 	 	default:
	 	 		break user;
	 	 	}
		}
	}
	
	public static void browsePOI() {
		
	}
	
	public static void feedback() {
			
	}
	
	/**
	 * 
	 * @param userName
	 * @param con
	 * @throws Exception 
	 */
	public static void favorites(String userName, Connector con) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Favorite favorite = new Favorite();
		
		// Function to check to see if user has favorite
		String existingFavorite = favorite.hasFavorite(userName, con.stmt);
		
		String newVar = null;			// User's new favorite name
		String changeFavorite = null;	// User's decision to change favorite
		String changeVar = null;		// User's change favorite name
		
		// User has no favorite, so ask them for it, and save it
		if(existingFavorite.equals("")) {
			System.out.println("You don't have a favorite already selected.");
			System.out.println("Please enter the name of your favorite POI");
			while ((newVar = in.readLine()) == null && newVar.length() == 0);
			if(favorite.saveFavorite(userName, newVar, con.stmt)) {
				System.out.println("Your Favorite has been recorded\n");
			}
			else {
				System.out.println("Favorite not saved. Try again\n");
			}
		}
		// User has a favorite, so display it, then ask if they want to change it
		else {
			System.out.println("Your current favorite is: " +existingFavorite);
			System.out.println("Would you like to change it? Y/N");
			while ((changeFavorite = in.readLine()) == null && changeFavorite.length() == 0);
			// They want to change it
			if(changeFavorite.equals("Y") || changeFavorite.equals("y")) {
				System.out.println("What would you like to change it to?");
				while ((changeVar = in.readLine()) == null && changeVar.length() == 0);
				if(favorite.changeFavorite(userName, changeVar, con.stmt)) {
					System.out.println("Your favorite has been changed\n");
				}
				else {
					System.out.println("Your favorite has not been changed. Try again.\n");
				}
			}
			// They don't want to change it
			else if(changeFavorite.equals("N") || changeFavorite.equals("n")) {
				System.out.println("Then why are you here?");
				return;
			}
			// They're being cheeky
			else {
				System.out.println("You didn't answer Y or N. Goodbye.");
				System.exit(0);
			}
		}
	}
	
	public static void visit(Connector con) throws Exception {
		
	}
	
	public static void trusted(Connector con) throws Exception {
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connector con = null;
		String choice;
        String userName = null;
        String userPassword = null;
        String userType = null;
        String regSuccess = null;
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
		    		String[] userInfo = loginMenu(userName, userPassword, con);
		    		userName = userInfo[0];
		    		userType = userInfo[1];
		    		
		    		if(userType.equals("false")) {
		    			System.out.println("Passwords do not match, please try again");
		    		}
		    		else if(userType.equals("mismatch")) {
		    			System.out.println("Login does not exist. Please register as a new user\n");
		    			continue;
		    		}
		    		else if(userType.equals("admin")) {
		    			adminMenu(con);
		    		}
		    		else {
		    			userMenu(userName, con);
		    		}
		    	}
		    	else if (c==2) {	 
		    		regSuccess = registerMenu(con);
		    		if(regSuccess.equals("success")) {
		    			System.out.println("Thank you for registering. You may now login");
		    		}
		    		else {
		    			System.out.println("Registration failed. Please try again\n");
		    		}
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
