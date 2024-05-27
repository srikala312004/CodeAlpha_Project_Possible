import java.util.*;

// Enum for room types
enum RoomType {
    SINGLE, DOUBLE, SUITE
}

// Class to represent a room
class Room {
    private int roomNumber;
    private RoomType type;
    private boolean available;

    public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookRoom() {
        this.available = false;
    }

    public void freeRoom() {
        this.available = true;
    }
}

// Class to represent a hotel
class Hotel {
    private String name;
    private Map<RoomType, List<Room>> rooms;

    public Hotel(String name) {
        this.name = name;
        rooms = new HashMap<>();
        initializeRooms();
    }

    private void initializeRooms() {
        for (RoomType type : RoomType.values()) {
            rooms.put(type, new ArrayList<>());
        }

        // Populate rooms
        for (int i = 1; i <= 10; i++) {
            rooms.get(RoomType.SINGLE).add(new Room(i, RoomType.SINGLE));
        }
        for (int i = 11; i <= 20; i++) {
            rooms.get(RoomType.DOUBLE).add(new Room(i, RoomType.DOUBLE));
        }
        for (int i = 21; i <= 30; i++) {
            rooms.get(RoomType.SUITE).add(new Room(i, RoomType.SUITE));
        }
    }

    public void displayAvailableRooms() {
        for (RoomType type : RoomType.values()) {
            System.out.println("Available " + type + " rooms:");
            for (Room room : rooms.get(type)) {
                if (room.isAvailable()) {
                    System.out.println("Room number: " + room.getRoomNumber());
                }
            }
        }
    }

    public boolean bookRoom(RoomType type, int roomNumber) {
        for (Room room : rooms.get(type)) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.bookRoom();
                return true;
            }
        }
        return false;
    }

    public void freeRoom(RoomType type, int roomNumber) {
        for (Room room : rooms.get(type)) {
            if (room.getRoomNumber() == roomNumber) {
                room.freeRoom();
                break;
            }
        }
    }
}

// Main class
public class HotelReservation {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Grand Hotel");
        Scanner scanner = new Scanner(System.in);

        boolean continueBooking = true;
        while (continueBooking) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Display available rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Free a room");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.println("Enter room type (SINGLE, DOUBLE, or SUITE):");
                    RoomType type = RoomType.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter room number:");
                    int roomNumber = scanner.nextInt();
                    if (hotel.bookRoom(type, roomNumber)) {
                        System.out.println("Room booked successfully.");
                    } else {
                        System.out.println("Failed to book room. Room may not be available.");
                    }
                    break;
                case 3:
                    System.out.println("Enter room type to free (SINGLE, DOUBLE, or SUITE):");
                    type = RoomType.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter room number to free:");
                    roomNumber = scanner.nextInt();
                    hotel.freeRoom(type, roomNumber);
                    System.out.println("Room freed successfully.");
                    break;
                case 4:
                    continueBooking = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }

        // Close scanner
        scanner.close();
    }
}
