package SERVICES;
import MODEL.Match;
import MODEL.FriendlyMatch;
import MODEL.LeagueMatch;
import MODEL.CupMatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchList {
    private List<Match> arr;
    private Scanner sc;

    public MatchList() {
        this(new Scanner(System.in));
    }

    public MatchList(Scanner sc) {
        this.arr = new ArrayList<>();
        this.sc = sc;
    }
    //add match
   public void addMatch(){
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
    arr.add(match);
    System.out.println("Match added successfully");
   }
   //display match list
   public void displayMatchList(){
    if(arr.isEmpty()){
        System.out.println("Match list is empty");
        return;
    }
    for(Match match : arr){
        match.outputMatch();
    }
   }
   //search match by ID
   public void searchMatchByID(int id){
    if(arr.isEmpty()){
        System.out.println("Match list is empty");
        return;
    }
    boolean found=false;
    for(Match match : arr){
        if(match.getMatchID()==id){
            match.outputMatch();
            found=true;
        }
    }
    if(!found){
            System.out.println("Match not found");
        }
    }
   //update match
   public void updateMatch(int id){
    if(arr.isEmpty()){
        System.out.println("Match list is empty");
        return;
    }
        for (Match match : arr) {
            if (match.getMatchID() == id) {
                System.out.print("Input new date: ");
                match.setDate(sc.nextLine());
                System.out.print("Input new opponent team: ");
                match.setOpponentTeam(sc.nextLine());

                // Đa hình — update thuộc tính riêng theo loại
                if (match instanceof FriendlyMatch) {
                    FriendlyMatch fm = (FriendlyMatch) match;
                    System.out.print("Input new friendly reason: ");
                    fm.setFriendlyReason(sc.nextLine());

                } else if (match instanceof LeagueMatch) {
                    LeagueMatch lm = (LeagueMatch) match;
                    System.out.print("Input new league name: ");
                    lm.setLeagueName(sc.nextLine());

                } else if (match instanceof CupMatch) {
                    CupMatch cm = (CupMatch) match;
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
    if(arr.isEmpty()){
        System.out.println("Match list is empty");
        return;
    }
    for(int i=0;i<arr.size();i++){
        if(arr.get(i).getMatchID()==id){
            arr.remove(i);
            System.out.println("Match deleted successfully");
            return;
        }   
    }
    System.out.println("Match not found");
   }

}
