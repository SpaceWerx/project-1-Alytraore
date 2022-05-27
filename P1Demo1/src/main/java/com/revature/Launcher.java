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
import io.javalin.core.JavalinConfig;

public class Launcher {

	public static void main(String[] args) {
		
		//CLI_Menu_Service options = new CLI_Menu_Service();
		//options.displayMenu();
		//options. 
	
	
	AuthController ac = new AuthController();
	UserController uc = new UserController();
	ReimbursementController rc = new ReimbursementController();
	
	
	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //This is what allows teh server to process JS requests from anywhere
				}
			).start(8000);
		
			//Now we need our endpoints
			app.post("/login", ac.handleLogin);
			//app.post("/register", ac.handleRegister);
			
			app.get("/user", uc.handleGetUsers);
			app.post("/user", uc.insertEmployeesHandler);
			app.get("/userid", uc.handleGetUserById);
			app.get("/username", uc.handleGetByUserName);
			
			app.post("/submit", rc.handleSubmit);
			app.put("/process", rc.handleProcess);
			app.get("/Reimbursement", rc.handleGetReimbursements);
			app.get("/Reimbursement/{id}", rc.handleGetReimbursementById);
            app.get("/status", rc.handleGetReimbursementByStatus);
            app.get("/author/{author}", rc.handleGetReimbursementByAuthor);
			
			
			
			
			
			
			
		
	}
	}
	
	




