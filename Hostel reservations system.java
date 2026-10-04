import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    int roomNumber;

    Booking(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
    }

    public static void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room No: " + room.roomNumber +
                        " | Category: " + room.category);
            }
        }
    }

    public static void bookRoom() {
        System.out.print("Enter Customer Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        showAvailableRooms();

        System.out.print("Enter Room Number to Book: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && !room.isBooked) {
                room.isBooked = true;
                bookings.add(new Booking(name, roomNo));

                System.out.println("\nRoom Booked Successfully!");
                System.out.println("Customer: " + name);
                System.out.println("Room Number: " + roomNo);
                return;
            }
        }

        System.out.println("Room not available!");
    }

    public static void cancelBooking() {
        System.out.print("Enter Room Number to Cancel Booking: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.isBooked) {
                room.isBooked = false;

                bookings.removeIf(booking -> booking.roomNumber == roomNo);

                System.out.println("Booking Cancelled Successfully!");
                return;
            }
        }

        System.out.println("No booking found for this room!");
    }

    public static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }

        System.out.println("\nBooking Records:");
        for (Booking booking : bookings) {
            System.out.println(
                    "Customer: " + booking.customerName +
                    " | Room Number: " + booking.roomNumber);
        }
    }

    public static void main(String[] args) {

        initializeRooms();

        int choice;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Records");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showAvailableRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }
        }
