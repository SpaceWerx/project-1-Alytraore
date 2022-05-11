package Service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import Models.Roles;
import Models.Users;

public class User_Services {
	
	static List<Users> users = new ArrayList();
	
	public Users getUsersByUsersName(String UserName) {
		
		for(Users user : users) {
			if(user.getUserName().equals(UserName)) {
				return user;
			}	
		}
		return null;
	}
		
	public List<Users> getAllUsers(List<Users> users) {
		
		return users;
		
	}
	public void idExists(int Id) {
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
			if(user.getRole()==role) {
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
	}

}
