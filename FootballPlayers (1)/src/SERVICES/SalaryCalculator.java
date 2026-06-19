package SERVICES;
import MODEL.Player;

public class SalaryCalculator {
    
    private final double PENALTY_PER_ABSENT_DAY = 500.0; // Phạt 500$ mỗi ngày nghỉ
    private final double BONUS_PER_GOAL = 100.0;         // Thưởng 100$ mỗi bàn thắng

    // 1. Công thức tính Lương tháng (Lương cứng - Tiền phạt nghỉ làm)
    public double computeFinalSalary(Player p) {
        // Rút trực tiếp lương và số ngày nghỉ từ hồ sơ ra để tính
        double finalSalary = p.getSalary() - (p.getAbsentDays() * PENALTY_PER_ABSENT_DAY);
        
        // Đảm bảo lương không bị âm nếu nghỉ quá nhiều
        if (finalSalary < 0) {
            return 0.0; 
        }
        return finalSalary;
    }

    // 2. Công thức tính Tiền thưởng (Thưởng cứng + Tiền ghi bàn)
    public double computeTotalBonus(Player p) {
        // Rút trực tiếp tiền thưởng cứng và số bàn thắng từ hồ sơ ra để tính
        double totalBonus = p.getBonus() + (p.getGoalsScored() * BONUS_PER_GOAL);
        return totalBonus;
    }
    
    // 3. Công thức tính Lương tháng + Tiền thưởng (Lương cứng - Tiền phạt nghỉ làm + Thưởng cứng + Tiền ghi bàn)
    public double computeTotalPayout(Player p) {
        double finalSalary = computeFinalSalary(p);
        double totalBonus = computeTotalBonus(p);

        return finalSalary + totalBonus;
    }
}