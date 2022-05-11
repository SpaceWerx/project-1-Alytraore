package Service;

import java.util.List;
import java.util.Scanner;

import javax.management.relation.Role;

import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Roles;
import Models.Status;
import Models.Users;

public class CLI_Menu_Service {
	
	Reimbursement_Services rService = new Reimbursement_Services();
	///////////////////////////
	Scanner scan = new Scanner(System.in);
	public String fetchInput() {
		return scan.nextLine().split(" ")[0];//research if regex: is needed
	}
	/////////////////////////////
	public int promptSelection(int ...validEntries) {
		int input;
		boolean valid = false;
		
		do {
			input = parseIntegerInput(fetchInput());
			for(int entry : validEntries) {
				if(entry == input) {
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.out.println("Input received was not a valid option, please try again.");
			}
		}while(!valid);
		return input;
	}
/////////////////////////////////////////////
public int parseIntegerInput(String input) {
try {
return Integer.parseInt(input);
} catch (NumberFormatException e) {
System.out.println("The input received was malformed, please try again.");
return -1;
}
}
//////////////////////////////////////////////
public double parseDoubleInput(String input) {
	try {
		return Double.parseDouble(input);
	} catch(NumberFormatException e) {
		System.out.println("The Input received was not valid, please try again.");
		return -1;
	}
}
///////////////////////////////////////////////////////////////////////
public void handlePortal(Roles role) {
	List<Users> users = User_Services.getByRoles(role);
	
	int[] ids = new int[users.size() +1];
	ids[users.size()] = 0;
	for (int i = 0; i<users.size(); i++) {
		ids[i] = users.get(i).getId();
	}
	
	System.out.println("----------------------------------");
	System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
	
	for(Users u : users){
		System.out.println(u.getId() + " -> " + u.getUserName());
	}
	
	System.out.println("0 -> Return to Main Menu");
	System.out.println();
	
	int userChoice = promptSelection(ids);
	
	if (userChoice == 0) {
		return;
	}
	Users employee = User_Services.getUserById(userChoice);//change in userservice method body
	
	/*if(role == Roles.Manager) {
		System.out.println("Opening Manager Portal for " + employee.getUserName());
		displayFinanceManagerMenu(employee);
	} else {
		System.out.println("Opening Employee Portal for " + employee.getUserName());
		displayEmployeeMenu(employee);
	}*/
	
}

/////////////////////////////////////////////
public void displayMenu() {
	boolean menuOptions = true;
	System.out.println("---------------------------------------");
	System.out.println("Welcom to the revature Reimbursement System");
	System.out.println("---------------------------------------");
	System.out.println();
	while(menuOptions) {
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.println("1 -> Employee Portal");
		System.out.println("2 -> Finance Manager Portal");
		System.out.println("0 -> Exit Application");
		
		int firstChoice = promptSelection(1,2,0);//lookthisup
		switch(firstChoice) {
		case 1:
			handlePortal(Roles.Employee);
			break;
		case 2:
			handlePortal(Roles.Manager);
			break;
		case 0:
			System.out.println("\nHave a great day! Goodbye.");
			menuOptions = false;
			break;
		}
	}
}

/////////////////////////////////////////////////////////////////


}