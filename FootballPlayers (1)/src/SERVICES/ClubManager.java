package SERVICES;

import MODEL.Player;
import MODEL.RegularPlayer;
import MODEL.StarPlayer;
import java.util.Scanner;

public class ClubManager {
    Player[] arr = new Player[100];
    int count = 0;
    private Scanner sc;

    public ClubManager() {
        this.sc = new Scanner(System.in);
    }

    public ClubManager(Scanner sc) {
        this.sc = (sc != null) ? sc : new Scanner(System.in);
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

            if (choice == 1) {
                arr[count] = new RegularPlayer();
                arr[count].setType("Regular player");
            } else {
                arr[count] = new StarPlayer();
                arr[count].setType("Star player");
            }

            arr[count].inputPlayers(this.sc);
            count++;
            System.out.println("Add more (true|false)?: ");

            cont = this.sc.nextBoolean();
            this.sc.nextLine();

        } while (cont == true && count < 100);
    }

    public void viewAllPlayers() {
        for (int i = 0; i < count; i++) {
            arr[i].displayPlayer();
            System.out.println("\n");
        }
    }

    public void updateInfo() {
        System.out.println("Enter Player id to update");
        String idUpdate = this.sc.nextLine();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(idUpdate)) {
                System.out.println("===Enter player new information===");
                arr[i].inputPlayers(this.sc);
                found = true;
                System.out.println("Update successful!");
                break;
            }
        }
        if (!found)
            System.out.println("Player not found");
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

    public void searchPlayer(String id) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equals(id)) {
                arr[i].displayPlayer();
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Player not found!");
        }
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
}
