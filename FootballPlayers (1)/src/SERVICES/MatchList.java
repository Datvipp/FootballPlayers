package SERVICES;
import MODEL.Match;
import java.util.Scanner;

public class MatchList {
    private Match[] arr;
    private int count;
    //add match
   public void addMatch(Scanner sc){
    if (count >= arr.length) {
        System.out.println("Match list is full");
        return;
    }
    arr[count] = new Match();
    arr[count].inputMatch(sc);
    count++;
   }

   public void addMatch(){
    addMatch(new Scanner(System.in));
   }
   //display match list
   public void displayMatchList(){
    if (count == 0) {
        System.out.println("No matches available");
        return;
    }
    for(int i=0;i<count;i++){
        arr[i].outputMatch();
    }
   }
   //search match by ID
   public void searchMatchByID(int id){
    boolean found=false;
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            arr[i].outputMatch();
            found=true;
            break;
        }
    }
    if(!found){
            System.out.println("Match not found");
        }
    }
   //update match
   public void updateMatch(int id, Scanner sc){
    if (sc == null) {
        sc = new Scanner(System.in);
    }
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            System.out.print("Input new date: ");
            String newdate=sc.nextLine();
            arr[i].setDate(newdate);
            System.out.print("Input new opponent team: ");
            String newopponent=sc.nextLine();
            arr[i].setOpponentTeam(newopponent);
            System.out.print("Input new match type: ");
            String newmatchtype=sc.nextLine();
            arr[i].setMatchType(newmatchtype);
            System.out.println("Match updated successfully");
            return;
        }
    }
    System.out.println("Match not found");
   }

   public void updateMatch(int id){
    updateMatch(id, new Scanner(System.in));
   }
   //delete match
   public void deleteMatch(int id){
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            for(int j=i;j<count-1;j++){
                arr[j]=arr[j+1];
            }
            arr[count-1] = null;
            count--;
            System.out.println("Match deleted successfully");
            return;
        }   
    }
    System.out.println("Match not found");
   }
    //constructor
        public MatchList() {
            this.arr = new Match[100];
            this.count = 0;
        }
    //getters
        public Match[] getArr() {
            return this.arr;
        }
        
        public int getCount() {
            return this.count;
        }
    //setters
        public void setArr(Match[] arr) {
            this.arr = arr;
        }
        public void setCount(int count) {
            this.count = count;
        }
}
