package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dto.Customer;

public class ReservationCrud {
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub\
		
		String url="jdbc:mysql://localhost:3306/hotel_db";
		String username = "root";
		String password = "root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		return DriverManager.getConnection(url, username, password);

	}
	
	public void reservationRoomc(Customer customer) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();
		
		 String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";
		 int affected =0;
		    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        // Set parameters in the PreparedStatement
		        preparedStatement.setString(1, customer.getGuestName());  // guest_name
		        preparedStatement.setInt(2, customer.getRoomNumber());    // room_number
		        preparedStatement.setLong(3, customer.getContactNumber());  // contact_number
		      
		        // Execute the query
		        affected =  preparedStatement.executeUpdate();
		        preparedStatement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Handle exception
		    }
		
		
		if(affected>0) System.out.println("Data enter successfully in database...!");
		else System.out.println("Data is not inserted successfully...!");
	
		connection.close();
			
	
		
	}
	
	
	public void viewReservation() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		
		
		Statement statement=connection.createStatement();
		
		String sql = "select * from reservations";
		
		ResultSet set=statement.executeQuery(sql);
		
		while(set.next()) {
			
			System.out.println("Id of Guest is : "+set.getInt("reservation_id"));
			System.out.println("Name of Customer is "+set.getString("guest_name"));
			System.out.println("Room Number of Guest is : "+set.getInt("room_number"));
			System.out.println("Contact Number is : "+set.getLong("contact_number"));
			System.out.println("Reservation date : "+set.getTimestamp("reservation_date"));
			
			System.err.println("*******************************************************");
			
		}
		set.close();
		statement.close();
		connection.close();

	}
	
	public int getRoomNumberById(Scanner sc,int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = getConnection();
		
		String sql = "select room_number,guest_name from reservations where reservation_id=?";
		PreparedStatement statement=connection.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		ResultSet resultSet =statement.executeQuery();
		
		
		if(resultSet.next()) {
			
			System.out.println("Name : "+resultSet.getString("guest_name"));
			System.out.println("Room Number :"+resultSet.getInt("room_number"));
			return 1;
			
		}
		else {
			System.err.println("Reservation not found for given id");
			return 0;
		}
		
		

	}
	
	public void updateReservation(Scanner sc,Customer customer) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection=null;
		
		try {
			connection = getConnection();
		} catch (ClassNotFoundException e) {
			
			System.out.println("Class not found in connnection from update Reservation");
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			System.out.println("Sql Exception while doing connection in update reservatin ");
			
			e.printStackTrace();
		}
		
		System.out.println("Enter id of guest:");
		int id = sc.nextInt();
		
		
		
		if((getRoomNumberById(sc,id))!=0) {
			
			System.out.println("Enter a Guest Name :-");
			customer.setGuestName(sc.next());
			
			System.out.println("Enter a Room Number :- ");
			customer.setRoomNumber(sc.nextInt());
			
			System.out.println("Enter a Contact Number :- ");
			customer.setContactNumber(sc.nextLong());
			
			String sql = "update reservations set guest_name=?,room_number=?,contact_number=?";
			
			PreparedStatement statement=connection.prepareStatement(sql);
			
			
			
			statement.setString(1, customer.getGuestName());
			statement.setInt(2, customer.getRoomNumber());	
			statement.setLong(3, customer.getContactNumber());
			
			int updated =statement.executeUpdate();
			
			if(updated>0) {
				
				System.out.println("Query updated");
				
			}
			else System.out.println("No query affected..!");
			
			
		}
		else {
			System.out.println("No such id found in database ");
		}
		
		

	}
	
	public void deleteById(Scanner sc ) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection connection=null;
		
		try {
			connection=getConnection();
			
		}
		catch(ClassNotFoundException e){
			System.out.println("Class not found in delete by id");
			e.printStackTrace();
			
		}
		catch(SQLException e) {
			
			System.out.println("Sql Exception found in delete by id method..");
			e.printStackTrace();
			
		}
		
		String sql = "delete from reservations where reservation_id=?" ;
		
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		
		System.out.println("Please Enter your ID TO DELETE Guest form database ...");
		int id = sc.nextInt();
		
		preparedStatement.setInt(1, id);
		
		int result = preparedStatement.executeUpdate();
		
		if(result>0) System.out.println("Delter Successfully...!");
		else System.err.println("Data is Not deleted from database...1");
		
		
		preparedStatement.close();
		connection.close();
		

	}

}




















