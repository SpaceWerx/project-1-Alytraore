package mockdata;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Status;

public class MockReimbursementData {
	

	private final List<Reimbursement> reimbursements = new ArrayList<>();
	
			public MockReimbursementData() {
			MockUserData userData = new MockUserData();
			Reimbursement REIMBURSEMENT_TO_PROCESS_1 = new Reimbursement(1, 1, 0 , "Oracle Java Certification", Reimbursement_Type.Other, Status.Pending, 250.00);
			Reimbursement REIMBURSEMENT_TO_PROCESS_2 = new Reimbursement(2, 2, 0 , "Travel to Reston HQ", Reimbursement_Type.Travel, Status.Pending, 600.00);
			Reimbursement REIMBURSEMENT_APPROVED_1 = new Reimbursement(3, 1, 3, "Free Lunch offer from Sean", Reimbursement_Type.Food, Status. Approved, 25.00);
			Reimbursement REIMBURSEMENT_APPROVED_2 = new Reimbursement(4, 2, 4, "2-night hotel stay near Orlando Office for visit", Reimbursement_Type.Lodging, Status.Approved, 300.00);
			Reimbursement REIMBURSEMENT_DENIED_1 = new Reimbursement(5 , 1, 3, "Rental Car to drive from Reston to Orlando", Reimbursement_Type.Travel, Status. Denied, 2500.00);
			reimbursements.add(REIMBURSEMENT_TO_PROCESS_1);
			reimbursements.add(REIMBURSEMENT_TO_PROCESS_2);
			reimbursements.add(REIMBURSEMENT_APPROVED_1);
			reimbursements.add(REIMBURSEMENT_APPROVED_2);
			reimbursements.add(REIMBURSEMENT_DENIED_1);
			}
			public List<Reimbursement> getReimbursements() {
				return reimbursements; 
				}
			}
	
	


