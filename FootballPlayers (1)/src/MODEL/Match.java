package MODEL;
import java.util.Scanner;
import java.util.Objects;

/**
 * Match class represents a football match
 * Inherits from Entity base class
 */
public class Match extends Entity {
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
    @Override
    public void input(Scanner sc) {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
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

    public void inputMatch() {
        Scanner sc = new Scanner(System.in);
        this.input(sc);
    }

    public void inputMatch(Scanner sc) {
        this.input(sc);
    }
    
    // Display method
    @Override
    public void output() {
        System.out.println(this.toString());
    }

    public void outputMatch() {
        this.output();
    }

    @Override
    public int getId() {
        return this.matchID;
    }

    @Override
    public String toString() {
        return "Match ID: " + this.matchID + "\n"
                + "Date: " + this.date + "\n"
                + "Opponent Team: " + this.opponentTeam + "\n"
                + "Match Type: " + this.matchType + "\n"
                + "---------------------";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Match)) {
            return false;
        }
        Match other = (Match) obj;
        return this.matchID == other.matchID
                && Objects.equals(this.date, other.date)
                && Objects.equals(this.opponentTeam, other.opponentTeam)
                && Objects.equals(this.matchType, other.matchType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.matchID, this.date, this.opponentTeam, this.matchType);
    }
}
