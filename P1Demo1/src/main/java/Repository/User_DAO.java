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
import Models.Roles;
import Models.Status;
import Models.Users;
import Utilities.ConnectionFactoryUtility;

public class User_DAO {
	
	public static Users getUserId( int Id) {
		
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "select * from ers_users where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				return new Users(
						resultSet.getInt("Id"),
						resultSet.getString("userName"),
						resultSet.getString("password"),
						Roles.valueOf(resultSet.getString("role"))
						
						);
			}
		} catch (SQLException e) {
			
			System.out.println("Something went wrong obtaining your List!");
			e.printStackTrace();
		}	
		return null;
}
//////////////////////////////////////////////////////////////////////////				
	
	public static Users getByUserName(String userName) {
try(Connection connection = ConnectionFactoryUtility.getConnection()){
			//System.out.println(userName);
			//System.out.println("aly");
			String sql = "select * from ers_users WHERE username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return new Users(
						resultSet.getInt("Id"),
						resultSet.getString("userName"),
						resultSet.getString("password"),
						Roles.valueOf(resultSet.getString("role"))
						);
			}
		} catch (SQLException e) {
			
			System.out.println("Something went wrong obtaining your List!");
			e.printStackTrace();
		}
			return null;
	}
	////////////////////////////////////////////////////////////////////////////////////////
	public static int create(Users user) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
		
			String sql = "INSERT INTO ers_users (username, password, role)"
					+ "values (?,?,?::type, ?::role.?"
					+ "RETURNING ers_users.id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString( 1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setObject(3, user.getRoles());
			
			ResultSet resultSet;
			
			if((resultSet = preparedStatement.executeQuery()) != null) {
				resultSet.next();
				//finally returning the new id
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.print("Creating user has failed");
			e.printStackTrace();
		}
		return 0;	
	}
	/////////////////////////////////////////////////////////////////////////
	public static List<Users> getAllUsers() {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			List<Users> users = new ArrayList<>();
			
			String sql = "Select * from ers_users";
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				users.add(new Users(
						resultSet.getInt("Id"),
						resultSet.getString("userName"),
						resultSet.getString("password"),
						Roles.valueOf(resultSet.getString("role"))
						));				
			}
			return users;
		} catch (SQLException sqlException) {
			System.out.println("Something went wrong with the database!");
			sqlException.printStackTrace();
		}	
		return null;
	}

}
