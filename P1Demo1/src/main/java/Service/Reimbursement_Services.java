package Service;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Roles;
import Models.Status;
import Models.Users;
import Repository.Reimbursement_DAO;

public class Reimbursement_Services {
	
	static List<Reimbursement> reimbursements = new ArrayList();
	
	public static Reimbursement_DAO reimbursement_DAO = new Reimbursement_DAO();
	public static User_Services user_Service = new User_Services();
	
	
	 public static List<Reimbursement> getPendingReimbursements() {
		 return reimbursement_DAO.getByStatus(Status.Pending);
		 
	 }
	
	 public static List<Reimbursement> getResolvedReimbursements(){
		  List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		  
		  resolvedReimbursements.addAll(reimbursement_DAO.getByStatus(Status.Approved));
		  resolvedReimbursements.addAll(reimbursement_DAO.getByStatus(Status.Denied));
		  
		  return resolvedReimbursements;
	 }
	 
	 public static int submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		 
		 getUserServices();
		 Users employee = User_Services.getUserById(reimbursementToBeSubmitted.getAuthor());
		 
		 if(employee.getRoles()!= Roles.Employee) {
			 throw new IllegalArgumentException("Managers connaot submit reimbursement requests.");
			 
		 } else {
			 reimbursementToBeSubmitted.setStatus(Status.Pending);
			 return reimbursement_DAO.create(reimbursementToBeSubmitted);
		 }
	 }
	 
    
    
    public static Reimbursement update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		getUserServices();
		Users manager = User_Services.getUserById(resolverId);
		
		if(manager.getRoles() != Roles.Manager ) {
			 throw new IllegalArgumentException("A employee connaot process reimbursement requests.");
			
			}else {
				unprocessedReimbursement.setResolver(resolverId);
				unprocessedReimbursement.setStatus(updatedStatus);
				
				reimbursement_DAO.update(unprocessedReimbursement);
			}
		return unprocessedReimbursement;
	}
	
	public static Reimbursement getReimbursementById(int id) {
		return reimbursement_DAO.getReimbursementById(id);
		
		
	}
	public static List<Reimbursement> getReimbursementsByAuthor(int userId){
		return reimbursement_DAO.getReimbursementsByUser(userId);
	}
	
	public static User_Services getUserServices() {
		return user_Service;
		
	}
	
	public void  setUserService(User_Services user_Service) {
		Reimbursement_Services.user_Service = user_Service;
	}
	
	 public List<Reimbursement> getPendingReimbursements(List<Reimbursement> Pending) {
	    	return Pending;
	    }
		
	    public List<Reimbursement> getResolvedReimbursements(List<Reimbursement> Resolved) {
	    	return Resolved;
	    }
	    
	    public List<Reimbursement> getAllReimbursements(List<Reimbursement> reimbursement) {
	    	return reimbursement;
	    }
	


    
	/*public void submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
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
		
	}*/
    
   
    
    ///////////////////////////////////////////////////////////////////////////////
    
	/*public List<Reimbursement> getReimbursementsByAuthor(int userId){
		
		 List<Reimbursement> userReimbursements = new ArrayList();

		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getAuthor()== userId || reimbursement.getResolver()== userId) {
				userReimbursements.add(reimbursement);
			}
		}
		
		return userReimbursements;
	

}*/
	



}

	
	
	
		
	
