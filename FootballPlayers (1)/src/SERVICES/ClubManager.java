package SERVICES;

import MODEL.Player;
import MODEL.RegularPlayer;
import MODEL.StarPlayer;
import java.util.Scanner;

public class ClubManager implements PlayerProvider {
    Player[] arr = new Player[100];
    int count = 0;
    private Scanner sc;
    private PlayerFileManager fileManager = new PlayerFileManager();

    public void saveToFile(String fileName) {
        fileManager.saveToFile(fileName, arr, count);
    }

    public void loadFromFile(String fileName) {
        count = fileManager.loadFromFile(fileName, arr);
    }

    public ClubManager() {
        this.sc = new Scanner(System.in);
    }

    public ClubManager(Scanner sc) {
        this.sc = (sc != null) ? sc : new Scanner(System.in);
    }

    private boolean isDuplicateId(String id, String excludeId) {
        for (int i = 0; i < count; i++) {
            if (excludeId != null && arr[i].getId().equals(excludeId)) continue;
            if (arr[i].getId().equalsIgnoreCase(id)) return true;
        }
        return false;
}

    private boolean isDuplicateShirtNumber(int number, String excludeId) {
        for (int i = 0; i < count; i++) {
            if (excludeId != null && arr[i].getId().equals(excludeId)) continue;
            if (arr[i].getStatus().equalsIgnoreCase("Active") && arr[i].getNumber() == number) return true;
        }
        return false;
}

    public void addPlayer() {
        boolean cont = false;
        do {
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

            boolean validEntry;
                do {
                    validEntry = true;
                    if (choice == 1) {
                         arr[count] = new RegularPlayer();
                         arr[count].setType("Regular player");
                    } else {
                        arr[count] = new StarPlayer();
                        arr[count].setType("Star player");
                            }

                             arr[count].inputPlayers(this.sc);

                    if (isDuplicateId(arr[count].getId(), null)) {
                        System.out.println("Player ID already exists! Please re-enter this player.");
                        validEntry = false;
                    } else if (arr[count].getStatus().equalsIgnoreCase("Active")
                            && isDuplicateShirtNumber(arr[count].getNumber(), null)) {
                                System.out.println("Shirt number already used by another active player! Please re-enter this player.");
                                validEntry = false;
                    }
                } while (!validEntry);

                count++;
            System.out.println("Add more (true|false)?: ");

            cont = readBooleanInput();

        } while (cont == true && count < 100);
    }

    private boolean readBooleanInput() {
        while (true) {
            String input = this.sc.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("1") || input.equals("yes")) {
                return true;
            } else if (input.equals("false") || input.equals("0") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'true' or 'false' (or 'yes'/'no'):");
            }
        }
    }

    public void viewAllPlayers() {
        if (count == 0) {
            System.out.println("Player list is empty");
            return;
        }
        for (int i = 0; i < count; i++) {
            arr[i].displayPlayer();
            System.out.println();
        }
    }

    public void updateInfo() {
        System.out.println("Enter Player id to update");
        String idUpdate = this.sc.nextLine();

        Player p = getPlayerById(idUpdate);
        if (p == null) {
            System.out.println("Player not found");
            return;
        }

        p.displayPlayer();

        String newPosition;
        do {
            System.out.println("Enter new position (Goalkeeper/Defender/Midfielder/Forward): ");
            newPosition = this.sc.nextLine().trim();
            if (!p.isValidPosition(newPosition)) System.out.println("Invalid position!");
        } while (!p.isValidPosition(newPosition));

        int newNumber;
        do {
            System.out.println("Enter new shirt number (1-99): ");
            while (!sc.hasNextInt()) { System.out.println("Invalid input!"); sc.next(); }
            newNumber = sc.nextInt();
            if (newNumber < 1 || newNumber > 99) System.out.println("Shirt number must be between 1 and 99!");
        } while (newNumber < 1 || newNumber > 99);
        sc.nextLine();

        double newSalary;
        do {
            System.out.println("Enter new base salary: ");
            while (!sc.hasNextDouble()) { System.out.println("Invalid input!"); sc.next(); }
            newSalary = sc.nextDouble();
            if (newSalary <= 0) System.out.println("Base salary must be greater than 0!");
        } while (newSalary <= 0);
        sc.nextLine();

        String newStatus;
        do {
            System.out.println("Enter new status (Active/Inactive): ");
            newStatus = this.sc.nextLine().trim();
            if (!p.isValidStatus(newStatus)) System.out.println("Invalid status!");
        } while (!p.isValidStatus(newStatus));

        if (newStatus.equalsIgnoreCase("Active") && isDuplicateShirtNumber(newNumber, p.getId())) {
            System.out.println("Update failed: shirt number already used by another active player!");
            return;
        }

        p.setPosition(newPosition);
        p.setNumber(newNumber);
        p.setSalary(newSalary);
        p.setStatus(newStatus);
        System.out.println("The player updated successfully.");
    }

    public void deactivatePlayer(String id) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(id)) {
                arr[i].setStatus("Inactive");
                found = true;
                System.out.println("Player deactivated!");
                break;
            }
        }

        if (found == false) {
            System.out.println("Player not found!");
        }
    }

    public void viewPlayerDetails(String id) {
    Player p = getPlayerById(id);
    if (p != null) p.displayPlayer();
    else System.out.println("Player not found!");
}

    public void searchPlayers(int type, String keyword) {
        if (count == 0) { System.out.println("Player list is empty"); return; }
        String kw = (keyword == null ? "" : keyword.trim().toLowerCase());
        boolean found = false;
        for (int i = 0; i < count; i++) {
            String field;
            switch (type) {
                case 1: field = arr[i].getName(); break;
                case 2: field = arr[i].getPosition(); break;
                case 3: field = arr[i].getNational(); break;
                case 4: field = arr[i].getStatus(); break;
                default: System.out.println("Invalid search type!"); return;
            }
            if (field != null && field.toLowerCase().contains(kw)) {
                arr[i].displayPlayer();
                found = true;
            }
        }
        if (!found) System.out.println("No player found matching your search.");
    }

    public Player getPlayerById(String id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(id)) {
                return arr[i];
            }
        }
        return null;
    }

   public void updatePlayerStats() {
    System.out.print("Enter Player ID to update stats: ");
    String searchId = this.sc.nextLine();

    Player p = getPlayerById(searchId);

    if (p != null) {
        System.out.println("Updating stats for player: " + p.getName());

        int goals;
        do {
            System.out.print("Input goals scored this month: ");
            while (!sc.hasNextInt()) { System.out.println("Invalid input! Please enter a number."); sc.next(); }
            goals = sc.nextInt();
            if (goals < 0) System.out.println("Goals cannot be negative!");
        } while (goals < 0);
        p.setGoalsScored(goals);

        int absentDays;
        do {
            System.out.print("Input absent days this month (0-31): ");
            while (!sc.hasNextInt()) { System.out.println("Invalid input! Please enter a number."); sc.next(); }
            absentDays = sc.nextInt();
            if (absentDays < 0 || absentDays > 31) System.out.println("Absent days must be between 0 and 31!");
        } while (absentDays < 0 || absentDays > 31);
        p.setAbsentDays(absentDays);

        this.sc.nextLine();
        System.out.println("-> Stats updated successfully!");
    } else {
        System.out.println("Player not found!");
    }
}

    public int getCount() {
        return this.count;
    }

    public Player getPlayerByIndex(int index) {
        return this.arr[index];
    }
}
