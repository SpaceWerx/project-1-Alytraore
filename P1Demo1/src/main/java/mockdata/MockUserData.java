package mockdata;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Roles;
import Models.Status;
import Models.Users;

public class MockUserData {
		private final List<Users> users = new ArrayList<>();
		public MockUserData() {
		Users GENERIC_EMPLOYEE_1 = new Users( 1, "genericEmployeel", "genericPasswordl", Roles. Employee);
		Users GENERIC_EMPLOYEE_2 = new Users( 2, "genericEmployee2", "generic Password2", Roles. Employee);
		Users GENERIC_EMPLOYEE_3 = new Users( 3,"genericEmployee3", "genericPassword3", Roles. Employee);
		Users GENERIC_FINANCE_MANAGER_1 = new Users( 4,"genericManager1", "genericPasswordl", Roles. Manager);
		Users GENERIC_FINANCE_MANAGER_2 = new Users( 5, "genericManager2", "genericPassword2", Roles. Manager);
		Users GENERIC_FINANCE_MANAGER_3 = new Users( 6, "genericManager3", "genericPassword3", Roles. Manager);
		users.add(GENERIC_EMPLOYEE_1);
		users.add(GENERIC_EMPLOYEE_2);
		users.add(GENERIC_EMPLOYEE_3);
		users.add(GENERIC_FINANCE_MANAGER_1);
		users.add(GENERIC_FINANCE_MANAGER_2);
		users.add(GENERIC_FINANCE_MANAGER_3);
		}
		
		public List<Users> getUsers() { 
			return users;
		}
		


}
