import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GradeTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> Grades = new ArrayList<>();

        // Input loop
        while (true) {
            System.out.print("Enter a grade (or -1 to finish): ");
            int grade = scanner.nextInt();
            if (grade == -1)
                break;
            Grades.add(grade);
        }

        // Check if grades were entered
        if (Grades.isEmpty()) {
            System.out.println("There are no grades entered.");
            return;
        }

        // Calculating average
        double sum = 0;
        for (int grade : Grades) {
            sum += grade;
        }
        double average = sum / Grades.size();

        // Finding highest and lowest grades
        int highest = Collections.max(Grades);
        int lowest = Collections.min(Grades);

        // Output results
        System.out.println("Average grade: " + average);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
    }
}
