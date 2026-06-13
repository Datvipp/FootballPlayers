package MODEL;

import java.util.Scanner;
import java.util.Objects;

/**
 * Players class represents a football player
 * Inherits from Entity base class
 */
public class Players extends Entity {
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
    @Override
    public void input(Scanner sc) {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
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

    public void inputPlayers() {
        Scanner sc = new Scanner(System.in);
        this.input(sc);
    }

    public void inputPlayers(Scanner sc) {
        this.input(sc);
    }
    
    // Display method
    @Override
    public void output() {
        System.out.println(this.toString());
    }

    public void displayPlayer() {
        this.output();
    }

    @Override
    public String toString() {
        return "===== PLAYER INFORMATION =====\n"
                + "Player ID: " + this.id + "\n"
                + "Full Name: " + this.name + "\n"
                + "Age: " + this.age + "\n"
                + "Nationality: " + this.national + "\n"
                + "Position: " + this.position + "\n"
                + "Shirt Number: " + this.number + "\n"
                + "Player Type: " + this.type + "\n"
                + "Base Salary: " + this.salary + "\n"
                + "Status: " + this.status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Players)) {
            return false;
        }
        Players other = (Players) obj;
        return this.id == other.id
                && Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
