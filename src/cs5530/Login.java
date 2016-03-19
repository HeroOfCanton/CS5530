package cs5530;

import java.sql.*;

public class Login {
	
	public Login() {}
	
	public String verifyLogin(String _login, String _password, Statement stmt)
	{
		String sql="SELECT login, password FROM Users "
				+  "WHERE login ='" +_login +"'";
		String output="";
		ResultSet rs=null;
		
//		System.out.println("executing "+sql);
	 	try {
	 		rs = stmt.executeQuery(sql);
	 		
	 		// Check to see if query was empty
	 		if (!rs.next()) {    
	 			 return "mismatch";
	 		}
	 		while (rs.next()) {
//	 			System.out.println(rs.getString("password"));
// 				System.out.println(_password);
	 			if(rs.getString("password") == _password)
	 			{
	 				output+=rs.getString("type");
	 			}
	 			else
	 			{
	 				return "false";
	 			}
	 		}
	 		rs.close();
	 	}
	 	catch(Exception e) {
	 		System.out.println("Database error. Please contact System Administrator");
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
	    return output;
	}
}
