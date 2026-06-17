package MODEL;
import java.util.Scanner;

public class FriendlyMatch extends Match {
    private String friendlyReason;
    // Constructor
    public FriendlyMatch() {
        super();
        this.friendlyReason = "";
    }
    
    public String getFriendlyReason() {
        return this.friendlyReason;
    }
    public void setFriendlyReason(String friendlyReason) {
        this.friendlyReason = friendlyReason;
    }

    @Override
    public void inputMatch() {
        super.inputMatch();
        Scanner sc = new Scanner(System.in);
        System.out.print("Input friendly reason: ");
        this.friendlyReason = sc.nextLine();
    }

    @Override
    public void outputMatch() {
        super.outputMatch();
        System.out.println("Friendly Reason: " + this.friendlyReason);
    }
}