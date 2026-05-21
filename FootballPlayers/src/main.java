
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
        
        Scanner sc=new Scanner(System.in);
        Players a=new Players();
        
        
        int choice;
        
        do{
           System.out.println("1.Add Player: ");
           System.out.println("2.View All Players: ");
           System.out.println("3.Exit: ");
           
           System.out.println("Choice: ");
           choice=sc.nextInt();
           
           switch(choice){
               
               case 1:
                   a.inputPlayers();break;
               case 2:
                   a.displayPlayer(); break;
               case 3:
                   System.out.println("Bye Bye have a nice day!!... ");
                   break;
           }
        }while(choice != 3);
    }
}
