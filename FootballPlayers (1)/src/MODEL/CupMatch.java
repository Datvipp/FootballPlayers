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

    public void inputMatch(Scanner sc) {
        super.inputMatch(sc);
        System.out.print("Input cup name: ");
        this.cupName = sc.nextLine();
    }

    @Override
    public void inputMatch() {
        inputMatch(new Scanner(System.in));
    }

    @Override
    public void outputMatch() {
        super.outputMatch();
        System.out.println("Cup Name: " + this.cupName);
    }

    @Override
    public String toFileLine() {
        return super.toFileLine() + "|" + this.cupName;
    }
}
