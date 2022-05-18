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

public class Launcher {
	public static void main(String[] args) {
		
		AuthController ac = new AuthController();
		UserController uc = new UserController();
		ReimbursementController rc = new ReimbursementController();
		
		
		CLI_Menu_Service options = new CLI_Menu_Service();
		options.displayMenu();
		
	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //This is what allows teh server to process JS requests from anywhere
				}
			).start(3000);
		
			//Now we need our endpoints
			//app.get("/employee", ec.getEmployeesHandler);
			
			//app.post("/employee", ec.insertEmployeesHandler);
		
	}
	
	/*public void start(int port) {
		this.app.start(port);
	}*/
}


