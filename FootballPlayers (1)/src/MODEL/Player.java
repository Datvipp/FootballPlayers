package MODEL;

import java.util.Scanner;

public abstract class Player {
    // Private attributes
    private String id;
    private int age;
    private int number;
    private String name;
    private String national;
    private String type;
    private String status;
    private String position;
    private double salary;
    private double bonus;
    private int absentDays;
    private int goalsScored;
    
    // Constructor
    public Player() {
        this.id = "";
        this.age = 0;
        this.number = 0;
        this.name = "";
        this.national = "";
        this.type = "";
        this.status = "";
        this.position = "";
        this.salary = 0.0;
        this.bonus = 0.0;
        this.absentDays = 0;
        this.goalsScored = 0;
    }
    
    // Constructor
    public Player(String id, int age, int number, String name, String national,
                   String type, String status, String position, double salary) {
        this.id = id;
        this.age = age;
        this.number = number;
        this.name = name;
        this.national = national;
        this.type = type;
        this.status = status;
        this.position = position;
        this.salary = salary;
        this.absentDays = 0;
        this.goalsScored = 0;
    }
    
    // Getters
    public String getId() {
        return this.id;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNational() {
        return this.national;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public double getSalary() {
        return this.salary;
    }

    public int getAbsentDays() {
        return this.absentDays;
    }

    public double getBonus() { 
        return this.bonus; 
    }
    
    public int getGoalsScored() {
        return this.goalsScored;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setNational(String national) {
        this.national = national;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(double bonus) { 
        this.bonus = bonus; 
    }

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    // Input method
    public void inputPlayers() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter player ID: ");
        this.id = sc.nextLine();
        
        System.out.println("Enter full name: ");
        this.name = sc.nextLine();
        
        System.out.println("Enter age: ");
        this.age = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Enter nationality: ");
        this.national = sc.nextLine();
        
        System.out.println("Enter position: ");
        this.position = sc.nextLine();
        
        System.out.println("Enter shirt number: ");
        this.number = sc.nextInt();
        sc.nextLine();

        // Type is set by caller (addPlayer) so no prompt here
        
        System.out.println("Enter base salary: ");
        this.salary = sc.nextDouble();
        sc.nextLine();
        
        System.out.println("Enter status (Active|Inactive): ");
        this.status = sc.nextLine();
    }
    
    // Display method
    public void displayPlayer() {
        System.out.println("===== PLAYER INFORMATION =====");
        System.out.println("Player ID: " + this.id);
        System.out.println("Full Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Nationality: " + this.national);
        System.out.println("Position: " + this.position);
        System.out.println("Shirt Number: " + this.number);
        System.out.println("Player Type: " + this.type);
        System.out.println("Base Salary: " + this.salary);
        System.out.println("Status: " + this.status);
        System.out.println("Goals Scored: " + this.goalsScored);
        System.out.println("Absent Days: " + this.absentDays);
    }

    public abstract double calculateBonus(int monthlyPerformancePoints);

    public double calculateMonthlySalary(int monthlyPerformancePoints) {
        return this.salary + calculateBonus(monthlyPerformancePoints);
    }
}
