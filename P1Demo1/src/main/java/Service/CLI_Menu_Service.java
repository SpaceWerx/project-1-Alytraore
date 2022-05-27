package Service;

import java.util.List;
import java.util.Scanner;

import javax.management.relation.Role;

import Models.EmployeeMenu;
import Models.ManagerMenu;
import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Roles;
import Models.Status;
import Models.Users;

public class CLI_Menu_Service {
	
	Reimbursement_Services rService = new Reimbursement_Services();
	User_Services userServices = new User_Services();
	ManagerMenu mm = new ManagerMenu();
	EmployeeMenu em = new EmployeeMenu();
	Auth_Services au = new Auth_Services();
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
	Users employee = User_Services.getUserById(userChoice);
	if(role == Roles.Manager) {
		System.out.println("Opening Manager Portal for " + employee.getUserName());
		displayFinanceManagerMenu(employee);
	} else {
		System.out.println("Opening Employee Portal for " + employee.getUserName());
		displayEmployeeMenu(employee);
	}
	
}

/////////////////////////////////////////////////////////
public void displayPreviousRequest(Users employee) {

List<Reimbursement> reimbursements = Reimbursement_Services.getReimbursementsByAuthor(employee.getId());

if(reimbursements.isEmpty()) {
System.out.println("No Previous Request..");
System.out.println("Returning to Previous Menu...");
}
for (Reimbursement r : reimbursements) {
System.out.println(r.getAuthor() + "" + r.getType() +"" + r.getDescription() + "" +r.getAmount() + "" + r.getStatus());
}
}
///////////////////////////////////////////////////////////////
public void submitReimbursement(Users employee) {
Reimbursement reimbursementToBeSubmitted = new Reimbursement();
reimbursementToBeSubmitted.setAuthor(employee.getId());

System.out.println("What type of reimbursement would you like to submit?");
System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
System.out.println("1 -> Lodging");
System.out.println("2 -> Travel");
System.out.println("3 -> Food");
System.out.println("4 -> Other");

int typeDecision = promptSelection(1, 2, 3, 4); //check if valid entries necessary

switch(typeDecision) {
case 1:
reimbursementToBeSubmitted.setType(Reimbursement_Type.Lodging);
break;
case 2:
reimbursementToBeSubmitted.setType(Reimbursement_Type.Travel);
break;
case 3:
reimbursementToBeSubmitted.setType(Reimbursement_Type.Food);
break;
case 4:
reimbursementToBeSubmitted.setType(Reimbursement_Type.Other);
break;
}
System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
System.out.print("$");

reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));

if (reimbursementToBeSubmitted.getAmount()<= 0) {
System.out.println("Invalid Amount has been entered, please input a correct dollar amount.");
boolean valid = false;
while(!valid) {
reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
if (reimbursementToBeSubmitted.getAmount() != 0) {
valid = true;
}
}
}
System.out.println("Please enter a description/reason for your reimbursement request.");

reimbursementToBeSubmitted.setDescription(scan.nextLine());
if(reimbursementToBeSubmitted.getDescription().trim().equals("")) {
System.out.println("You cannot submit a request with an empty description, please explain the reason for your request.");
boolean valid = false;
while(!valid) {
reimbursementToBeSubmitted.setDescription(scan.nextLine());
if(!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
valid = true;
}

}
}
Reimbursement_Services.submitReimbursement(reimbursementToBeSubmitted);
}
/////////////////////////////////////////////
public void displayPendingReimbursements() {
List<Reimbursement> pendingReimbursements = Reimbursement_Services.getPendingReimbursements();

if(pendingReimbursements.isEmpty()) {
System.out.println("No Pending Requests...");
System.out.println("Returning to Previous Menu...");
}
//for(Reimbursement r : pendingReimbursements) {
//System.out.println(r);
}


///////////////////////////////////////////////
public void displayResolvedReimbursements() {
List<Reimbursement> resolvedReimbursements = Reimbursement_Services.getResolvedReimbursements();

if(resolvedReimbursements.isEmpty()) {
System.out.println("No Resolved Requests...");
System.out.println("Returning to Previous Menu...");
}
//for(Reimbursement r: resolvedReimbursements) {
	//System.out.println(r);
}


//////////////////////////////////////////////
public void processReimbursement(Users manager) {
boolean processPortal = true;
System.out.println("---------------------------------------");
System.out.println("Welcome to the Manager Processing Portal" + manager.getUserName());
System.out.println("---------------------------------------");
System.out.println();

while(processPortal) {
List<Reimbursement> reimbursements = rService.getPendingReimbursements();

if (reimbursements.isEmpty()) {
System.out.println("There are no reimbursemetns to process.");
System.out.println("Returning to previous menu...");
return;
}
int[] ids = new int[reimbursements.size()];
for (int i = 0; i< reimbursements.size(); i++) {
Reimbursement r = reimbursements.get(i);
Users author = userServices.getUserById(r.getAuthor());
System.out.println(r.getId() + " -> " + author.getUserName() + " : $" + r.getAmount());
ids[i] = r.getId();
}

System.out.println("Please enter the ID of the Reimbursement you wish to process.");

int selection = promptSelection(ids);
Reimbursement reimbursementToBeProcessed = rService.getReimbursementById(selection);
System.out.println("Processing reimbursement #" + reimbursementToBeProcessed.getId());
System.out.println("Details\nAuthor: " + userServices.getUserById(reimbursementToBeProcessed.getAuthor()).getUserName()
+ "\nAmount: " + reimbursementToBeProcessed.getAmount()
+ "\nDescription: " + reimbursementToBeProcessed.getDescription());
System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
System.out.println("1 -> Approve");
System.out.println("2 -> Deny");

int decision = promptSelection(1, 2);
Status status = (decision == 1) ? Status.Approved : Status.Denied;
rService.update(reimbursementToBeProcessed);

System.out.println("Would you like to process anohter reimbursement?");
System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
System.out.println("1 -> Yes");
System.out.println("2 -> No");

int lastChoice = promptSelection(1, 2);

if (lastChoice == 2) {
processPortal=false;
}

}
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
		
	int firstChoice = promptSelection(1,2,0);
		//String input = scan.nextLine();
		switch(firstChoice) {
		case 1:
			handlePortal(Roles.Employee);
			//displayEmployeeMenu(null);
			break;
		case 2:
			//displayFinanceManagerMenu(null);
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
//////////////////////////////////////////////////////////
public void displayEmployeeMenu(Users employee) {
boolean employeePortal = true;

System.out.println("---------------------------------------");
System.out.println("Welcom to the Employee Portal, " + employee.getUserName());
System.out.println("---------------------------------------");
System.out.println();

while (employeePortal) {

System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
System.out.println("1 -> View Previous Requests");
System.out.println("2 -> Submit a Reimbursement");
System.out.println("0 -> Return to Main Menu");

int firstChoice = promptSelection(1,2,0);//lookthisup, also verify "validentries" isnt required

switch(firstChoice) {
case 1:
displayPreviousRequest(employee);
break;
case 2:
submitReimbursement(employee);
break;
case 0:
System.out.println("Returning to Main Menu...");
employeePortal = false;
break;
}
}

}
////////////////////////////////////////////////////////////
public void displayFinanceManagerMenu(Users manager) {
boolean managerPortal = true;

System.out.println("---------------------------------------");
System.out.println("Welcom to the Manager Portal, " + manager.getUserName());
System.out.println("---------------------------------------");
System.out.println();

while (managerPortal) {

System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
System.out.println("1 -> View All Pending Reimbursements");
System.out.println("2 -> View All Resolved Reimbursements");
System.out.println("3 -> Process a Reimbursement");
System.out.println("0 -> Return to Main Menu");

int firstChoice = promptSelection(1,2,3,0);//lookthisup

switch(firstChoice) {
case 1:
displayPendingReimbursements();
break;
case 2:
displayResolvedReimbursements();
break;
case 3:
processReimbursement(manager);
case 0:
System.out.println("Returning to Main Menu...");
managerPortal = false;
break;
}
}

}
}

