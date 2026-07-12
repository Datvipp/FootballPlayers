package SERVICES;
import MODEL.Match;
import MODEL.FriendlyMatch;
import MODEL.LeagueMatch;
import MODEL.CupMatch;
import MODEL.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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

   // thu muc co dinh de luu file, nam canh noi chay chuong trinh
   private static final String DATA_FOLDER = "data";

   // ghep ten file voi thu muc data/, tu tao thu muc neu chua co
   private String resolveDataPath(String fileName){
    File folder = new File(DATA_FOLDER);
    if(!folder.exists()){
        folder.mkdirs();
    }
    return DATA_FOLDER + File.separator + fileName;
   }

   //ghi toan bo match list ra file text trong thu muc data/
   public void saveToFile(String fileName){
    String path = resolveDataPath(fileName);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
        for(Match match : arr){
            bw.write(match.toFileLine());
            bw.newLine();
        }
        System.out.println("Saved " + arr.size() + " match(es) to " + path);
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }
   }

   //doc match list tu file text trong thu muc data/, bo qua dong loi thay vi crash
   public void loadFromFile(String fileName){
    String path = resolveDataPath(fileName);
    arr.clear();
    int lineNumber = 0;
    int skipped = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
            lineNumber++;
            if(line.trim().isEmpty()) continue;

            try {
                Match match = parseMatchLine(line);
                if(isDuplicateID(match.getMatchID())){
                    System.out.println("Line " + lineNumber + ": duplicate match ID " + match.getMatchID() + ", skipped.");
                    skipped++;
                    continue;
                }
                arr.add(match);
            } catch (Exception e) {
                // NumberFormatException, DateTimeParseException, ArrayIndexOutOfBoundsException... deu roi vao day
                System.out.println("Line " + lineNumber + ": invalid data (" + e.getMessage() + "), skipped.");
                skipped++;
            }
        }
        System.out.println("Loaded " + arr.size() + " match(es) from " + path
                + (skipped > 0 ? " (" + skipped + " line(s) skipped)" : ""));
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
   }

   //parse 1 dong text thanh doi tuong Match cu the (da hinh)
   //format: Type|matchID|date(yyyy-MM-dd)|opponentTeam|stadium|extraField
   private Match parseMatchLine(String line){
    String[] p = line.split("\\|", -1);
    if(p.length < 5){
        throw new IllegalArgumentException("not enough fields");
    }

    String type = p[0];
    int id = Integer.parseInt(p[1]);
    LocalDate date = LocalDate.parse(p[2]);
    String opponentTeam = p[3];
    String stadium = p[4];

    Match match;
    switch(type){
        case "Friendly":
            FriendlyMatch fm = new FriendlyMatch();
            if(p.length > 5) fm.setFriendlyReason(p[5]);
            match = fm;
            break;
        case "League":
            LeagueMatch lm = new LeagueMatch();
            if(p.length > 5) lm.setLeagueName(p[5]);
            match = lm;
            break;
        case "Cup":
            CupMatch cm = new CupMatch();
            if(p.length > 5) cm.setCupName(p[5]);
            match = cm;
            break;
        default:
            throw new IllegalArgumentException("unknown match type: " + type);
    }

    match.setMatchType(type);
    match.setMatchID(id);
    match.setDate(date);
    match.setOpponentTeam(opponentTeam);
    match.setStadium(stadium);
    return match;
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
