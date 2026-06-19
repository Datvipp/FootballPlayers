package SERVICES;
import MODEL.Player; 
import MODEL.RegularPlayer;
import MODEL.StarPlayer;
import java.util.Scanner;
public class ClubManager {
    Player [] arr=new Player[100];
    int count=0;
    
    public void addPlayer(){
        boolean cont= false;
        Scanner sc=new Scanner(System.in);
        do{
            int choice;
            do {
                System.out.println("Player Type: 1.Regular Player  2.Star Player");
                System.out.print("Choose Player Type: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter 1 or 2.");
                    sc.next();
                }
                choice = sc.nextInt();

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice! Please enter 1 or 2.");
                }
            } while (choice != 1 && choice != 2);
            sc.nextLine();

            if (choice == 1) {
                arr[count] = new RegularPlayer();
                arr[count].setType("Regular player");
            } else {
                arr[count] = new StarPlayer();
                arr[count].setType("Star player");
            }

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
<<<<<<< HEAD
        String idUpdate = sc.nextLine(); // [ADD] đổi nextInt() -> nextLine() vì id giờ là String
=======
        String idUpdate = sc.nextLine();

>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
        boolean found = false;
        for (int i=0; i <count; i++){
<<<<<<< HEAD
            if(arr[i].getId().equals(idUpdate)){ // [ADD] đổi "==" -> ".equals()" vì id là String
=======
            if(arr[i].getId().equals(idUpdate)){
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
                System.out.println("===Enter player new information===");
                arr[i].inputPlayers();
                found = true;
                System.out.println("Update successful!");
                break;
            }
        }
        if(!found) System.out.println("Player not found");
    } 
<<<<<<< HEAD
     public void deactivatePlayer(String id) { // [ADD] đổi tham số int -> String
=======

     public void deactivatePlayer(String id) {
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
        boolean found = false;
        for(int i = 0; i < count; i++) {
<<<<<<< HEAD
            if(arr[i].getId().equals(id)) { // [ADD] đổi "==" -> ".equals()"
=======
            if(arr[i].getId().equals(id)) { 
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
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
<<<<<<< HEAD
    public void searchPlayer(String id){ // [ADD] đổi tham số int -> String
=======

    public void searchPlayer(String id){
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
        boolean found = false;
        for (int i=0; i<count; i++){
            
<<<<<<< HEAD
            if( arr[i].getId().equals(id)){ // [ADD] đổi "==" -> ".equals()"
=======
            if( arr[i].getId().equals(id)){
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
                arr[i].displayPlayer();
                found = true;
                break;
            }
        }
        if (found == false){
           System.out.println("Player not found!");
        }
    }
<<<<<<< HEAD
    public MODEL.Player getPlayerById(String id) { // [ADD] đổi MODEL.Players -> MODEL.Player, int -> String
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(id)) { // [ADD] đổi "==" -> ".equals()"
=======

    public Player getPlayerById(String id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(id)) {
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
                return arr[i]; // Trả về nguyên object Player nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không có cầu thủ này
    } // Q thêm vào đó
    // Hàm mới: Dùng để cập nhật riêng thành tích thi đấu hàng tháng (Q thêm vào đó)
    public void updatePlayerStats() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Player ID to update stats: ");
<<<<<<< HEAD
        String searchId = sc.nextLine(); // [ADD] đổi nextInt() -> nextLine()
        MODEL.Player p = getPlayerById(searchId); // [ADD] đổi MODEL.Players -> MODEL.Player
=======
        String searchId = sc.nextLine();

        Player p = getPlayerById(searchId);

>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
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
    // Lấy tổng số cầu thủ hiện có
    public int getCount() {
        return this.count;
    }
    // Lấy hồ sơ cầu thủ theo số thứ tự (để chạy vòng lặp)
<<<<<<< HEAD
    public MODEL.Player getPlayerByIndex(int index) { // [ADD] đổi MODEL.Players -> MODEL.Player
        return this.arr[index];
=======
    public Player getPlayerByIndex(int index) {
        return this.arr[index]; 
>>>>>>> e2d362e (Refactor Player id to String; rename Players->Player; fix imports and comparisons; update Salary model; add TestRunner; UI fixes)
    }
}
