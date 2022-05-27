package Controller;

import java.util.List;

import org.eclipse.jetty.server.Authentication.User;

import com.google.gson.Gson;

import Models.Users;
import Repository.User_DAO;
import Service.User_Services;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class UserController {

	User_Services us = new User_Services();
	User_DAO userDAO = new User_DAO();
	Users user;
	public Handler handleGetUsers = (ctx) ->{
		
	List<Users> allUsers = us.getAllUsers();
	
	Gson gson = new Gson();
	
	String JSONObject = gson.toJson(allUsers);
	
	ctx.result(JSONObject);
	ctx.status(200);
	};
	
	public Handler insertEmployeesHandler = (ctx) ->{
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Users employee = gson.fromJson(body, Users.class);
		System.out.println(employee.getUserName());
		//us.addUser(employee);
		userDAO.create(employee);
		
		ctx.result("Employee successfully added!");
		ctx.status(201);
		
	};

	
	
	public Handler handleGetUserById = (ctx) ->{
		
	String body = ctx.body();
	
	int id = Integer.parseInt(body);
    user = User_Services.getUserById(id);
	
	Gson gson = new Gson();
	
	String JSONObject = gson.toJson(user);
	
	ctx.result(JSONObject);
	ctx.status(200);
	};
	
	public Handler handleGetByUserName = (ctx) ->{
		
		String usernameParam = ctx.body();
		
		Users name = User_Services.getByUsersName(usernameParam);
			
			Gson gson = new Gson();
			
			String JSONObject = gson.toJson(name);
			
			ctx.result(JSONObject);
			ctx.status(200);
	};


	


}

