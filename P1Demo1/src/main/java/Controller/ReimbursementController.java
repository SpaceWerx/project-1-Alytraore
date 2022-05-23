package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.Reimbursement;
import Models.Status;
import Models.Users;
import Service.Reimbursement_Services;
import Service.User_Services;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class ReimbursementController {
	Reimbursement_Services rs = new Reimbursement_Services();

	public Handler handleGetReimbursements = (ctx) ->{
		String body= ctx.body();
		int id = Integer.parseInt(body);
		
		List<Reimbursement> reimAuthor = Reimbursement_Services.getReimbursementsByAuthor(id);
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(reimAuthor);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	public Handler handleSubmit = (ctx) ->{
		
		
		Reimbursement reimbursement = new Reimbursement();
		
		int id = Reimbursement_Services.submitReimbursement(reimbursement);
		
		if(id !=0) {
			ctx.status(HttpCode.CREATED);
			ctx.result("" + id);
		}else {
			ctx.status(HttpCode.BAD_REQUEST);
			ctx.result("Reimbursement submnission was unsuccessful");
			
		}
	 
		
	};
	
	public Handler handleGetReimbursementById = (ctx) ->{
		String body = ctx.body();
		int id = Integer.parseInt(body);
		Reimbursement reimId = Reimbursement_Services.getReimbursementById(id);
		
		Gson gson = new Gson();
		String JSONObject = gson.toJson(reimId);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	public Handler handleProcess = (ctx) ->{
		
		String authHeader = ctx.header("Current-User");
		
		if(authHeader != null) {
			
			int userId = Integer.parseInt(authHeader);
				
				String reimbursementIdInput = ctx.pathParam("id");
				
				int id = Integer.parseInt(reimbursementIdInput);
				
				String statusInput = ctx.formParam("status");
				
				Reimbursement reimbursement = Reimbursement_Services.getReimbursementById(id);
				
				if(reimbursement != null) {
				
					Reimbursement processedReimbursement = Reimbursement_Services.update (reimbursement, userId, Status.valueOf(statusInput));
				
					ctx.status(HttpCode.ACCEPTED);
					ctx.json(processedReimbursement);
					
				} else {
					ctx.status (HttpCode.BAD_REQUEST);
					ctx.result("Reimbursement processing was not successful");
					}
		}
	
	};


	
	

	public Handler handleGetReimbursementByAuthor = (ctx) ->{
		String idParam = ctx.queryParam("author");
		int id = Integer.parseInt(idParam);
		
		
		
		
		
		//Gson gson = new Gson();
		//String JSONObject = gson.toJson(reimId);
		
		if(User_Services.idExists(id)) {
			ctx.status(HttpCode.OK);
			ctx.json(Reimbursement_Services.getReimbursementsByAuthor(id));
			
			
		}else {
			ctx.status(HttpCode.NOT_FOUND);
			ctx.result(" Unable to retrieve reimbursements, current user is not in the database");
		}
	
	};

	public Handler handleGetReimbursementByStatus = (ctx) ->{
		String authParam = ctx.body();
		int id = Integer.parseInt(authParam);
		Status status = Status.valueOf(authParam);
		
		List<Reimbursement> reimId = Reimbursement_Services.getReimbursementsByStatus(status);
		
		Gson gson = new Gson();
		String JSONObject = gson.toJson(reimId);
		
		if(status == Status.Pending) {
			ctx.status(HttpCode.OK);
			ctx.json(Reimbursement_Services.getPendingReimbursements());
			
		}else {
			ctx.status(HttpCode.OK);
			ctx.result(JSONObject);
		}
	
	
	};
	
}

	
	
			
		
		
		
