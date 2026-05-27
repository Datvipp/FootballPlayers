package View;

import SERVICES.TrainingManager;
import SERVICES.ClubManager;
import SERVICES.MatchList;
import SERVICES.SalaryManager;
import java.util.Scanner;

public class Main {
    private static ClubManager clubManager = new ClubManager();
    private static TrainingManager trainingManager = new TrainingManager();
    private static MatchList matchManager = new MatchList();
    private static SalaryManager salaryManager = new SalaryManager();
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
                case 1: clubManager(); break; //duymanh
                case 2: trainingMenu(); break; // Dat
                case 3: matchMenu(); break; //Khoa
                case 4: salaryMenu(); break; //Quan
                case 5: reportMenu(); break;
                case 0: System.out.println("Exiting program..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
//Cái này của Mạnh
    private static void clubManager() {
    int choice;

    do {
        System.out.println("\n===== PLAYER MANAGEMENT =====");
        System.out.println("1. Add Player");
        System.out.println("2. Update Info");
        System.out.println("3. Deactivate");
        System.out.println("4. View All");
        System.out.println("5. Search");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                clubManager.addPlayer();
                break;

            case 2:
                clubManager.updateInfo();
                break;

            case 3:
                System.out.print("Enter player ID to deactivate: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                clubManager.deactivatePlayer(id);
                break;

            case 4:
                clubManager.viewAllPlayers();
                break;

            case 5:
                System.out.print("Enter player ID to search: ");
                int searchId = scanner.nextInt();
                scanner.nextLine();

                clubManager.searchPlayer(searchId);
                break;

            case 0:
                System.out.println("Back to main menu...");
                break;

            default:
                System.out.println("Invalid choice!");
        }

    } while (choice != 0);
}
//Cái này của Đạt
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
        int choice;

        do {

            System.out.println("\n-- MATCH MANAGEMENT --");
            System.out.println("1. Create Match");
            System.out.println("2. Update Match");
            System.out.println("3. View History");
            System.out.println("4. Search Match");
            System.out.println("5. Delete Match");
            System.out.println("0. Back");
    
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
    
                case 1:
    
                    matchManager.addMatch();
                    break;
    
                case 2:
    
                    System.out.print("Input Match ID to update: ");
                    int updateID = scanner.nextInt();
                    scanner.nextLine();
    
                    matchManager.updateMatch(updateID);
                    break;
    
                case 3:
    
                    matchManager.displayMatchList();
                    break;
    
                case 4:
    
                    System.out.print("Input Match ID to search: ");
                    int searchID = scanner.nextInt();
                    scanner.nextLine();
    
                    matchManager.searchMatchByID(searchID);
                    break;
    
                case 5:
    
                    System.out.print("Input Match ID to delete: ");
                    int deleteID = scanner.nextInt();
                    scanner.nextLine();
    
                    matchManager.deleteMatch(deleteID);
                    break;
    
                case 0:
    
                    System.out.println("Back to main menu");
                    break;
    
                default:
    
                    System.out.println("Invalid choice!");
            }
    
        } while (choice != 0);
    }

    private static void salaryMenu() {
        int choice;
        do {
            System.out.println("\n-- CONTRACT & SALARY MANAGEMENT --");
            System.out.println("1. Calculate Monthly Salary");
            System.out.println("2. Calculate Bonus");
            System.out.println("3. Validate Rules");
            System.out.println("0. Exit");
            System.out.println("Your Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter player ID to calculate monthly salary: ");
                    int salaryId = scanner.nextInt();
                    scanner.nextLine();
                    
                    salaryManager.calculateMonthlySalary(salaryId);
                    break;
                    
                case 2:
                    System.out.print("Enter player ID to calculate bonus: ");
                    int bonusId = scanner.nextInt();
                    scanner.nextLine();
                    
                    salaryManager.calculateBonus(bonusId);
                    break;
                    
                case 3:
                    salaryManager.validateContractRules();
                    break;
                    
                case 0:
                    System.out.println("Back to main menu");
                    break;
                    
                default:
                    System.out.println("Invalid choice!");        
            }
        } while(choice != 0);
}
    //comming soon
    private static void reportMenu() {
        System.out.println("\n-- REPORTING --");
        System.out.println("1. Salary Summary Report | 2. Top Goal Scorers Report");
    }
}
