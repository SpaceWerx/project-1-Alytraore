package com.revature;
import java.util.Scanner;

import Models.Roles;
import Models.Users;
import Service.CLI_Menu_Service;

public class Launcher {
	public static void main(String[] args) {
		
		CLI_Menu_Service options = new CLI_Menu_Service();
		options.displayMenu();
		
		
		
	}

}
