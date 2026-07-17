package IO;

import MODEL.Player;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SalaryIO {
    
    // 1. Xuất báo cáo lương tổng hợp của toàn bộ cầu thủ ra file CSV
    public void exportSalaryReport(ClubManager clubManager, SalaryCalculator calculator, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Viết tiêu đề cột (Header)
            writer.write("ID,Name,Base Salary,Absent Days,Final Salary,Base Bonus,Goals Scored,Total Bonus,Total Payout,Status\n");
            
            int totalPlayers = clubManager.getCount();
            for (int i = 0; i < totalPlayers; i++) {
                Player p = clubManager.getPlayerByIndex(i);
                if (p != null) {
                    // Sử dụng Calculator để tính toán
                    double finalSalary = calculator.computeFinalSalary(p);
                    double totalBonus = calculator.computeTotalBonus(p);
                    double totalPayout = calculator.computeTotalPayout(p);
                    
                    // Viết dữ liệu từng cầu thủ thành 1 dòng, cách nhau bằng dấu phẩy
                    writer.write(String.format("%s,%s,%.2f,%d,%.2f,%.2f,%d,%.2f,%.2f,%s\n",
                        p.getId(), 
                        p.getName(), 
                        p.getSalary(), 
                        p.getAbsentDays(), 
                        finalSalary,
                        p.getBonus(), 
                        p.getGoalsScored(), 
                        totalBonus, 
                        totalPayout,
                        p.getStatus()
                    ));
                }
            }
            System.out.println("-> Đã xuất báo cáo lương thành công ra file: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi xuất file báo cáo lương: " + e.getMessage());
        }
    }

    // 2. Xuất biên lai lương (Payslip) cho một cầu thủ cụ thể ra file TXT
    public void exportPlayerPayslipToTXT(Player p, SalaryCalculator calculator, String directoryPath) {
        if (p == null) {
            System.out.println("Không thể xuất biên lai: Cầu thủ không tồn tại.");
            return;
        }
        
        // Tên file dựa trên ID của cầu thủ
        String fileName = directoryPath + "/Payslip_" + p.getId() + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=========================================\n");
            writer.write("           MONTHLY PAYSLIP               \n");
            writer.write("=========================================\n");
            writer.write("Player ID   : " + p.getId() + "\n");
            writer.write("Player Name : " + p.getName() + "\n");
            writer.write("Status      : " + p.getStatus() + "\n");
            writer.write("-----------------------------------------\n");
            writer.write("EARNINGS & DEDUCTIONS:\n");
            writer.write(String.format("- Base Salary       : $%.2f\n", p.getSalary()));
            writer.write(String.format("- Absent Penalty    : -$%.2f (%d days)\n", (p.getAbsentDays() * 500.0), p.getAbsentDays()));
            writer.write(String.format("=> FINAL SALARY     : $%.2f\n", calculator.computeFinalSalary(p)));
            writer.write("-----------------------------------------\n");
            writer.write("BONUSES:\n");
            writer.write(String.format("- Base Bonus        : $%.2f\n", p.getBonus()));
            writer.write(String.format("- Goal Bonus        : +$%.2f (%d goals)\n", (p.getGoalsScored() * 100.0), p.getGoalsScored()));
            writer.write(String.format("=> TOTAL BONUS      : $%.2f\n", calculator.computeTotalBonus(p)));
            writer.write("=========================================\n");
            writer.write(String.format("TOTAL PAYOUT        : $%.2f\n", calculator.computeTotalPayout(p)));
            writer.write("=========================================\n");
            
            System.out.println("-> Đã xuất biên lai lương cho " + p.getName() + " ra file: " + fileName);
        } catch (IOException e) {
            System.err.println("Lỗi khi xuất biên lai: " + e.getMessage());
        }
    }
}
