package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtility {
	
	  private static ConnectionFactoryUtility instance;
	  
	  private ConnectionFactoryUtility() {
	        super();
	    }

	
	
	public static ConnectionFactoryUtility getInstance(){
		 if(instance == null) {
	            instance = new ConnectionFactoryUtility();
		 }
		
		return instance;
		
	}
	
	public static Connection getConnection() throws SQLException {
		
		try {
    		Class.forName("org.postgresql.Driver"); //try to find and return the psotgresql Driver Class
    	} catch (ClassNotFoundException e) {
    		System.out.println("CLASS WASN'T FOUND");
    		e.printStackTrace(); //this will print the exception message to the console
    	}
    	
    	//we need to provide our database credentials
    	//we'll hardcode them for now, but I'll show a way to hide the credentials in environment variables
    	
    	//the url to my database schema
    	String url = "jdbc:postgresql://java-fullstack-aws.chem9piwxtkd.us-east-1.rds.amazonaws.comjava-fullstack-aws.chem9piwxtkd.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=p1";
    	//your postgres username (should just be postgres)
    	String username = "postgres";
    	//your postgres password (hopefully just "password")
    	String password = "password"; //It had better be password
    
    	//This is what actually returns our Connection object. (Note how this method has a return type of Connection)
    	return DriverManager.getConnection(url, username, password);
		
		
	}

}
