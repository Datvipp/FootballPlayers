package SERVICES;
import MODEL.Players; // Chỉ cần dùng Players, xóa luôn import MODEL.Salary

public class SalaryManager {
    
    // Khai báo Kế toán và đường kết nối sang Hành chính
    private SalaryCalculator calculator = new SalaryCalculator();
    private ClubManager clubManager; 

    // Constructor kết nối
    public SalaryManager(ClubManager clubManager) {
        this.clubManager = clubManager;
    }



    // Case 2: Tính lương (Tự động kéo số ngày nghỉ từ hồ sơ cầu thủ)
    public void calculateMonthlySalary(int id) {
        Players p = clubManager.getPlayerById(id); // Xin hồ sơ từ ClubManager

        if (p != null) {
            System.out.println("\n=== Monthly Salary Information ===");
            System.out.println("Player Name: " + p.getName());
            System.out.println("-> Base Salary: $" + p.getSalary());
            System.out.println("-> Absent Days: " + p.getAbsentDays());
            System.out.println("-> Absent Penalty: -$" + (p.getAbsentDays() * 500.0));
            
            // Đưa cho Kế toán tính
            double finalPayout = calculator.computeFinalSalary(p);
            System.out.println("-> FINAL MONTHLY SALARY: $" + finalPayout);
        } else {
            System.out.println("Player ID not found in the Club system!");
        }
    }

    // Case 3: Tính thưởng (Tự động kéo số bàn thắng từ hồ sơ)
    public void calculateBonus(int id) {
        Players p = clubManager.getPlayerById(id); 

        if (p != null) {
            System.out.println("\n=== Bonus Information ===");
            System.out.println("Player Name: " + p.getName());
            System.out.println("-> Base Contract Bonus: $" + p.getBonus());
            System.out.println("-> Goals Scored: " + p.getGoalsScored());
            System.out.println("-> Goals Bonus: +$" + (p.getGoalsScored() * 100.0));
            
            // Đưa cho Kế toán tính
            double finalBonus = calculator.computeTotalBonus(p);
            System.out.println("-> TOTAL BONUS PAYOUT: $" + finalBonus);
        } else {
            System.out.println("Player ID not found in the Club system!");
        }
    }
    
    // TÍNH NĂNG MỚI: In Tổng Thu Nhập (Lương + Thưởng)
    public void displayTotalIncome(int id) {
        Players p = clubManager.getPlayerById(id);
        
        if (p != null) {
            System.out.println("\n=== TOTAL INCOME REPORT ===");
            System.out.println("Player Name: " + p.getName());
            
            // Gọi công thức tính tổng bạn vừa viết
            double totalIncome = calculator.computeTotalPayout(p);
            System.out.println("-> TOTAL PAYOUT (Salary + Bonus): $" + totalIncome);
        } else {
            System.out.println("Player ID not found!");
        }
    }

    // Case 4: Quét toàn bộ danh sách để kiểm tra hợp đồng
    public void validateContractRules() {
        int totalPlayers = clubManager.getCount(); // Hỏi ClubManager xem có bao nhiêu người

        if (totalPlayers == 0) {
            System.out.println("No records to validate!");
            return;
        }

        System.out.println("\n=== Validating Contract Rules ===");
        for (int i = 0; i < totalPlayers; i++) {
            Players p = clubManager.getPlayerByIndex(i); // Lấy từng người ra kiểm tra
            
            System.out.print("ID: " + p.getId() + " | Name: " + p.getName() + " | Status: " + p.getStatus());
            
            if (p.getStatus().equalsIgnoreCase("Inactive") || p.getStatus().equalsIgnoreCase("Expired")) {
                System.out.println(" -> WARNING: Contract needs renewal!");
            } else if (p.getStatus().equalsIgnoreCase("Active")) {
                System.out.println(" -> Valid and Active.");
            } else {
                System.out.println(" -> Needs Review.");
            }
        }
        System.out.println("Validation complete!");
    }
}