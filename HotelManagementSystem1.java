package exceptionhandlings;

import java.util.*;

//Custom exceptions
class InvalidReservationException extends Exception {
 public InvalidReservationException(String message) {
     super(message);
 }
}

class RoomNotFoundException extends Exception {
 public RoomNotFoundException(String message) {
     super(message);
 }
}

//Base class for reservations
class Reservation {
 private int reservationId;
 private Guest guest;

 public Reservation(int reservationId, Guest guest) {
     this.reservationId = reservationId;
     this.guest = guest;
 }

 public int getReservationId() {
     return reservationId;
 }

 public Guest getGuest() {
     return guest;
 }

 public void displayDetails() {
     System.out.println("Reservation ID: " + reservationId);
     guest.displayDetails();
 }
}

class Guest {
 private String name;

 public Guest(String name) {
     this.name = name;
 }

 public void displayDetails() {
     System.out.println("Guest Name: " + name);
 }
}

//Room hierarchy
abstract class Room {
 private int roomNumber;
 private boolean available;

 public Room(int roomNumber) {
     this.roomNumber = roomNumber;
     this.available = true;
 }

 public int getRoomNumber() {
     return roomNumber;
 }

 public boolean isAvailable() {
     return available;
 }

 public void bookRoom() {
     available = false;
 }

 public abstract String getRoomType();
}

class StandardRoom extends Room {
 public StandardRoom(int roomNumber) {
     super(roomNumber);
 }

 @Override
 public String getRoomType() {
     return "Standard";
 }
}

class DeluxeRoom extends Room {
 public DeluxeRoom(int roomNumber) {
     super(roomNumber);
 }

 @Override
 public String getRoomType() {
     return "Deluxe";
 }
}

class SuiteRoom extends Room {
 public SuiteRoom(int roomNumber) {
     super(roomNumber);
 }

 @Override
 public String getRoomType() {
     return "Suite";
 }
}

//Hotel class
public class HotelManagementSystem1 {
 private Map<Integer, Room> rooms = new HashMap<>();
 private List<Reservation> reservations = new ArrayList<>();
 private Scanner scanner = new Scanner(System.in);
 private int nextReservationId = 1;

 public static void main(String[] args) {
     HotelManagementSystem system = new HotelManagementSystem();
     system.run();
 }

 public void run() {
     while (true) {
         System.out.println("1. Make Reservation");
         System.out.println("2. Check Room Availability");
         System.out.println("3. Exit");
         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline

         switch (choice) {
             case 1:
                 makeReservation();
                 break;
             case 2:
                 checkRoomAvailability();
                 break;
             case 3:
                 System.out.println("Exiting the program.");
                 scanner.close();
                 System.exit(0);
             default:
                 System.out.println("Invalid choice. Please enter a valid option.");
         }
     }
 }

 private void makeReservation() {
     System.out.print("Enter Guest Name: ");
     String guestName = scanner.nextLine();
     System.out.print("Enter Room Type (Standard/Deluxe/Suite): ");
     String roomType = scanner.nextLine();

     try {
         Guest guest = new Guest(guestName);
         Room room = findAvailableRoom(roomType);
         if (room == null) {
             throw new RoomNotFoundException("No available rooms of type " + roomType);
         }
         room.bookRoom();
         Reservation reservation = new Reservation(nextReservationId++, guest);
         reservations.add(reservation);
         System.out.println("Reservation successful!");
     } catch (RoomNotFoundException e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 private void checkRoomAvailability() {
     System.out.print("Enter Room Type to Check Availability: ");
     String roomType = scanner.nextLine();

     try {
         Room availableRoom = findAvailableRoom(roomType);
         if (availableRoom == null) {
             System.out.println("No available rooms of type " + roomType);
         } else {
             System.out.println("Room " + availableRoom.getRoomNumber() + " is available.");
         }
     } catch (RoomNotFoundException e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 private Room findAvailableRoom(String roomType) throws RoomNotFoundException {
     for (Room room : rooms.values()) {
         if (room.isAvailable() && room.getRoomType().equalsIgnoreCase(roomType)) {
             return room;
         }
     }
     return null;
 }
}
