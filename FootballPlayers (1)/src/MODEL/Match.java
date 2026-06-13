package MODEL;
import java.util.Scanner;



public class Match {
    // Private attributes
    private int matchID;
    private String date;
    private String opponentTeam;
    private String matchType;
    
    // Constructor
    public Match() {
        this.matchID = 0;
        this.date = "";
        this.opponentTeam = "";
        this.matchType = "";
    }
    
    // Constructor
    public Match(int matchID, String date, String opponentTeam, String matchType) {
        this.matchID = matchID;
        this.date = date;
        this.opponentTeam = opponentTeam;
        this.matchType = matchType;
    }
    
    // Getters
    public int getMatchID() {
        return this.matchID;
    }
    
    public String getDate() {
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
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }
    
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
    
    // Input method
    public void inputMatch() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Match ID: ");
        this.matchID = sc.nextInt();
        sc.nextLine();
        System.out.print("Input date: ");
        this.date = sc.nextLine();
        System.out.print("Input opponent team: ");
        this.opponentTeam = sc.nextLine();
        System.out.print("Input match type: ");
        this.matchType = sc.nextLine();
    }
    
    // Display method
    public void outputMatch() {
        System.out.println("Match ID: " + this.matchID);
        System.out.println("Date: " + this.date);
        System.out.println("Opponent Team: " + this.opponentTeam);
        System.out.println("Match Type: " + this.matchType);
        System.out.println("---------------------");
    }
}
