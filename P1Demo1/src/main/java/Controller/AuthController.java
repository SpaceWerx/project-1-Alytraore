package Controller;

import java.util.Objects;

import org.eclipse.jetty.server.Authentication.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Models.Users;
import Repository.User_DAO;
import Service.Auth_Services;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class AuthController {
Auth_Services as = new Auth_Services();
	
	Users user = new Users();
	
	public Handler handleRegister = (ctx) ->{
		
		
		
			String input = ctx.body();
			
			ObjectMapper mapper = new ObjectMapper();
			Users user = mapper.readValue(input, Users.class);
			
			int id = Auth_Services.register(user);
			
			if(id == 0) {
				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				ctx.result("Registration unsuccessful.");	
			}else {
				ctx.status(HttpCode.CREATED);
				ctx.result("Registration successful.");
			}
		}; 
	
	public Handler handleLogin = (ctx) ->{
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		Users login = gson.fromJson(body, Users.class);
		System.out.println(login.getUserName());
		if(Auth_Services.login(login.getUserName(), login.getPassword())==1) {
			
			ctx.status(201);
			ctx.result("Manager Login successful");
		}else if(Auth_Services.login(login.getUserName(), login.getPassword())==2) {
				ctx.status(202);
				ctx.result("Employee Login successful");
			}else {
				ctx.status(401);
				ctx.result("Invalid Credentials");
			}
		};
	
		
		
	
	
	
}

