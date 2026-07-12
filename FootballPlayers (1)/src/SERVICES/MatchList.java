package SERVICES;
import MODEL.Match;
import MODEL.FriendlyMatch;
import MODEL.LeagueMatch;
import MODEL.CupMatch;
import MODEL.Player;
import IO.MatchIO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;

public class MatchList {
    private List<Match> arr;
    private Scanner sc;
    private PlayerProvider playerProvider;

    public MatchList() {
        this(new Scanner(System.in));
    }

    public MatchList(Scanner sc) {
        this.arr = new ArrayList<>();
        this.sc = sc;
    }

    public void setPlayerProvider(PlayerProvider playerProvider) {
        this.playerProvider = playerProvider;
    }
    //kiem tra trung ID
   private boolean isDuplicateID(int id){
    for(Match match : arr){
        if(match.getMatchID() == id){
            return true;
        }
    }
    return false;
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

    if(isDuplicateID(match.getMatchID())){
        System.out.println("Match ID " + match.getMatchID() + " already exists! Match not added.");
        return;
    }

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
                match.setDate(Match.readDateInput(sc));
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

   // ================= FILE I/O =================

   public void saveToFile(String fileName){
    try {
        MatchIO.saveMatches(arr, fileName);
        System.out.println("Saved " + arr.size() + " match(es) to " + MatchIO.resolvePath(fileName));
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }
   }

   public void loadFromFile(String fileName){
    arr.clear();
    int skipped = 0;

    try {
        List<Match> loadedMatches = MatchIO.loadMatches(fileName);
        for (Match match : loadedMatches) {
            if (isDuplicateID(match.getMatchID())) {
                skipped++;
                continue;
            }
            arr.add(match);
        }
        System.out.println("Loaded " + arr.size() + " match(es) from " + MatchIO.resolvePath(fileName)
                + (skipped > 0 ? " (" + skipped + " line(s) skipped)" : ""));
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
   }

   // ================= ALGORITHM OPTIMIZATION =================

   //sap xep danh sach match theo matchID tang dan
   public void sortMatchesByID(){
    Collections.sort(arr, Comparator.comparingInt(Match::getMatchID));
    System.out.println("Sorted " + arr.size() + " match(es) by ID.");
   }

   //tim kiem nhi phan theo matchID - yeu cau danh sach da duoc sap xep truoc (goi sortMatchesByID())
   //do phuc tap O(log n) thay vi O(n) cua searchMatchByID
   public void binarySearchByID(int id){
    if(arr.isEmpty()){
        System.out.println("Match list is empty");
        return;
    }

    int low = 0, high = arr.size() - 1;
    while(low <= high){
        int mid = low + (high - low) / 2;
        int midID = arr.get(mid).getMatchID();

        if(midID == id){
            arr.get(mid).outputMatch();
            return;
        } else if(midID < id){
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    System.out.println("Match not found");
   }

   // ================= LIEN KET VOI PLAYER =================

   //hien thi tong so ban thang cua 1 Player, lay du lieu qua PlayerProvider (giong pattern cua TrainingSession)
   public void viewPlayerGoals(String playerId){
    if(playerProvider == null){
        System.out.println("Player Management is not connected. Cannot look up player goals.");
        return;
    }

    Player player = playerProvider.getPlayerById(playerId);
    if(player == null){
        System.out.println("Player not found!");
        return;
    }

    System.out.println("Player: " + player.getName() + " (ID: " + player.getId() + ")");
    System.out.println("Goals Scored: " + player.getGoalsScored());
   }

}
