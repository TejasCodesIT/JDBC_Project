package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ReservationCrud;
import dto.Customer;

public class ReservationController {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		ReservationCrud crud = new ReservationCrud();
		Customer customer = new Customer();
		Scanner sc = new Scanner(System.in);
		int choice=1;
		System.out.println("Please Enter your Choice between 0 to 5 :-");
		while(choice>0) {
			System.err.println("******************************************************************");
			
		
			
			System.out.println("1. Reservation a room ");
			System.out.println("2. View Reservations ");
			System.out.println("3. Get Room Nummber");
			System.out.println("4. Update Reservation ");
			System.out.println("5. Delete Reservation ");
			System.out.println("6. Exit");
			
			 choice = sc.nextInt();
			
			switch(choice) {
		
			case 1: 
				
				System.out.println("Enter a Guest Name :-");
				customer.setGuestName(sc.next());
				
				System.out.println("Enter a Room Number :- ");
				customer.setRoomNumber(sc.nextInt());
				
				System.out.println("Enter a Contact Number :- ");
				customer.setContactNumber(sc.nextLong());
				
				crud.reservationRoomc(customer);
				
			
					
				break;
				
			case 2:
				
				crud.viewReservation();
				
				
				break;
			case 3:
				System.out.println("Enter Id of Guest");
				int id= sc.nextInt();
				
				crud.getRoomNumberById(sc,id);
				break;
				
				
				
			case 4:
				
				crud.updateReservation(sc, customer);
				break;
				
				
				
			case 5:
				
				break;
			case 6:
				System.err.println("Exit Successfully...!");
				choice=-1;
				
				break;
			default:
				System.err.println("Please Enter a valid input");
			
			
			}
		}
		
	}

}





















