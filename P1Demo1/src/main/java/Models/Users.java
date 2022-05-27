package Models;

import Models.Roles;
import mockdata.MockUserData;

public class Users {
	//MockUserData e = new MockUserData();
	
	private int Id;
	private String userName;
	private String password;
	private Roles role;
	
	 
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String userName, String password, Roles role) {
		super();
		Id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public Users( String userName, String password, Roles role) {
		super();
		
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		
		
		
		
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRoles() {
		return role;
	}
	public void setRoles(Roles role) {
		this.role = role;
	}
	

}
