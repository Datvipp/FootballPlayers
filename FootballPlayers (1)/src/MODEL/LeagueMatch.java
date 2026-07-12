package MODEL;
import java.util.Scanner;   

public class LeagueMatch extends Match {
    private String leagueName;
    // Constructor
    public LeagueMatch() {
        super();
        this.leagueName = "";
    }
    
    public String getLeagueName() {
        return this.leagueName;
    }
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void inputMatch(Scanner sc) {
        super.inputMatch(sc);
        System.out.print("Input league name: ");
        this.leagueName = sc.nextLine();
    }

    @Override
    public void inputMatch() {
        inputMatch(new Scanner(System.in));
    }

    @Override
    public void outputMatch() {
        super.outputMatch();
        System.out.println("League Name: " + this.leagueName);
    }

    @Override
    public String toFileLine() {
        return super.toFileLine() + "|" + this.leagueName;
    }
}
