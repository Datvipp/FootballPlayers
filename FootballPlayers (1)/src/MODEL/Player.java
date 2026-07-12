package MODEL;

import java.util.Scanner;

public abstract class Player {
    // Private attributes
    private static final String[] VALID_POSITIONS = {"Goalkeeper", "Defender", "Midfielder", "Forward"};
    private static final String[] VALID_STATUSES = {"Active", "Inactive"};
    private static final String ID_PATTERN = "^[A-Z][0-9]{2,}$"; // vd: P01, P02
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

    public boolean isValidId(String id) {
        if (id == null) return false;
            return id.trim().matches(ID_PATTERN);
}

    public boolean isValidPosition(String pos) {
        if (pos == null) return false;
            for (String p : VALID_POSITIONS) {
                if (p.equalsIgnoreCase(pos.trim())) return true;
    }
    return false;
}

    public boolean isValidStatus(String status) {
        if (status == null) return false;
        for (String s : VALID_STATUSES) {
            if (s.equalsIgnoreCase(status.trim())) return true;
    }
    return false;
}
    // Input method
   public void inputPlayers(Scanner sc) {
    String tempId;
    do {
        System.out.println("Enter player ID (format: P01, P02,...): ");
        tempId = sc.nextLine().trim();
        if (!isValidId(tempId)) System.out.println("Invalid ID format! Must be 1 uppercase letter + digits, e.g. P01.");
    } while (!isValidId(tempId));
    this.id = tempId;

    String tempName;
    do {
        System.out.println("Enter full name: ");
        tempName = sc.nextLine().trim();
        if (tempName.isEmpty()) System.out.println("Full name cannot be empty!");
    } while (tempName.isEmpty());
    this.name = tempName;

    int tempAge;
    do {
        System.out.println("Enter age (16-45): ");
        tempAge = readIntInput(sc);
        if (tempAge < 16 || tempAge > 45) System.out.println("Age must be between 16 and 45!");
    } while (tempAge < 16 || tempAge > 45);
    this.age = tempAge;

    String tempNational;
    do {
        System.out.println("Enter nationality: ");
        tempNational = sc.nextLine().trim();
        if (tempNational.isEmpty()) System.out.println("Nationality cannot be empty!");
    } while (tempNational.isEmpty());
    this.national = tempNational;

    String tempPosition;
    do {
        System.out.println("Enter position (Goalkeeper/Defender/Midfielder/Forward): ");
        tempPosition = sc.nextLine().trim();
        if (!isValidPosition(tempPosition)) System.out.println("Invalid position!");
    } while (!isValidPosition(tempPosition));
    this.position = tempPosition;

    int tempNumber;
    do {
        System.out.println("Enter shirt number (1-99): ");
        tempNumber = readIntInput(sc);
        if (tempNumber < 1 || tempNumber > 99) System.out.println("Shirt number must be between 1 and 99!");
    } while (tempNumber < 1 || tempNumber > 99);
    this.number = tempNumber;

    // Type is set by caller (addPlayer) so no prompt here

    double tempSalary;
    do {
        System.out.println("Enter base salary: ");
        tempSalary = readDoubleInput(sc);
        if (tempSalary <= 0) System.out.println("Base salary must be greater than 0!");
    } while (tempSalary <= 0);
    this.salary = tempSalary;

    String tempStatus;
    do {
        System.out.println("Enter status (Active/Inactive): ");
        tempStatus = sc.nextLine().trim();
        if (!isValidStatus(tempStatus)) System.out.println("Invalid status!");
    } while (!isValidStatus(tempStatus));
    this.status = tempStatus;
}

    private int readIntInput(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please enter a valid number.");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private double readDoubleInput(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty! Please enter a valid number.");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
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
    
    public String toFileLine() {
        return this.type + "|" + this.id + "|" + this.name + "|" + this.age + "|"
                + this.national + "|" + this.position + "|" + this.number + "|"
                + this.salary + "|" + this.status + "|" + this.absentDays + "|" + this.goalsScored;
}

    public double calculateMonthlySalary(int monthlyPerformancePoints) {
        return this.salary + calculateBonus(monthlyPerformancePoints);
    }
}
