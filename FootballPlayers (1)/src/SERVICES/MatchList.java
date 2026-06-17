package SERVICES;
import MODEL.Match;
import MODEL.FriendlyMatch;
import MODEL.LeagueMatch;
import MODEL.CupMatch;
import java.util.Scanner;

public class MatchList {
    private Match[] arr;
    private int count;
    private Scanner sc;

    public MatchList() {
        this(new Scanner(System.in));
    }

    public MatchList(Scanner sc) {
        this.arr = new Match[100];
        this.count = 0;
        this.sc = sc;
    }
    //add match
   public void addMatch(){
    if(count>=100){
        System.out.println("Match list is full");
        return;
    }
    System.out.println("Select match type:");
    System.out.println("1. Friendly Match");
    System.out.println("2. League Match");
    System.out.println("3. Cup Match");
    System.out.print("Enter your choice: ");
    int choice=sc.nextInt();
    sc.nextLine();

    Match match = null;
    switch(choice){
        case 1:
            match = new FriendlyMatch();
            match.setMatchType("Friendly");
            break;
        case 2:
            match = new LeagueMatch();
            match.setMatchType("League");
            break;
        case 3:
            match = new CupMatch();
            match.setMatchType("Cup");
            break;
        default:
            System.out.println("Invalid choice");
            return;
    }

    match.inputMatch(sc);
    arr[count] = match;
    count++;
    System.out.println("Match added successfully");
   }
   //display match list
   public void displayMatchList(){
    if(count==0){
        System.out.println("Match list is empty");
        return;
    }
    for(int i=0;i<count;i++){
        arr[i].outputMatch();
    }
   }
   //search match by ID
   public void searchMatchByID(int id){
    if(count==0){
        System.out.println("Match list is empty");
        return;
    }
    boolean found=false;
    for(int i=0;i<count;i++){
        if(arr[i].getMatchID()==id){
            arr[i].outputMatch();
            found=true;
        }
    }
    if(!found){
            System.out.println("Match not found");
        }
    }
   //update match
   public void updateMatch(int id){
    if(count==0){
        System.out.println("Match list is empty");
        return;
    }
        for (int i = 0; i < count; i++) {
            if (arr[i].getMatchID() == id) {
                System.out.print("Input new date: ");
                arr[i].setDate(sc.nextLine());
                System.out.print("Input new opponent team: ");
                arr[i].setOpponentTeam(sc.nextLine());

                // Đa hình — update thuộc tính riêng theo loại
                if (arr[i] instanceof FriendlyMatch) {
                    FriendlyMatch fm = (FriendlyMatch) arr[i];
                    System.out.print("Input new friendly reason: ");
                    fm.setFriendlyReason(sc.nextLine());

                } else if (arr[i] instanceof LeagueMatch) {
                    LeagueMatch lm = (LeagueMatch) arr[i];
                    System.out.print("Input new league name: ");
                    lm.setLeagueName(sc.nextLine());

                } else if (arr[i] instanceof CupMatch) {
                    CupMatch cm = (CupMatch) arr[i];
                    System.out.print("Input new cup name: ");
                    cm.setCupName(sc.nextLine());
                }

                System.out.println("Match updated successfully!");
                return;
            }
        }
        System.out.println("Match with ID " + id + " not found!");
    }
   
   //delete match
   public void deleteMatch(int id){
    if(count==0){
        System.out.println("Match list is empty");
        return;
    }
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

}
