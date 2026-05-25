package view;

import SERVICES.TrainingManager;
import java.util.Scanner;

public class Main {
    private static ClubManager clubManager = new ClubManager(100);
    private static TrainingManager trainingManager = new TrainingManager();
    private static MatchManager matchManager = new MatchManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== FOOTBALL CLUB MANAGEMENT SYSTEM =====");
            System.out.println("1. Player Management");
            System.out.println("2. Training Management");
            System.out.println("3. Match Management");
            System.out.println("4. Contract & Salary Management");
            System.out.println("5. Reporting");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: playerMenu(); break; //duymanh
                case 2: trainingMenu(); break; // Dat
                case 3: matchMenu(); break; //Khoa
                case 4: salaryMenu(); break; //Quan
                case 5: reportMenu(); break;
                case 0: System.out.println("Exiting program..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
//SUBMENU
    private static void playerMenu() {
        System.out.println("\n-- PLAYER MANAGEMENT --");
        System.out.println("1. Add Player | 2. Update Info | 3. Deactivate | 4. View All | 5. Search");
    }

    private static void trainingMenu() {
    int choice;

    do {
        System.out.println("\n-- TRAINING MANAGEMENT --");
        System.out.println("1. Create Session");
        System.out.println("2. Record Attendance");
        System.out.println("3. View History");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                trainingManager.createSession();
                break;
            case 2:
                trainingManager.recordAttendance();
                break;
            case 3:
                trainingManager.viewHistory();
                break;
            case 0:
                System.out.println("Back to main menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    } while (choice != 0);
}

    private static void matchMenu() {
        System.out.println("\n-- MATCH MANAGEMENT --");
        System.out.println("1. Create Match | 2. Update Performance | 3. View History");
    }

    private static void salaryMenu() {
        System.out.println("\n-- CONTRACT & SALARY MANAGEMENT --");
        System.out.println("1. Calculate Monthly Salary | 2. Calculate Bonus | 3. Validate Rules");
    }
    
    private static void reportMenu() {
        System.out.println("\n-- REPORTING --");
        System.out.println("1. Salary Summary Report | 2. Top Goal Scorers Report");
    }
}
