package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.Reimbursement;
import Models.Users;
import Service.Reimbursement_Services;
import io.javalin.http.Handler;

public class ReimbursementController {
	Reimbursement_Services rs = new Reimbursement_Services();

	public Handler handleGetReimbursements = (ctx) ->{
		String idParam = ctx.pathParam("id");
		int id = Integer.parseInt(idParam);
		
		List<Reimbursement> reimAuthor = rs.getReimbursementsByAuthor(id);
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(reimAuthor);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	public Handler handleSubmit = (ctx) ->{
		
	};
	
	public Handler handleGetReimbursementById = (ctx) ->{
		String idParam = ctx.pathParam("id");
		int id = Integer.parseInt(idParam);
		Reimbursement reimId = Reimbursement_Services.getReimbursementById(id);
		
		Gson gson = new Gson();
		String JSONObject = gson.toJson(reimId);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	public Handler handleProcess = (ctx) ->{;

};
}
