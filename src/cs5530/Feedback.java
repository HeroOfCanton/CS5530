package cs5530;

import java.sql.*;

public class Feedback {
	
	public Feedback() {}
	
	public boolean canGiveFeedback(String pid, String login, Statement stmt) {
		String fid = "";
		String sql="SELECT fid FROM Feedback "
				+  "WHERE pid ='" +pid +"' AND login = '" +login +"'";
		
		ResultSet rs = null;
		int count = 0;
		
		System.out.println("executing "+sql);
	 	try {
	 		rs = stmt.executeQuery(sql);
	 		while (rs.next()) {
	 			fid = rs.getString("fid");
	 			count++;
	 		}	 		
	 		// If count is < 1, that means there were no fid's to be found
	 		// for that pid and login. Thus, return true, as user can leave
	 		// feedback for that pid
	 		if (count < 1) {    
	 			 return true;
	 		}
	 		// There is an existing fid for that pid and login
	 		// return false, they can't leave more than one feedback
	 		else {
	 			return false;
	 		}
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
		return false;
	}

	public boolean addFeedback(String pid, 
							   String userName, 
							   String text, 
							   String score, 
							   String fbdate, 
							   Statement stmt) {
		
		String sql="INSERT INTO Feedback (pid, login, text, score, fbdate) "
				+  "VALUES ('" +pid +"','" +userName +"','" +text +"','" +score + "','" +fbdate +"');";
		
		int rs = 0;
		System.out.println("executing "+ sql);
	 	try {
	 		rs = stmt.executeUpdate(sql);
	 		
	 		if(rs > 0) {
	 			return true;
	 		}
	 		else {
	 			return false;
	 		}
	 	}
	 	catch(Exception e) {
	 		if(e.getMessage().equals("Duplicate entry '"+pid +"' for key 'PRIMARY'")) {
	 			System.out.println("Duplicate feedback attempt detected");
	 		}
	 	}
		return false;
	}

}
