
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
        TrainingSession ts=new TrainingSession();
        Scanner sc=new Scanner(System.in);
     
        pm.inputPlayers();
        pm.displayPlayer();
        ts.createTrainingSession();
        ts.displayTrainingSession();
        MatchList ds = new MatchList();
        int choice;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Them tran dau");
            System.out.println("2. Xem lich su tran dau");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            if(choice == 1) {
                ds.themMatch();
            }
            else if(choice == 2) {
                ds.xemMatchHistory();
            }
            else if(choice == 0) {
                System.out.println("Thoat chuong trinh");
            }
            else {
                System.out.println("Lua chon khong hop le");
            }
        } while(choice != 0);

    }
    }
}
