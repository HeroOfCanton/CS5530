package cs5530;

import java.sql.*;
import java.util.Date;
import java.text.*;

public class Visit {
	
	Statement _stmt;
	
	public Visit() {}

	public boolean addtoDB(String userName, 
						   String pid, 
						   String cost, 
						   String numofheads, 
						   String visitdate,
						   Statement stmt) {
		
		_stmt = stmt;
		String vid = addtoVisEvent(cost, numofheads, _stmt);
		
		if(vid.equals("") || vid.equals(null)) {
			return false;
		}
		
		String sql="INSERT INTO Visit (login, pid, vid, visitdate) "
				  + "VALUES ('"+userName +"', '" +pid +"', '" +vid +"', '" +visitdate +"');";
				
		int success = 0;
		System.out.println("executing "+ sql);
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
	 		System.out.println(e.getMessage());
	 	}		
		return false;
	}
	
	private String addtoVisEvent(String cost, String numofheads, Statement stmt) {
		String autovalue;
		String sql = "INSERT INTO VisEvent (cost, numofheads) "
				+    "VALUES('" +cost +"', '" +numofheads +"');";
		
		int success = 0;
		System.out.println("executing "+ sql);
	 	try {
	 		
	 		success = stmt.executeUpdate(sql);
	 		
	 		if(success > 0) {
	 			autovalue = lastid(_stmt);
	 			return autovalue;
	 		}
	 		else {
	 			return "";
	 		}
	 	}
	 	catch(Exception e) {
	 		System.out.println(e.getMessage());
	 	}		
		return "";
	}
	
	private String lastid(Statement stmt) {
		String sql="SELECT last_insert_id()";
		String output="";
		ResultSet rs = null;
		int count = 0;
		
//		System.out.println("executing "+sql);
	 	try {
	 		rs = stmt.executeQuery(sql);
	 		while (rs.next()) {
	 			output = rs.getString("last_insert_id()");
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
	    return output;
	}
	
}
