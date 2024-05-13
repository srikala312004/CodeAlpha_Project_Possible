import java.util.*;

class Destination {
    String name;
    Date startDate;
    Date endDate;

    Destination(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

class TravelItinerary {
    List<Destination> destinations;

    TravelItinerary() {
        destinations = new ArrayList<>();
    }

    void addDestination(String name, Date startDate, Date endDate) {
        destinations.add(new Destination(name, startDate, endDate));
    }

    void printItinerary() {
        System.out.println("Your Travel Itinerary:");
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.name);
            System.out.println("Start Date: " + destination.startDate);
            System.out.println("End Date: " + destination.endDate);
            System.out.println();
        }
    }
}

public class TravelPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelItinerary itinerary = new TravelItinerary();

        System.out.println("Welcome to the Travel Itinerary Planner!");

        while (true) {
            System.out.print("Enter destination name (type 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equals("done")) {
                break;
            }

            System.out.print("Enter start date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            Date startDate = parseDate(startDateStr);
            if (startDate == null) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                continue;
            }

            System.out.print("Enter end date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();
            Date endDate = parseDate(endDateStr);
            if (endDate == null || endDate.before(startDate)) {
                System.out.println("Invalid date format or end date before start date.");
                continue;
            }

            itinerary.addDestination(name, startDate, endDate);
        }

        System.out.println();
        itinerary.printItinerary();
    }

    private static Date parseDate(String dateStr) {
        try {
            return new Date(dateStr);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}