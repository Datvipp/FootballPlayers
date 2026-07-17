package View;

import IO.SalaryIO;
import MODEL.Player;
import SERVICES.ClubManager;
import SERVICES.MatchList;
import SERVICES.SalaryManager;
import SERVICES.TrainingManager; // Đã import chính xác file SalaryIO từ thư mục IO
import java.util.Scanner;

public class Main {

    private ClubManager clubManager;
    private TrainingManager trainingManager;
    private MatchList matchManager;
    private SalaryManager salaryManager;
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        clubManager = new ClubManager(scanner);
        clubManager.loadFromFile("data/players.txt");
        trainingManager = new TrainingManager(scanner, clubManager);
        matchManager = new MatchList(scanner);
        salaryManager = new SalaryManager(clubManager);
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
                case 0:
                    clubManager.saveToFile("data/players.txt");
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
            System.out.println("6. View Player Details");
            System.out.println("7. Update Player Stats (Goals & Absences)"); 
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
                    String id = scanner.nextLine();
                    clubManager.deactivatePlayer(id);
                    break;
                case 4:
                    clubManager.viewAllPlayers();
                    break;
                case 5:
                    System.out.println("Search by: 1.Name  2.Position  3.Nationality  4.Status");
                    System.out.print("Choose search type: ");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    clubManager.searchPlayers(searchType, keyword);
                    break;
                case 6:
                    System.out.print("Enter player ID to view: ");
                    String viewId = scanner.nextLine();
                    clubManager.viewPlayerDetails(viewId);
                    break;
                case 7: 
                    System.out.println("=== Update Player Stats (Goals & Absences for the month) ===");
                    clubManager.updatePlayerStats();
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
            System.out.println("5. Update Session");
            System.out.println("6. Delete Session");
            System.out.println("7. Save to File");
            System.out.println("8. Load from File");
            System.out.println("9. Delete from File");
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
                case 5:
                    trainingManager.updateSession();
                    break;
                case 6:
                    trainingManager.deleteSession();
                    break;
                case 7:
                    trainingManager.saveToFile();
                    break;
                case 8:
                    trainingManager.loadFromFile();
                    break;
                case 9:
                    trainingManager.deleteFromFile();
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
            System.out.println("6. Save Matches to File");
            System.out.println("7. Load Matches from File");
            System.out.println("8. Delete Match File");
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
                case 6:
                    System.out.print("Enter file name to save (e.g. matches.txt): ");
                    matchManager.saveToFile(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter file name to load (e.g. matches.txt): ");
                    matchManager.loadFromFile(scanner.nextLine());
                    break;
                case 8:
                    System.out.print("Enter file name to delete (e.g. matches.txt): ");
                    matchManager.deleteFile(scanner.nextLine());
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
            System.out.println("\n== CONTRACT & SALARY MANAGEMENT ==");
            System.out.println("1. Calculate Monthly Salary");
            System.out.println("2. Calculate Bonus");
            System.out.println("3. View Total Income (Salary + Bonus)");
            System.out.println("4. Validate Rules");
            System.out.println("5. Export Salary Report (CSV)");
            System.out.println("6. Export Player Payslip (TXT)");
            System.out.println("0. Back");
            System.out.print("Your Choice: ");

            choice = readIntChoice(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter player ID to calculate monthly salary: ");
                    String salaryId = scanner.nextLine();
                    salaryManager.calculateMonthlySalary(salaryId);
                    break;
                case 2:
                    System.out.print("Enter player ID to calculate bonus: ");
                    String bonusId = scanner.nextLine();
                    salaryManager.calculateBonus(bonusId);
                    break;
                case 3:
                    System.out.print("Enter player ID to view total income: ");
                    String totalId = scanner.nextLine();
                    salaryManager.displayTotalIncome(totalId);
                    break;
                case 4:
                    salaryManager.validateContractRules();
                    break;
                case 5:
                    System.out.println("Exporting Salary Report...");
                    SalaryIO csvIO = new SalaryIO();
                    // Đã đưa vào thư mục ExportData
                    csvIO.exportSalaryReport(clubManager, salaryManager.getCalculator(), "ExportData/Salary_Reports/SalaryReport.csv");
                    break;
                case 6:
                    System.out.print("Enter player ID to export payslip: ");
                    String payslipId = scanner.nextLine();
                    Player p = clubManager.getPlayerById(payslipId);
                    
                    if (p != null) {
                        SalaryIO txtIO = new SalaryIO();
                        // Đã đưa vào thư mục ExportData
                        txtIO.exportPlayerPayslipToTXT(p, salaryManager.getCalculator(), "ExportData/Player_Payslips");
                    } else {
                        System.out.println("Player ID not found! Cannot export payslip.");
                    }
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
}
