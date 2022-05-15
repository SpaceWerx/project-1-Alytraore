package Service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import Models.Roles;
import Models.Users;
import Repository.User_DAO;

public class User_Services {
	
	
	
	User_DAO user_DAO = new User_DAO();
	
	
	static List<Users> users = new ArrayList();
	
	/*public Users getUsersByUsersName(String UserName) {
		
		for(Users user : users) {
			if(user.getUserName().equals(UserName)) {
				return user;
			}	
		}
		return null;
//	*/
	public Users getUsersByUsersName(String userName) {
		return user_DAO.getByUserName(userName);
		
	}
	
	public List<Users> getAllUsers(List<Users> users) {
		return User_DAO.getAllUsers();
	}
	
	
	public void idExists(int Id) {
		
		
		
		
	}
	
	public static List<Users> getByRoles(Roles role){
		return users;
		
	}

	
	/*public void idExists(int Id) {
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
		
		
	}*/
	
	/*public static Users getUserById(int userChoice) {
		for(Users user: users) {
			if(user.getId()== userChoice) {
				return user;
			}
		}
		
		return null;
	}*/
	
	
	
	
	public static Users getUserById(int userChoice) {
		return User_DAO.getUserId(userChoice);
		
		
		
		
		
	}
	
}
