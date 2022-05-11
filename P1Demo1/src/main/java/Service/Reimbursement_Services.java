package Service;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Status;
import Models.Users;

public class Reimbursement_Services {
	
    static List<Reimbursement> reimbursements = new ArrayList();
    
	public void submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		Reimbursement latestReimbursement = reimbursements.get(reimbursements.size() - 1);
		int id = latestReimbursement.getId() + 1;// New ID is 1 higher than the previous highest
		
		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.Pending);
		reimbursements.add(reimbursementToBeSubmitted);
		
	}
	public void update(Reimbursement unprocessedReimbursement, int resolvedId, Status updatedStatus) {
		for(Reimbursement reimbursement: reimbursements) {
			if(reimbursement.getId()== unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolvedId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");
	}
	public Reimbursement getReimbursementById(int selection) {
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getId()== selection) {
				return reimbursement;
				
			}
		}
		return null;
		
	}
   public List<Reimbursement> getPendingReimbursements() {
	   List<Reimbursement> pendingReimbursements = new ArrayList<>();
   
	   for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getStatus()== Status.Pending) {
				pendingReimbursements.add(reimbursement);
				
			}
	   }
		return pendingReimbursements;
		
	}
	public List<Reimbursement> getResolvedReimbursements(){
		  List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		  for(Reimbursement reimbursement : reimbursements) {
				if(reimbursement.getStatus()== Status.Approved || reimbursement.getStatus()== Status.Denied) {
					resolvedReimbursements.add(reimbursement);
		  
				}
			  
		  }
		return resolvedReimbursements;
		
	}
	
	
	public List<Reimbursement> getReimbursementsByAuthor(int userId){
		
		 List<Reimbursement> userReimbursements = new ArrayList();

		
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getAuthor()== userId || reimbursement.getResolver()== userId) {
				userReimbursements.add(reimbursement);
			}
		}
		
		
		return userReimbursements;
	

}}

	
	
	
		
	
