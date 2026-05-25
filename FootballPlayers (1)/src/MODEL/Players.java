package Model;

import java.util.Scanner;


public class Players {
    // Private attributes
    private int id;
    private int age;
    private int number;
    private String name;
    private String national;
    private String type;
    private String status;
    private String position;
    private double salary;
    
    // Constructor
    public Players() {
        this.id = 0;
        this.age = 0;
        this.number = 0;
        this.name = "";
        this.national = "";
        this.type = "";
        this.status = "";
        this.position = "";
        this.salary = 0.0;
    }
    
    // Constructor
    public Players(int id, int age, int number, String name, String national, 
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
    }
    
    // Getters
    public int getId() {
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
    
    // Setters
    public void setId(int id) {
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
    
    // Input method
    public void inputPlayers() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter player ID: ");
        this.id = sc.nextInt();
        sc.nextLine();
        
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

        System.out.println("Enter player type (Regular player|Star player): ");
        this.type = sc.nextLine();
        
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
    }
}
