package Service;

import Models.Users;
import Repository.User_DAO;

public class Auth_Services {
	/**
	 * Note: userToBeRegistered will have on id=0.
	 * After registration, the id will be a positive integer.
	 **/
	// making a new user object
	public static int register(Users userToBeRegistered) {
		
		// checking if the username already exists in the database 
		// if the method returns null, the username is available
		if(User_DAO.getByUserName(userToBeRegistered.getUserName()) != null){
			
			 // Throws new NullPointerException("Username already taken");
		}
		
		// take in the user object sent from the menu and send it to the user_DAO to be inserted into the database
		//After the entry has been made, the ID of the new user is immediately return
		return User_DAO.create(userToBeRegistered);
		
	}
	
	/**
	 * The login method is used to check the information given and verify their credentials
	 * 
	 * @return Users object
	 */
	public static Users login(String username, String password) {
		
		// Instantiating a temporary user
		Users user;
		
		//The try-catch block will catch any exception thrown by the User_DAO methods
		try {
			// Retrieving the user data from the database from the username given
			user = User_DAO.getByUserName(username);
			
			//These conditional statements are checking various contingencies
			//The first is checking if the user exists and that the password given matches the one stored
			
			if(user != null && password.equals(user.getPassword())) {
				//If this one is true, the user object is returned and login is deemed successful
				System.out.println("Logged In Successfully");
				return user;
				
			// The second is checking if the user exists and the password given is different than the one stored
				
			} else if(user != null && !password.equals(user.getPassword())) {
				// if this one is true and the previous false, a null object is returned and login is deemed unsuccessfully
				System.out.println("Wrong Password");
				return null;
				
			// the third is the final contingency and will only occur if the username does not exist in the database
				
			
			} else {
				
				// This outcome will return a null object and login is deemed unsuccessful
				System.out.println("User Does Not Exist!");
				return null;
			}
		}catch(Exception e) {
			System.out.println("Login Unsuccessful");
			e.printStackTrace();// Helpful debuging tool
		}
		
		// If the try-catch does not run, a null object is return and login is deemed unsuccessful
		return null;
		
	}

}
