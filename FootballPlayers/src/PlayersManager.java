
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manhd
 */
public class PlayersManager {
    Players [] arr=new Players[100];
    int count=0;
    
    
    void viewAllPlayers(){
        for(int i=0; i<count; i++){
            arr[i].displayPlayer();
            System.out.println("\n");
        }
    }
    
}
