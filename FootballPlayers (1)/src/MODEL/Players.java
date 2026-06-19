package MODEL;

import java.util.Scanner;

public abstract class Player { // [ADD] đổi "Players" -> "Player" và thêm "abstract" để làm superclass
    // Private attributes
    private String id; // [ADD] đổi int -> String để hỗ trợ format "P01" theo đề (BR1)
    private int age;
    private int number;
    private String name;
    private String national;
    private String type;
    private String status;
    private String position;
    private double salary;
    private double bonus; // Q thêm 
    private int absentDays; // Thêm vào
    private int goalsScored; // Thêm vào
    
    // Constructor
    public Players() {
        this.id = ""; // [ADD] sửa theo kiểu String
        this.age = 0;
        this.number = 0;
        this.name = "";
        this.national = "";
        this.type = "";
        this.status = "";
        this.position = "";
        this.salary = 0.0;
        this.bonus = 0.0; // Q thêm vào đó
        this.absentDays = 0; // Thêm vào
        this.goalsScored = 0; // Thêm vào
    }
    
    // Constructor
    public Player(String id, int age, int number, String name, String national, // [ADD] id: int -> String
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
        this.absentDays = 0; // Thêm vào
        this.goalsScored = 0; // Thêm vào
    }
    
    // Getters
    public String getId() { // [ADD] đổi kiểu trả về int -> String
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
    } // Thêm vào

    public double getBonus() { 
        return this.bonus; 
    } // Q thêm vào đó
    
    public int getGoalsScored() {
        return this.goalsScored;
    } // Thêm vào
    

    // Setters
    public void setId(String id) { // [ADD] đổi kiểu int -> String
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
    } // Q thêm vào đó

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    } // Thêm vào

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    } // Thêm vào

    // [ADD] Hàm kiểm tra format ID theo đề: bắt đầu bằng "P" + số, ví dụ P01, P02...
    public static boolean isValidIdFormat(String id) { // [ADD]
        if (id == null) return false;
        return id.matches("^P\\d{2,}$"); // P + ít nhất 2 chữ số, có thể chỉnh số chữ số tùy ý
    }

    // Input method
    public void inputPlayers() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter player ID: ");
        String inputId = sc.nextLine();
        while (!isValidIdFormat(inputId)) {
            System.out.println("Invalid ID format! ID must be like P01, P02,...");
            System.out.println("Enter player ID: ");
            inputId = sc.nextLine();
        }
        this.id = inputId;
        
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

        // [REMOVE] Bỏ 2 dòng hỏi type bằng text vì type đã được xác định
        // từ bước chọn 1/2 ở addPlayer() và set sẵn trước khi gọi inputPlayers()
        // System.out.println("Enter player type (Regular player|Star player): ");
        // this.type = sc.nextLine();
        
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
        System.out.println("Goals Scored: " + this.goalsScored); // Thêm vào
        System.out.println("Absent Days: " + this.absentDays); // Thêm vào
    }

    // [ADD] Method trừu tượng để mỗi subclass tự tính bonus (Polymorphism - đúng yêu cầu OOP mục 5)
    public abstract double calculateBonus(int monthlyPerformancePoints);

    // [ADD] Method tính tổng lương tháng, dùng chung công thức BR27: Total = Base + Bonus
    public double calculateMonthlySalary(int monthlyPerformancePoints) {
        return this.salary + calculateBonus(monthlyPerformancePoints);
    }
}
