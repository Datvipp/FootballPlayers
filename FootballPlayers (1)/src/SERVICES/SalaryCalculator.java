package SERVICES;
import MODEL.Salary;

public class SalaryCalculator {
    
    // Đặt các mức giá cố định (bạn có thể thay đổi số tiền tùy ý)
    private final double PENALTY_PER_ABSENT_DAY = 500.0; // Phạt 500$ mỗi ngày nghỉ
    private final double BONUS_PER_GOAL = 100.0;         // Thưởng 100$ mỗi bàn thắng

    // 1. Công thức tính Lương tháng (Lương cứng - Tiền phạt nghỉ làm)
    public double computeFinalSalary(Salary player, int absentDays) {
        double finalSalary = player.getBaseSalary() - (absentDays * PENALTY_PER_ABSENT_DAY);
        
        // Đảm bảo lương không bị âm nếu nghỉ quá nhiều
        if (finalSalary < 0) {
            return 0.0; 
        }
        return finalSalary;
    }

    // 2. Công thức tính Tiền thưởng (Thưởng cứng + Tiền ghi bàn)
    public double computeTotalBonus(Salary player, int goalsScored) {
        double totalBonus = player.getBonus() + (goalsScored * BONUS_PER_GOAL);
        return totalBonus;
    }
}