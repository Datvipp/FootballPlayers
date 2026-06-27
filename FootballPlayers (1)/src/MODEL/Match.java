package MODEL;
import java.util.Scanner;



public class Match {
    // Private attributes
    private int matchID;
    private String date;
    private String opponentTeam;
    private String matchType;
    private String stadium;

    // Constructor
    public Match() {
        this.matchID = 0;
        this.date = "";
        this.opponentTeam = "";
        this.matchType = "";
        this.stadium = "";
    }
    
    // Constructor
    public Match(int matchID, String date, String opponentTeam, String matchType, String stadium) {
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
    
    public void setDate(String date) {
        this.date = date;
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
    // Input method
    public void inputMatch() {
        inputMatch(new Scanner(System.in));
    }

    public void inputMatch(Scanner sc) {
        System.out.print("Input Match ID: ");
        this.matchID = sc.nextInt();
        sc.nextLine();
        System.out.print("Input date (dd/MM/yyyy): ");
        this.date = sc.nextLine();
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
}
