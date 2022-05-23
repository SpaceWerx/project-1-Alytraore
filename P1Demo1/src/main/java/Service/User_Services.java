package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.relation.Role;

import Models.Roles;
import Models.Users;
import Repository.User_DAO;

public class User_Services {
	
	private Users user;
	
	
	User_DAO user_DAO = new User_DAO();
	
	
	
	
	static List<Users> users = new ArrayList();
	
	
	public static Users getByUsersName(String userName) {
		return User_DAO.getByUserName(userName);
		
	}
	
	public List<Users> getAllUsers() {
		return User_DAO.getAllUsers();
	}
	
	
	public static boolean idExists(int Id) {
	Users user = User_DAO.getUserId(Id);
	
	if(user != null) {
		System.out.println("Id exist");
	} else {
		System.out.println("Id does not exist");
	}
	return false;
 	
		
	}
	
	public static List<Users> getByRoles(Roles role){
		List<Users> theRole = new ArrayList<>();
		for(Users user: User_DAO.getAllUsers()) {
			if(user.getRoles()== role) {
				theRole.add(user);
			}
		}
		return theRole;
		
	}
	
	public static Users getUserById(int userChoice) {
		return User_DAO.getUserId(userChoice);	
}

	
	/*public Users getByUsersName(String UserName) {
		
		for(Users user : users) {
			if(user.getUserName().equals(UserName)) {
				return user;
			}	
		}
		return null;
	}
	 
	 * public void idExists(int Id) {
		for(Users user : users) {
			if(user.getId()==Id) {
				System.out.println("The Id already exists");
				break;
			}	
		}
		System.out.println("The Id doesn't exist");
		
		
	}
	public static List<Users> getByRoles(Roles role){
		for(Users user:users) {
			if(user.getRoles()==role) {
				users.add(user);
				
			}
			
		}
		return users;
		
		
	}
	
	public static Users getUserById(int userChoice) {
		for(Users user: users) {
			if(user.getId()== userChoice) {
				return user;
			}
		}
		
		return null;
	}*/
	
	
	
	
	
	
}
