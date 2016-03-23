package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class POI {
	public POI() {}
	
	public boolean newPOI(String name, 
						  String city, 
						  String state, 
						  String url, 
						  String telephone, 
						  String yearest, 
						  String hours, 
						  String price, 
						  String category, 
						  Statement stmt) {
		
		String sql="INSERT INTO POI (name, city, state, url, telephone, yearest, hours, price, category) "
				+  "VALUES ('" +name +"','" +city +"','" +state +"','" +url + "','" +telephone +"','" +yearest +"','" +hours +"','" +price + "','" +category +"');";
		
		int rs = 0;
		//System.out.println("executing "+ sql);
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
	 		if(e.getMessage().equals("Duplicate entry '"+name+"' for key 'PRIMARY'")) {
	 			System.out.println("Duplicate POI name detected");
	 		}
	 		else {
	 			System.err.println(e.getMessage());
	 		}
	 	}
		return false;
	}

	public boolean updatePOI(String name, 
							 String updateField, 
							 String updateVar,
							 Statement stmt) {
		
		String sql = "UPDATE POI "
					+"SET " +updateField +"='" +updateVar +"'"
					+" WHERE name='" +name +"';";
		
		int rs = 0;
		//System.out.println("executing "+ sql);
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
	 		System.err.println(e.getMessage());
	 	}
		return false;
	}
	
	public void browsePOI() {
		
	}
	
	public String getPid (String name, Statement stmt) {
		String pid = "";		
		String sql="SELECT pid FROM POI "
				+  "WHERE name ='" +name +"'";		
		ResultSet rs = null;
		int count = 0;
		
//		System.out.println("executing "+sql);
	 	try {
	 		rs = stmt.executeQuery(sql);
	 		while (rs.next()) {
	 			pid = rs.getString("pid");
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
		return pid;
	}

	public void getPrice(String lowPrice, String highPrice, Statement stmt) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String[]> getPopular(Connector con) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String[]> getExpensive(Connector con) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String[]> getRated(Connector con) {
		return null;
		// TODO Auto-generated method stub
		
	}
}
