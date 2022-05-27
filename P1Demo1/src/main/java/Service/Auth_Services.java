package Service;

import Models.Roles;
import Models.Users;
import Repository.User_DAO;

public class Auth_Services {
	/**
	 * Note: userToBeRegistered will have on id=0.
	 * After registration, the id will be a positive integer.
	 **/
	// making a new user object
	public static int register(Users userToBeRegistered) {
		
	
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
	public static int login(String username, String password) {
		Users user;
		try {
			user = User_DAO.getByUserName(username);
			if(user != null && password.equals(user.getPassword()) && user.getRoles()== Roles.Manager) {
				
				System.out.println("Logged In Successfully as Manager");
				return 1;
	
			} else if(user != null && password.equals(user.getPassword()) && user.getRoles()== Roles.Employee) {
				// if this one is true and the previous false, a null object is returned and login is deemed unsuccessfully
				System.out.println("Logged in succcessful as Employee");
				return 2;
				
			} else {	
				System.out.println("User Does Not Exist!");
				return 0;
			}
		}catch(Exception e) {
			System.out.println("Login Unsuccessful");
			e.printStackTrace();// Helpful debuging tool
		}
		
		// If the try-catch does not run, a null object is return and login is deemed unsuccessful
		return 0;
		
	}

}
