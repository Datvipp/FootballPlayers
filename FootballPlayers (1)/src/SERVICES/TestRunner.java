package SERVICES;

import MODEL.Player;
import MODEL.RegularPlayer;
import MODEL.Salary;

public class TestRunner {
    public static void main(String[] args) {
        ClubManager cm = new ClubManager();

        RegularPlayer rp = new RegularPlayer();
        rp.setId("P01");
        rp.setName("Nguyen A");
        rp.setAge(25);
        rp.setNational("Viet");
        rp.setPosition("Forward");
        rp.setNumber(9);
        rp.setType("Regular player");
        rp.setSalary(1000.0);
        rp.setStatus("Active");

        // add directly
        cm.arr[cm.count] = rp;
        cm.count++;

        System.out.println("-- After adding player --");
        cm.viewAllPlayers();

        // update stats directly
        Player p = cm.getPlayerById("P01");
        if (p != null) {
            p.setGoalsScored(2);
            p.setAbsentDays(1);
        }

        SalaryManager sm = new SalaryManager(cm);
        System.out.println("\n-- Salary outputs --");
        sm.calculateMonthlySalary("P01");
        sm.calculateBonus("P01");
        sm.displayTotalIncome("P01");

        System.out.println("\n-- Salary model display --");
        Salary s = new Salary("P01", 900.0, 100.0, "Active");
        s.xuatSalary();

        System.out.println("\n-- Contract validation --");
        sm.validateContractRules();
    }
}
