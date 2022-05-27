package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Roles;
import Models.Status;
import Models.Users;
import Repository.Reimbursement_DAO;
import Service.Reimbursement_Services;
import Service.User_Services;
import mockdata.MockReimbursementData; 

public class ReimbursementServiceTest {



//	private static Reimbursement_Services reimbursement_Service;
//	private static User_Services user_Service;
//	private static Reimbursement_DAO reimbursement_DAO;
//	private Reimbursement REIMBURSEMENT_TO_PROCESS; 
//	private List<Reimbursement> mockPendingReimbursements; 
//	private List<Reimbursement> mockApprovedReimbursements; 
//	private List<Reimbursement> mockDeniedReimbursements;
//	private Users GENERIC_EMPLOYEE_1;
//	private Users GENERIC_MANAGER_1;
//	
//@BeforeAll
//public static void setUpBeforeClass() throws Exception {
//reimbursement_Service = new Reimbursement_Services();
//}
//
//		
//@BeforeAll 
//public void setUp() throws Exception {
//	
//	user_Service = mock(User_Services.class); 
//	reimbursement_DAO = mock(Reimbursement_DAO.class);
//
//	MockReimbursementData mockReimbursementData = new MockReimbursementData();
//	
//	reimbursement_Service.reimbursement_DAO = reimbursement_DAO; 
//	reimbursement_Service.user_Service = user_Service;
//	// Generic mock users to use in our tests 
//	
//	GENERIC_EMPLOYEE_1 = new Users( 1, "genericEmployee1",  "genericPassword", Roles. Employee); 
//	GENERIC_MANAGER_1 = new Users( 1,  "generic Manager1", "genericPassword", Roles. Manager);
//	// mock reimbursement that can be tested by processing
//	REIMBURSEMENT_TO_PROCESS = new Reimbursement(2, GENERIC_EMPLOYEE_1.getId(), 1, "Oracle Certification", Reimbursement_Type.Other, Status.Pending, 150.00);
//
//	List<Reimbursement> mockReimbursements = mockReimbursementData.getReimbursements(); 
//	mockPendingReimbursements = new ArrayList<>();
//	mockApprovedReimbursements = new ArrayList<>();
//	mockDeniedReimbursements = new ArrayList<>(); 
//		
//	for(Reimbursement reimbursement : mockReimbursements) { 
//		if(reimbursement.getStatus() == Status.Pending) {
//			mockPendingReimbursements.add(reimbursement); 
//		} else if (reimbursement.getStatus() == Status. Approved) {
//			mockApprovedReimbursements.add(reimbursement); 
//		} else {
//			mockDeniedReimbursements.add(reimbursement);
//		}
//	}
//		
//}
//
//	
//	@Test
//	public void testUpdateThrowsIllegalArgumentExceptionWhenResolverIsNotManager() {
//	
//	when(User_Services.getUserById(anyInt())).thenReturn(GENERIC_EMPLOYEE_1);
//	// Checking to make sure the tested update method throws the exception we want
//	assertThrows (IllegalArgumentException.class,
//			() -> reimbursement_Service.update(REIMBURSEMENT_TO_PROCESS, GENERIC_EMPLOYEE_1.getId(), Status. Approved)
//	);
//	
//	
//	verify(reimbursement_DAO, never()).update(REIMBURSEMENT_TO_PROCESS);
//	verify(user_Service);
//	User_Services.getUserById(GENERIC_EMPLOYEE_1.getId());
//																																																																																																																																																								
//	}
//	
//	@Test
//	public void testSubmitreimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
//	
//	when(User_Services.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
//	
//	assertThrows (IllegalArgumentException.class,
//	()-> reimbursement_Service.submitReimbursement (REIMBURSEMENT_TO_PROCESS)
//	);
//	// Verifying that the mocked reimbursementDAO create method is never called
//	// Verifying that the mocked user Service getUserById method is called
//	verify(reimbursement_DAO, never()).create(REIMBURSEMENT_TO_PROCESS);
//	verify(user_Service).getUserById(GENERIC_MANAGER_1.getId());
//	
//	}
//	
//	
//	@Test 
//	public void testResolverIsAssignedAfterReimbursementUpdate() {
//		// Telling the nested user Service method to return a Manager when called in the tested update method 
//		when(User_Services.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
//		// Checking to make sure the resolver is assigned accordingly when the update method is called
//		assertEquals(GENERIC_MANAGER_1.getId(), reimbursement_Service.update (REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), Status. Approved).getResolver());
//		
//		// Verifying that the mocked reimbursementDAO update method is called 
//		// Verifying that the mocked user Service getUserById method is called 
//		verify(user_Service).getUserById(GENERIC_MANAGER_1.getId());
//		verify(reimbursement_DAO).update (REIMBURSEMENT_TO_PROCESS);
//	}
//	
//	
//	@Test
//	public void testReimbursementStatusIsChangedAfterUpdate() {
//	// Telling the nested user Service method to return a Manager when called in the tested update method
//	when(User_Services.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
//	// Checking to make sure the correct status is assigned accordingly when the update method is called
//	assertEquals(Status. Approved, reimbursement_Service.update(REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), Status. Approved).getStatus());
//	verify(user_Service);
//	// Verifying that the mocked reimbursementDAO update method is called
//	// Verifying that the mocked user Service getUserById method is called
//	User_Services.getUserById(GENERIC_EMPLOYEE_1.getId());
//	verify(reimbursement_DAO).update (REIMBURSEMENT_TO_PROCESS);
//	}
//
//	
//@Test
//public void testGetResolvedReimbursementsReturnsOnlyApprovedAndDenied() {
//// Telling the nested reimbursementDAO getByStatus method to return the mocked list of Approved and Denied reimbursements when called respectively 
//	when(reimbursement_DAO.getByStatus (Status. Approved)).thenReturn(mockApprovedReimbursements); 
//	when(reimbursement_DAO.getByStatus (Status.Denied)).thenReturn(mockDeniedReimbursements);
//// Creating a new list that combines the mocked approved and denied reimbursements (similar to how the service method works) 
//	
//	
//	
//	
//	
//	
//	List<Reimbursement> resolvedReimbursements = new ArrayList<>(); 
//	resolvedReimbursements.addAll(mockApprovedReimbursements);
//	resolvedReimbursements.addAll(mockDeniedReimbursements);
//// Checking to make sure the service method returns the correct data
//	assertEquals(resolvedReimbursements, reimbursement_Service.getResolvedReimbursements());
//// Verifying that the mocked reimbursementDAO method getByStatus is called twice 
//	verify(reimbursement_DAO).getByStatus (Status. Approved); 
//	verify(reimbursement_DAO).getByStatus (Status. Denied); 
//}
//
//
//@Test 
//public void testGetPendingReimbursementsReturnsOnlyPending() {
//// Telling the nested reimbursementDAO getByStatus method to return the mocked list of pending reimbursements when called 
//	when(reimbursement_DAO.getByStatus(any(Status.class))).thenReturn(mockPendingReimbursements);
//// Checking to make sure the service method returns the correct data
//	assertEquals(mockPendingReimbursements, reimbursement_Service.getPendingReimbursements());
//// Verifying that the mocked reimbursementDAO method getByStatus is called
//	verify(reimbursement_DAO).getByStatus (Status.Pending); 
//}


	
	}
	
	
	
	
	
	
	


