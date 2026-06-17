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

    @Override
    public void inputMatch() {
        super.inputMatch();
        Scanner sc = new Scanner(System.in);
        System.out.print("Input league name: ");
        this.leagueName = sc.nextLine();
    }

    @Override
    public void outputMatch() {
        super.outputMatch();
        System.out.println("League Name: " + this.leagueName);
    }
}