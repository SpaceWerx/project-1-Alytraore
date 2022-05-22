package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.Users;
import Service.User_Services;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class UserController {

	User_Services us = new User_Services();
	Users user;
	public Handler handleGetUsers = (ctx) ->{
	
	//This does not work anymore like it was intended to, do not use this		
//	if(ctx.req.getSession(false) != null) {
//		
//	}
//	else {
//		ctx.status(401);
//	}
		
	List<Users> allUsers = us.getAllUsers();
	
	Gson gson = new Gson();
	
	String JSONObject = gson.toJson(allUsers);
	
	ctx.result(JSONObject);
	ctx.status(200);
	
	/*public Handler insertEmployeesHandler = (ctx) ->{
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Employee employee = gson.fromJson(body, Employee.class);
		
		es.addEmployee(employee);
		
		ctx.result("Employee successfully added!");
		ctx.status(201);
		
	};*/

	};
	
	public Handler handleGetUserById = (ctx) ->{
		
	String body = ctx.body();
	
	int id = Integer.parseInt(body);
    user = User_Services.getUserById(id);
	
    /*if(user != null) {
    	ctx.status(HttpCode.OK);
    	ctx.json(user);
    }else {
    	ctx.status(HttpCode.BAD_REQUEST);
    	ctx.result(" COULD NOT RETRIEVE THE USER");
    }*/
	Gson gson = new Gson();
	
	String JSONObject = gson.toJson(user);
	
	ctx.result(JSONObject);
	ctx.status(200);
	};
	
	public Handler handleGetByUserName = (ctx) ->{
		
		String usernameParam = ctx.pathParam("username");
		
		Users name = User_Services.getByUsersName(usernameParam);
			
			Gson gson = new Gson();
			
			String JSONObject = gson.toJson(name);
			
			ctx.result(JSONObject);
			ctx.status(200);
			};

}
