package com.revature;
import java.util.Scanner;

import Controller.AuthController;
import Controller.ReimbursementController;
import Controller.UserController;
import Models.Roles;
import Models.Users;
import Service.CLI_Menu_Service;
import Service.User_Services;
import io.javalin.Javalin;
//import io.javalin.core.JavalinConfig;

public class Launcher {

	public static void main(String[] args) {
		
		//CLI_Menu_Service options = new CLI_Menu_Service();
		//options.displayMenu();
	
	
	//AuthController ac = new AuthController();
	UserController uc = new UserController();
	ReimbursementController rc = new ReimbursementController();
	
	
	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //This is what allows teh server to process JS requests from anywhere
				}
			).start(4000);
		
			//Now we need our endpoints
			//app.post("/login", ac.handleLogin());
			
			//app.post("/register", ac.handleRegister(null));
			
			
            app.get("/Users", uc.handleGetUsers);
			
			app.get("/userid", uc.handleGetUserById);
			app.get("/username", uc.handleGetByUserName);
			
            
			
			app.post("/submit", rc.handleSubmit);
			app.post("/process", rc.handleProcess);
			
			app.get("/Reimbursement", rc.handleGetReimbursements);

            app.get("/{id}", rc.handleGetReimbursementById);
            app.get("/status", rc.handleGetReimbursementByStatus);
            app.get("/author", rc.handleGetReimbursementByAuthor);
			
			
			
			
			
			
			
		
	}
	}
	
	
	/*Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOriginals).routes(()->{
		
		path("login", () ->{
			
			post(ac::handleLogin);
		
		});
		
		path("register", () ->{
			
			post(ac::handleRegister);	
			
		});
		
		path("users", () ->{
			
			get(uc::handleGetUsers);
			
			path("{id}", () ->{
				get(uc::handleGetUserById);
			});
			
		});
		

		path("reimbursements", () ->{
			
			get(rc::handleGetReimbursements);
			
			post(rc::handleSubmit);
			
			path("{id}", () ->{
				
				get(rc::handleGetReimbursementById);
				
				put(rc::handleProcess);
			});
			
		});
	});
	
	public void start(int port) {
		this.app.start(port);
	}*/




