package cs5530;

import java.sql.*;

public class Register {
	
	public Register() {}
	
	public String registerUser(String _login, 
							 String _realName, 
							 String _password1, 
							 String _city, 
							 String _state, 
							 String _telephone, 
							 Statement stmt) {
		
		String sql="SELECT login, password FROM Users "
				+  "WHERE login ='" +_login +"'";
		
		int rs = 0;
//		System.out.println("executing "+ sql);
	 	try {
	 		rs = stmt.executeUpdate(sql);
	 		
	 		if(rs > 0) {
	 			return "success";
	 		}
	 		
	 		else {
	 			return "fail";
	 		}
	 	}
	 	catch(Exception e) {
	 		System.out.println("Database error. Please contact System Administrator");
	 	}
		return "fail";
	}
}
