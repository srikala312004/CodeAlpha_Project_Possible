
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String type;
    private boolean isBooked;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }

    public void release() {
        isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ")";
    }
}

class Reservation {
    private Room room;
    private String guestName;
    private int numberOfNights;
    private double totalCost;

    public Reservation(Room room, String guestName, int numberOfNights, double totalCost) {
        this.room = room;
        this.guestName = guestName;
        this.numberOfNights = numberOfNights;
        this.totalCost = totalCost;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + " in " + room + " for " + numberOfNights + " nights. Total cost: $" + totalCost;
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Single"));
        rooms.add(new Room(201, "Double"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(301, "Suite"));
    }

    public void showAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room);
            }
        }
    }

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(String guestName, int roomNumber, int numberOfNights) {
        Room room = findRoom(roomNumber);
        if (room != null && !room.isBooked()) {
            room.book();
            double costPerNight = getCostPerNight(room.getType());
            double totalCost = costPerNight * numberOfNights;
            Reservation reservation = new Reservation(room, guestName, numberOfNights, totalCost);
            reservations.add(reservation);
            System.out.println("Reservation successful!");
            System.out.println(reservation);
        } else {
            System.out.println("Room not available or already booked.");
        }
    }

    private double getCostPerNight(String type) {
        switch (type) {
            case "Single":
                return 50.0;
            case "Double":
                return 75.0;
            case "Suite":
                return 150.0;
            default:
                return 0.0;
        }
    }

    public void showReservations() {
        System.out.println("Current reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

public class HotelReservation{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        
        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Show available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numberOfNights = scanner.nextInt();
                    hotel.makeReservation(guestName, roomNumber, numberOfNights);
                    break;
                case 3:
                    hotel.showReservations();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
