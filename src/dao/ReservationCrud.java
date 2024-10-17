package dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			
			System.out.println("*******************************************************");
			
		}
		set.close();
		statement.close();
		connection.close();

	}

}





















