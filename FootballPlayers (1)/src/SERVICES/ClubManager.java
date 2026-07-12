package SERVICES;

import MODEL.Player;
import MODEL.RegularPlayer;
import MODEL.StarPlayer;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClubManager implements PlayerProvider {
    Player[] arr = new Player[100];
    int count = 0;
    private Scanner sc;

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

            System.out.print("Input goals scored this month: ");
            p.setGoalsScored(this.sc.nextInt());
            this.sc.nextLine();

            System.out.print("Input absent days this month: ");
            p.setAbsentDays(this.sc.nextInt());
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
    public void saveToFile(String fileName) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        for (int i = 0; i < count; i++) { bw.write(arr[i].toFileLine()); bw.newLine(); }
        System.out.println("Saved " + count + " player(s) to " + fileName);
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }
}

public void loadFromFile(String fileName) {
    count = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            try {
                Player p = parsePlayerLine(line);
                if (!isDuplicateId(p.getId(), null)) { arr[count] = p; count++; }
            } catch (Exception e) {
                System.out.println("Invalid line skipped: " + e.getMessage());
            }
        }
        System.out.println("Loaded " + count + " player(s) from " + fileName);
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}

    private Player parsePlayerLine(String line) {
        String[] f = line.split("\\|", -1);
        if (f.length < 9) throw new IllegalArgumentException("not enough fields");
        Player p;
        if (f[0].equalsIgnoreCase("Regular player")) p = new RegularPlayer();
        else if (f[0].equalsIgnoreCase("Star player")) p = new StarPlayer();
        else throw new IllegalArgumentException("unknown type: " + f[0]);
        p.setType(f[0]); p.setId(f[1]); p.setName(f[2]); p.setAge(Integer.parseInt(f[3]));
        p.setNational(f[4]); p.setPosition(f[5]); p.setNumber(Integer.parseInt(f[6]));
        p.setSalary(Double.parseDouble(f[7])); p.setStatus(f[8]);
        if (f.length > 9 && !f[9].isEmpty()) p.setAbsentDays(Integer.parseInt(f[9]));
        if (f.length > 10 && !f[10].isEmpty()) p.setGoalsScored(Integer.parseInt(f[10]));
        return p;
    }
}
