package SERVICES;
import MODEL.Salary;

import java.util.Scanner;

public class SalaryManager {
    Salary [] arr = new Salary[100];
    int count = 0;
    
    // Input Data
    public void addSalary() {
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        do {
            arr[count] = new Salary();
            arr[count].nhapSalary();
            count++;
            System.out.println("Add more (true|false)?: ");
            
            cont = sc.nextBoolean();
            
        } while (cont == true && count < 100);
    }

    // Case 1: Calculate Monthly Salary
    public void calculateMonthlySalary(int id) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Player ID to calculate monthly salary: ");
        
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (arr[i].getPlayerID() == id) {
                System.out.println("=== Monthly Salary Information ===");
                arr[i].xuatSalary();
                // Giả sử lương tháng bằng lương cơ bản (có thể cộng thêm phụ phí nếu bạn muốn)
                System.out.println("-> Total Monthly Payout: $" + arr[i].getBaseSalary());
                
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Player salary record not found!");
        }
    }

    // Case 2: Calculate Bonus
    public void calculateBonus(int id) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Player ID to calculate bonus:");
        
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (arr[i].getPlayerID() == id) {
                System.out.println("=== Bonus Information ===");
                System.out.println("Player ID: " + arr[i].getPlayerID());
                System.out.println("Bonus Amount: $" + arr[i].getBonus());
                
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Player salary record not found!");
        }
    }

    // Case 3: Validate Rules
    public void validateContractRules() {
        if (count == 0) {
            System.out.println("No records to validate!");
            return;
        }

        System.out.println("=== Validating Contract Rules ===");
        for (int i = 0; i < count; i++) {
            System.out.print("Player ID: " + arr[i].getPlayerID() + " | Status: " + arr[i].getContractStatus());
            
            // Kiểm tra trạng thái hợp đồng để đưa ra cảnh báo
            if (arr[i].getContractStatus().equalsIgnoreCase("Expired")) {
                System.out.println(" -> WARNING: Contract needs renewal!");
            } else if (arr[i].getContractStatus().equalsIgnoreCase("Active")) {
                System.out.println(" -> Valid and Active.");
            } else {
                System.out.println(" -> Needs Review (Pending/Unknown).");
            }
        }
        System.out.println("Validation complete!");
    }
}
