package MODEL;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;



public class Match {
    // Private attributes
    private int matchID;
    private LocalDate date;
    private String opponentTeam;
    private String matchType;
    private String stadium;

    // Constructor
    public Match() {
        this.matchID = 0;
        this.date = LocalDate.now();
        this.opponentTeam = "";
        this.matchType = "";
        this.stadium = "";
    }
    
    // Constructor
    public Match(int matchID, LocalDate date, String opponentTeam, String matchType, String stadium) {
        this.matchID = matchID;
        this.date = date;
        this.opponentTeam = opponentTeam;
        this.matchType = matchType;
        this.stadium = stadium;
    }
    
    // Getters
    public int getMatchID() {
        return this.matchID;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public String getOpponentTeam() {
        return this.opponentTeam;
    }

    public String getMatchType() {
        return this.matchType;
    }
    
    // Setters
    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }
    
    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getStadium(){
         return this.stadium; 
    }

    public void setStadium(String stadium){
         this.stadium = stadium; 
    }

    // doc va validate ngay thang, dung chung cho ca inputMatch() va MatchList.updateMatch()
    // lap toi khi nhap dung dinh dang, khong cho qua ngay bay (giong pattern cua TrainingManager.readDateInput())
    public static LocalDate readDateInput(Scanner sc) {
        while (true) {
            System.out.print("Input date (yyyy-MM-dd): ");
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g. 2026-01-31).");
            }
        }
    }

    // Input method
    public void inputMatch() {
        inputMatch(new Scanner(System.in));
    }

    public void inputMatch(Scanner sc) {
        while (true) {
            System.out.print("Input Match ID: ");
            String input = sc.nextLine().trim();
    
            try {
                int id = Integer.parseInt(input);
    
                if (id <= 0) {
                    System.out.println("Match ID must be greater than 0.");
                    continue;
                }
    
                this.matchID = id;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Match ID! Please enter a positive integer (Example: 1, 2, 100).");
            }
        }
    
        this.date = readDateInput(sc);
            System.out.print("Input opponent team: ");
        this.opponentTeam = sc.nextLine();
        System.out.print("Input stadium: ");
        this.stadium = sc.nextLine();
    }
    
    // Display method
    public void outputMatch() {
        System.out.println("Match ID: " + this.matchID);
        System.out.println("Date: " + this.date);
        System.out.println("Opponent Team: " + this.opponentTeam);
        System.out.println("Stadium: " + this.stadium);
    }

    // File I/O: chuyen 1 match thanh 1 dong text de ghi file
    // Format: matchType|matchID|date(yyyy-MM-dd)|opponentTeam|stadium
    // Subclass se override va goi super.toFileLine() roi noi them field rieng
    public String toFileLine() {
        return this.matchType + "|" + this.matchID + "|" + this.date + "|"
                + this.opponentTeam + "|" + this.stadium;
    }
}
