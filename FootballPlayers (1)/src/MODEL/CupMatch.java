package MODEL;
import java.util.Scanner;

public class CupMatch extends Match {
    private String cupName;
    // Constructor
    public CupMatch() {
        super();
        this.cupName = "";
    }
    
    public String getCupName() {
        return this.cupName;
    }
    public void setCupName(String cupName) {
        this.cupName = cupName;
    }

    @Override
    public void inputMatch() {
        super.inputMatch();
        Scanner sc = new Scanner(System.in);
        System.out.print("Input cup name: ");
        this.cupName = sc.nextLine();
    }

    @Override
    public void outputMatch() {
        super.outputMatch();
        System.out.println("Cup Name: " + this.cupName);
    }
}