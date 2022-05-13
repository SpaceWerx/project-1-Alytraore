package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Reimbursement_Type;
import Models.Status;
import Utilities.ConnectionFactoryUtility;

public class Reimbursement_DAO {
	
	/*
	 * The create method is meant to create  a new record in the database for new reimbursement submissions
	 */
	
	public int create(Reimbursement reimbursementToBeSubmitted) {
		
		// try-catch block to catch sql exception that can be thrown with connection 
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			//writing out the (relatively complex) sql insert string to create a new  record
			//we explicitly ask the database to return the new id after entry
			String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount)"
					+ "Values (?,?,?::type, ?::status.?)"
					+"RETURNING ers_reimbursements.id";
			
			// We must use a prepared Statement because we have parameters
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//use the PreparedStatement objects' methods to insert values into the query's ?
			//the values will come from reimbursement object we use in
			preparedStatement.setInt( 1, reimbursementToBeSubmitted.getAuthor());
			preparedStatement.setString(2, reimbursementToBeSubmitted.getDescription());
			preparedStatement.setObject(3, reimbursementToBeSubmitted.getType());
			preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus());
			preparedStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());
			
			// we need to use the result set to retrieve the newly generated ID after entry of the new record
			ResultSet resultSet;
			
			// Here, we are checking that the sql query execute and returned the reimbursement record with the new id 
			if((resultSet = preparedStatement.executeQuery()) != null) {
				// must call this to get the return reimbursement record id
				resultSet.next();
				//finally returning the new id
				return resultSet.getInt(1);
			
			}
		} catch (SQLException e) {
			System.out.print("Creating reimbursemnet has failed");
			e.printStackTrace();
		}
		// Fail-safe if the try-catch block does not run
		return 0;
			
			
		
		
		
		
	}
	public void update(Reimbursement unprocessedReimbursement) {
		// try-catch block to catch sql exception that can be thrown with connection 
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			// Write the query that we meant to send to the database and assign it to a string
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";
			
			//Creating a prepared statement with the sql string we create
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Setting the update parameters (?'s) with their perspective values.
			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());
			preparedStatement.setObject(2, unprocessedReimbursement.getStatus().name());
			preparedStatement.setInt(3, unprocessedReimbursement.getId());
			
			// executing the record update
			preparedStatement.executeUpdate();
			
			// Proclaim victory
			System.out.println("Reimbursement Successfully Updated!");
			
		} catch (SQLException e) {
			System.out.print("Updating Failed!");// Proclaim defeat
			e.printStackTrace(); // useful debugging tool
		}
		
		
	}
	public List<Reimbursement> getReimbursementsByUser(int userId){
		
		//try-catch block to catch sql exception that can be thrown with connection 
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			// SQL statement prepared as a string
			//In this instance, we are filtering reimbursements by an author(user) id
			String sql = "select * from ers_reimbursements WHERE author = ?";
			
			//Preparing the sql statement to be execute once we fill the query parameters
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Filling the missing query value (?) with the method parameter (userId)
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			
			//Initializing a new Reimbursement array list to house and return with the data from the database
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			// This while loop will continue to add reimbursement to the list
			//until all the data from the result set has run out
			while(resultSet.next()) {
				
				// Adding reimbursements to the list with the data extracted from the database
				reimbursements.add(new Reimbursement(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Reimbursement_Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount"))
						);	
			}
		
		} catch (SQLException e) {
			
			// Catching the sql exception (this is a good place to utilize custom exception handling)
			System.out.println("Something went wrong obtaining Your List!");
			e.printStackTrace();
		}
		
		//Fail-safe if the try-catch block does not run
		return null;
			
		
		
		
		
	}
	public Reimbursement getReimbursementById(int id) {
		
		//try-catch block to catch sql exception that can be thrown with connection 
				try(Connection connection = ConnectionFactoryUtility.getConnection()){
					
					String sql = "select * from ers_reimbursements WHERE id = ?";
					
					//When we need parameters we need to use prepared statement, as opposed to a Statement (seen above)
					PreparedStatement preparedStatement = connection.prepareStatement(sql);// preparedStatement() as opposed to createStatement()
					
					// Insert the methods argument (int id) as the first (and only) variable in our sql query
					preparedStatement.setInt( 1, id);//the 1 here is referring to the first parameter(?) found in our sql String
					
					ResultSet resultSet = preparedStatement.executeQuery();
					
					// if there are results in  the result set
					if (resultSet.next()) {
						
						// return a reimbursement with the data to be returned to the service layer
						return new Reimbursement(
								resultSet.getInt("id"),
								resultSet.getInt("author"),
								resultSet.getInt("resolver"),
								resultSet.getString("description"),
								Reimbursement_Type.valueOf(resultSet.getString("type")),
								Status.valueOf(resultSet.getString("status")),
								resultSet.getDouble("amount")
								
								);
					}
					
				
				} catch (SQLException e) {
					
					// Catching the sql exception (this is a good place to utilize custom exception handling)
					System.out.println("Something went wrong obtaining your List!");
					e.printStackTrace();
				}
				
				//Fail-safe if the try-catch block does not run
				return null;
					
		
		
		
	}
	public List<Reimbursement> getByStatus(Status status){
		//try-catch block to catch sql exception that can be thrown with connection 
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			//Write the query that we meant to send to the database and assign it to a string
			String sql = "select * from ers_reimbursements WHERE status = ?::status";
			
			//Put the SQL query into a statement object (The Connection object has a method for this!! implicit?)
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			// Execute the Query by putting the results of the query into our resultSet object (resultSet)
			//The Statement object has a method that takes Strings to execute as a sql query
			preparedStatement.setString(1, status.toString());
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// All the Code Above Makes a call to our database.Now we need to store in an ArrayList.
			
			//Create an empty ArrayList to be filled with the date from database
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			//while there are results in results
			while (resultSet.next()) {
				
				// use the all args constractor to create a new User object from each returned row from the Database
				reimbursements.add(new Reimbursement(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Reimbursement_Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						
						));
				
			}
			//when there are no more results in resultSet, the while loop will break
			//then, return the populated ArrayList of Users
			return reimbursements;
		
		} catch (SQLException e) {
			
			System.out.println("Something went wrong obtaining your List!");
			e.printStackTrace();
		}
		
		//Fail-safe if the try-catch block does not run
		
		
		return null;
		
	}
	public List<Reimbursement> getAllReimbursement(){
		
		// try-catch block to catch sql exception that can be thrown with connection 
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
		
		// Instantiate a new new arrayList to store the records from database
					List<Reimbursement> reimbursements = new ArrayList<>();
					
					// write out the appropriate sql query string
					String sql = "Select * from ers_reimbursements";
					
					// we can create statement in this case because we don't have any parameters in the query
					Statement statement = connection.createStatement();
					
					// Storing the records from the query in a result set
					ResultSet resultSet = statement.executeQuery(sql);
					
					// Looping over the records from the query to then add to the return list
					while(resultSet.next()) {
						reimbursements.add(new Reimbursement(
								resultSet.getInt("id"),
								resultSet.getInt("author"),
								resultSet.getInt("resolver"),
								resultSet.getString("description"),
								Reimbursement_Type.valueOf(resultSet.getString("type")),
								Status.valueOf(resultSet.getString("status")),
								resultSet.getDouble("amount")
								
								));
						
					}
					//returning the list of records after the result set runs out
					return reimbursements;
				
				} catch (SQLException sqlException) {
					System.out.println("Something went wrong with the database!");
					sqlException.printStackTrace();
				}
				
				//Fail-safe if the try-catch block does not run
				return null;
		
	}

}
