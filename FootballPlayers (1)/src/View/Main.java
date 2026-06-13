package View;

import SERVICES.TrainingManager;
import SERVICES.ClubManager;
import SERVICES.MatchList;
import SERVICES.SalaryManager;
import java.util.Scanner;

public class Main {

    private ClubManager clubManager;
    private TrainingManager trainingManager;
    private MatchList matchManager;
    private SalaryManager salaryManager;
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        clubManager = new ClubManager();
        trainingManager = new TrainingManager(scanner);
        matchManager = new MatchList();
        salaryManager = new SalaryManager();
    }

    public ClubManager getClubManager() {
        return clubManager;
    }

    public void setClubManager(ClubManager clubManager) {
        this.clubManager = clubManager;
    }

    public TrainingManager getTrainingManager() {
        return trainingManager;
    }

    public void setTrainingManager(TrainingManager trainingManager) {
        this.trainingManager = trainingManager;
    }

    public MatchList getMatchManager() {
        return matchManager;
    }

    public void setMatchManager(MatchList matchManager) {
        this.matchManager = matchManager;
    }

    public SalaryManager getSalaryManager() {
        return salaryManager;
    }

    public void setSalaryManager(SalaryManager salaryManager) {
        this.salaryManager = salaryManager;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
        if (trainingManager != null) {
            trainingManager.setScanner(scanner);
        }
    }
    

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
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

            choice = readIntChoice();

            switch (choice) {
                case 1:
                    playerMenu();
                    break;
                case 2:
                    trainingMenu();
                    break;
                case 3:
                    matchMenu();
                    break;
                case 4:
                    salaryMenu();
                    break;
                case 5:
                    reportMenu();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    private void playerMenu() {
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

            choice = readIntChoice();

            switch (choice) {
                case 1:
                    clubManager.addPlayer();
                    break;
                case 2:
                    clubManager.updateInfo();
                    break;
                case 3:
                    System.out.print("Enter player ID to deactivate: ");
                    int id = readIntChoice();
                    clubManager.deactivatePlayer(id);
                    break;
                case 4:
                    clubManager.viewAllPlayers();
                    break;
                case 5:
                    System.out.print("Enter player ID to search: ");
                    int searchId = readIntChoice();
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

    private void trainingMenu() {
        int choice;

        do {
            System.out.println("\n-- TRAINING MANAGEMENT --");
            System.out.println("1. Create Session");
            System.out.println("2. Record Attendance");
            System.out.println("3. View History");
            System.out.println("4. Search Session");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            choice = readIntChoice();

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
                case 4:
                    trainingManager.searchSession();
                    break;
                case 0:
                    System.out.println("Back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    private void matchMenu() {
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

            choice = readIntChoice();

            switch (choice) {
                case 1:
                    matchManager.addMatch();
                    break;
                case 2:
                    System.out.print("Input Match ID to update: ");
                    int updateID = readIntChoice();
                    matchManager.updateMatch(updateID);
                    break;
                case 3:
                    matchManager.displayMatchList();
                    break;
                case 4:
                    System.out.print("Input Match ID to search: ");
                    int searchID = readIntChoice();
                    matchManager.searchMatchByID(searchID);
                    break;
                case 5:
                    System.out.print("Input Match ID to delete: ");
                    int deleteID = readIntChoice();
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

    private void salaryMenu() {
        int choice;

        do {
            System.out.println("\n-- CONTRACT & SALARY MANAGEMENT --");
            System.out.println("1. Add Salary");
            System.out.println("2. Calculate Monthly Salary");
            System.out.println("3. Calculate Bonus");
            System.out.println("4. Validate Rules");
            System.out.println("0. Back");
            System.out.print("Your Choice: ");

            choice = readIntChoice();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Adding New Salary Record ---");
                    salaryManager.addSalary();
                    break;
                case 2:
                    System.out.print("Enter player ID to calculate monthly salary: ");
                    int salaryId = readIntChoice();
                    salaryManager.calculateMonthlySalary(salaryId);
                    break;
                case 3:
                    System.out.print("Enter player ID to calculate bonus: ");
                    int bonusId = readIntChoice();
                    salaryManager.calculateBonus(bonusId);
                    break;
                case 4:
                    salaryManager.validateContractRules();
                    break;
                case 0:
                    System.out.println("Back to main menu");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    private int readIntChoice() {
        while (true) {
            if (!scanner.hasNextLine()) {
                return 0;
            }

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void reportMenu() {
        System.out.println("\n-- REPORTING --");
        System.out.println("1. Salary Summary Report | 2. Top Goal Scorers Report");
    }
}
