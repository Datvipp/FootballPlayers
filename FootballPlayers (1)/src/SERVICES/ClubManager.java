package SERVICES;
import MODEL.Players;

import java.util.Scanner;

public class ClubManager {
    Players [] arr=new Players[100];
    int count=0;
    
    public void addPlayer(){
        boolean cont= false;
        Scanner sc=new Scanner(System.in);
        do{
            arr[count]=new Players();
            arr[count].inputPlayers();
            count++;
            System.out.println("Add more (true|false)?: ");
           
            cont=sc.nextBoolean();
            
        }while (cont==true && count<100);
    }
    
    public void viewAllPlayers(){
        for(int i=0; i<count; i++){
            arr[i].displayPlayer();
            System.out.println("\n");
        }
    }

    public void updateInfo() {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter Player id to update");
        int idUpdate = sc.nextInt();

        boolean found = false;

        for (int i=0; i <count; i++){
            if(arr[i].getId() == idUpdate){
                System.out.println("===Enter player new information===");
                arr[i].inputPlayers();

                found = true;
                System.out.println("Update successful!");
                break;
            }
        }
        if(!found) System.out.println("Player not found");
    } 

     public void deactivatePlayer(int id) {
        boolean found = false;

        for(int i = 0; i < count; i++) {
            if(arr[i].getId() == id) {
                arr[i].setStatus("Inactive");
                found = true;
                System.out.println("Player deactivated!");
                break;
            }
        }

        if(found == false) {
            System.out.println("Player not found!");
        }
    }

    public void searchPlayer(int id){
        boolean found = false;

        for (int i=0; i<count; i++){
            
            if( arr[i].getId() == id){
                arr[i].displayPlayer();

                found = true;
                break;
            }
        }
        if (found == false){
           System.out.println("Player not found!");
        }
    }

    public MODEL.Players getPlayerById(int id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].getId() == id) {
                return arr[i]; // Trả về nguyên object Player nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không có cầu thủ này
    } // Q thêm vào đó

    // Hàm mới: Dùng để cập nhật riêng thành tích thi đấu hàng tháng (Q thêm vào đó)
    public void updatePlayerStats() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Player ID to update stats: ");
        int searchId = sc.nextInt();

        MODEL.Players p = getPlayerById(searchId);

        if (p != null) {
            System.out.println("Updating stats for player: " + p.getName());
            
            System.out.print("Input goals scored this month: ");
            p.setGoalsScored(sc.nextInt());
            
            System.out.print("Input absent days this month: ");
            p.setAbsentDays(sc.nextInt());
            
            System.out.println("-> Stats updated successfully!");
        } else {
            System.out.println("Player not found!");
        }
    }
}

