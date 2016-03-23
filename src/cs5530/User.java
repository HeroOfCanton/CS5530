package cs5530;

import java.sql.*;

public class User {
	
	public User () {}
	
	public String getLogin(String realName, Statement stmt) {
		String login = "";
		String sql = "SELECT login FROM Users"
				+ 	 " WHERE name = '" +realName + "';";
		
		ResultSet rs = null;
		int count = 0;
		
//		System.out.println("executing "+sql);
	 	try {
	 		rs = stmt.executeQuery(sql);
	 		while (rs.next()) {
	 			login = rs.getString("login");
	 			count++;
	 		}	 		
	 		// Check to see if query was empty
	 		if (count < 1) {    
	 			 return "";
	 		}
	 		rs.close();
	 	}
	 	catch(Exception e) {
	 		System.out.println("Database error. Please contact System Administrator");
	 		System.err.println(e.getMessage());
	 	}
	 	finally {
 			try {
	 			if (rs!=null && !rs.isClosed())
	 				rs.close();
	 		}
	 		catch(Exception e) {
	 			System.out.println("Can not close resultset");
	 		}
	 	}
		return login;
	}

	public boolean setTrust(String userName, String otherLogin, String trustVar, Statement stmt) {
		int trustVal;
		
		if(trustVar.equals("trusted")) {
			trustVal = 1;
		}
		else {
			trustVal = 0;
		}
		
		String sql="INSERT INTO Trust (login1, login2, isTrusted) "
				  + "VALUES ('" +userName +"', '" +otherLogin +"', '" +trustVal +"');";
				
		int success = 0;
//				System.out.println("executing "+ sql);
	 	try {
	 		success = stmt.executeUpdate(sql);
	 		
	 		if(success > 0) {
	 			return true;
	 		}
	 		else {
	 			return false;
	 		}
	 	}
	 	catch(Exception e) {
	 		if(e.getMessage().equals("Duplicate entry '"+userName +"' for key 'PRIMARY'")) {
	 			System.out.println("Duplicate trust attempt detected");
	 		}
	 		else {
	 			System.out.println(e.getMessage());
	 		}
	 	}
		return false;
	}

}
