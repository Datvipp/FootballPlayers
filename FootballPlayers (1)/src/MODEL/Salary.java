package MODEL;
import java.util.Scanner;
import java.util.Objects;

/**
 * Salary class represents salary information for a player
 * Inherits from Entity base class
 */
public class Salary extends Entity {
    // Private attributes
    private int playerID;
    private double baseSalary;
    private double bonus;
    private String contractStatus;
    
    // Constructor
    public Salary() {
        this.playerID = 0;
        this.baseSalary = 0.0;
        this.bonus = 0.0;
        this.contractStatus = "";
    }
    
    // Parameterized Constructor
    public Salary(int playerID, double baseSalary, double bonus, String contractStatus) {
        this.playerID = playerID;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.contractStatus = contractStatus;
    }
    
    // Getters
    public int getPlayerID() {
        return this.playerID;
    }
    
    public double getBaseSalary() {
        return this.baseSalary;
    }
    
    public double getBonus() {
        return this.bonus;
    }
    
    public String getContractStatus() {
        return this.contractStatus;
    }
    
    // Setters
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
    
    // Input method
    @Override
    public void input(Scanner sc) {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        System.out.print("Input Player ID: ");
        this.playerID = sc.nextInt();
        
        System.out.print("Input Base Salary: ");
        this.baseSalary = sc.nextDouble();
        
        System.out.print("Input Bonus: ");
        this.bonus = sc.nextDouble();
        sc.nextLine();
        
        System.out.print("Input Contract Status (e.g., Active, Expired, Pending): ");
        this.contractStatus = sc.nextLine();
    }

    public void nhapSalary() {
        Scanner sc = new Scanner(System.in);
        this.input(sc);
    }

    public void nhapSalary(Scanner sc) {
        this.input(sc);
    }
    
    // Display method
    @Override
    public void output() {
        System.out.println(this.toString());
    }

    public void xuatSalary() {
        this.output();
    }

    @Override
    public int getId() {
        return this.playerID;
    }

    @Override
    public String toString() {
        return "Player ID: " + this.playerID + "\n"
                + "Base Salary: $" + this.baseSalary + "\n"
                + "Bonus: $" + this.bonus + "\n"
                + "Contract Status: " + this.contractStatus + "\n"
                + "---------------------";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Salary)) {
            return false;
        }
        Salary other = (Salary) obj;
        return this.playerID == other.playerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerID);
    }
}
