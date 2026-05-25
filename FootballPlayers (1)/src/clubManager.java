
import java.util.Scanner;

public class PlayersManager {
    Players [] arr=new Players[100];
    int count=0;
    
    void addPlayer(){
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
    
    void viewAllPlayers(){
        for(int i=0; i<count; i++){
            arr[i].displayPlayer();
            System.out.println("\n");
        }
    }

    void updateInfo() {
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

     void deactivatePlayer(int id) {

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
}
}
