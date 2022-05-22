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

	
	Users user = new Users();
	
	public void handleRegister(Context ctx) {
		
		
		try {
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
		} catch(Exception e) {
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			
			e.printStackTrace();
		}
		
	}
	public void handleLogin(Context ctx) {
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(Objects.equals(username, "") || Objects.equals(password, "")) {
			
			ctx.status(HttpCode.BAD_REQUEST);
			ctx.result("Invalid Credentials");
		}else {
			Users user = Auth_Services.login(username, password);
			
			if(user != null ) {
				
				ctx.status(HttpCode.ACCEPTED);
				ctx.result(user.getRoles().toString());
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Invalid Credentials");
			}
		}
	
		
		
	}
	
	
}
