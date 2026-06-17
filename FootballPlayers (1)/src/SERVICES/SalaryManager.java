package SERVICES;
import MODEL.Salary;
import java.util.Scanner;

public class SalaryManager {
    Salary [] arr = new Salary[100];
    int count = 0;
    
    // Khai báo "Nhân viên Kế toán" để sử dụng các công thức
    SalaryCalculator calculator = new SalaryCalculator(); 
    
    // Input Data (Giữ nguyên của bạn)
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

    // Case 2: Đã tích hợp Kế Toán tính Lương
    public void calculateMonthlySalary(int id) {
        boolean found = false;
        Scanner sc = new Scanner(System.in); // Mở Scanner để nhập số ngày nghỉ

        for (int i = 0; i < count; i++) {
            if (arr[i].getPlayerID() == id) {
                System.out.println("\n=== Monthly Salary Information ===");
                arr[i].xuatSalary();
                
                // Yêu cầu nhập thêm dữ liệu thực tế của tháng này
                System.out.print("Enter number of absent days this month: ");
                int absentDays = sc.nextInt();
                
                // Gọi Kế toán ra tính toán
                double finalPayout = calculator.computeFinalSalary(arr[i], absentDays);
                
                // In biên lai
                System.out.println("-> Absent Penalty: -$" + (absentDays * 100.0));
                System.out.println("-> TOTAL MONTHLY PAYOUT: $" + finalPayout);
                
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Player salary record not found!");
        }
    }

    // Case 3: Đã tích hợp Kế Toán tính Thưởng theo Bàn thắng
    public void calculateBonus(int id) {
        boolean found = false;
        Scanner sc = new Scanner(System.in); // Mở Scanner để nhập số bàn thắng

        for (int i = 0; i < count; i++) {
            if (arr[i].getPlayerID() == id) {
                System.out.println("\n=== Bonus Information ===");
                System.out.println("Player ID: " + arr[i].getPlayerID());
                
                // Yêu cầu nhập số bàn thắng
                System.out.print("Enter number of goals scored this month: ");
                int goals = sc.nextInt();
                
                // Gọi Kế toán ra tính toán
                double finalBonus = calculator.computeTotalBonus(arr[i], goals);
                
                // In biên lai
                System.out.println("-> Base Contract Bonus: $" + arr[i].getBonus());
                System.out.println("-> Goals Bonus (" + goals + " goals x $500): +$" + (goals * 500.0));
                System.out.println("--------------------------------");
                System.out.println("-> TOTAL BONUS PAYOUT: $" + finalBonus);
                
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Player salary record not found!");
        }
    }

    // Case 4: Validate Rules (Giữ nguyên của bạn)
    public void validateContractRules() {
        if (count == 0) {
            System.out.println("No records to validate!");
            return;
        }

        System.out.println("\n=== Validating Contract Rules ===");
        for (int i = 0; i < count; i++) {
            System.out.print("Player ID: " + arr[i].getPlayerID() + " | Status: " + arr[i].getContractStatus());
            
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