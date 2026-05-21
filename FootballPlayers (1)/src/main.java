
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manhd
 */
public class main {
    public static void main(String[] args) {
        
        Players pm=new Players();
        TrainingSession ts = new TrainingSession();
        Match ms = new Match();
        Scanner sc=new Scanner(System.in);
     
        pm.inputPlayers();
        pm.displayPlayer();
        ts.createTrainingSession();
        ts.displayTrainingSession();
        ms.nhapMatch();
        ms.xuatMatch();
        Match a = new Match();
        Match b = new Match();
        a.nhapMatch();
        b.nhapMatch();
        a.xuatMatch();
        b.xuatMatch();
    }
}
