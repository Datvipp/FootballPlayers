
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguye
 */
public class Match {
   int matchID;
    String date;
    String opponentTeam;
    String matchType;

    void nhapMatch() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap Match ID: ");
        matchID = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap ngay thi dau: ");
        date = sc.nextLine();
        System.out.print("Nhap doi thu: ");
        opponentTeam = sc.nextLine();
        System.out.print("Nhap loai tran dau: ");
        matchType = sc.nextLine();
    }
    void xuatMatch(){
        System.out.println("Match ID: " + matchID);
        System.out.println("Date: " + date);
        System.out.println("Opponent Team: " + opponentTeam);
        System.out.println("Match Type: " + matchType);
        System.out.println("---------------------");
   }
}
    

