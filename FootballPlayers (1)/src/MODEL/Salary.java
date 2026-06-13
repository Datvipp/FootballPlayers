package MODEL;
import java.util.Scanner;

public class Salary {
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
    public void nhapSalary() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Player ID: ");
        this.playerID = sc.nextInt();
        
        System.out.print("Input Base Salary: ");
        this.baseSalary = sc.nextDouble();
        
        System.out.print("Input Bonus: ");
        this.bonus = sc.nextDouble();
        sc.nextLine(); // Xóa bộ đệm sau khi nhập số để nhập chuỗi không bị lỗi
        
        System.out.print("Input Contract Status (e.g., Active, Expired, Pending): ");
        this.contractStatus = sc.nextLine();
    }
    
    // Display method
    public void xuatSalary() {
        System.out.println("Player ID: " + this.playerID);
        System.out.println("Base Salary: $" + this.baseSalary);
        System.out.println("Bonus: $" + this.bonus);
        System.out.println("Contract Status: " + this.contractStatus);
        System.out.println("---------------------");
    }
}
