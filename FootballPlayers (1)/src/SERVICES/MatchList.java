package SERVICES;
import MODEL.Match;
import java.util.Scanner;

public class MatchList {
    private Match[] arr;
    private int count;
    //add match
   public void addMatch(){
    arr[count]=new Match();
    arr[count].nhapMatch();
    count++;
   }
   //display match list
   public void dispalyMatchList(){
    for(int i=0;i<count;i++){
        arr[i].xuatMatch();
    }
   }
   //search match by ID
   public void searchMatchbyID(int id){
    boolean found=false;
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            arr[i].xuatMatch();
            found=true;
        }
    }
    if(!found){
            System.out.println("Match not found");
        }
    }
   //update match
   public void updateMatch(int id){
    Scanner sc=new Scanner(System.in);
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            System.out.print("Input new date: ");
            String newdate=sc.nextLine();
            arr[i].setDate(newdate);
            System.out.print("Input new opponet team: ");
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
   //delete match
   public void deleteMatch(int id){
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            for(int j=i;j<count-1;j++){
                arr[j]=arr[j+1];
            }
            count--;
            System.out.println("Match deleted successfully");
            return;
        }   
    }
    System.out.println("Match not found");
   }
    //constructor
        public MatchList(int size) {
            this.arr = new Match[size];
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
