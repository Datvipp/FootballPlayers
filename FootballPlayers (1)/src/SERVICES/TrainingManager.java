package SERVICES;

import MODEL.FitnessTrainingSession;
import MODEL.Player;
import MODEL.RecoveryTrainingSession;
import MODEL.TacticalTrainingSession;
import MODEL.TrainingSession;
import IO.TrainingSessionFileIO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TrainingManager {
    private TrainingSession[] trainingSessions;
    private int sessionCount;
    private Scanner scanner;
    private ClubManager clubManager;

    public TrainingManager() {
        this(new Scanner(System.in), null);
    }

    public TrainingManager(Scanner scanner) {
        this(scanner, null);
    }

    public TrainingManager(Scanner scanner, ClubManager clubManager) {
        this.trainingSessions = new TrainingSession[100];
        this.sessionCount = 0;
        this.scanner = (scanner != null) ? scanner : new Scanner(System.in);
        this.clubManager = clubManager;
    }

    public TrainingSession[] getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(TrainingSession[] trainingSessions) {
        if (trainingSessions != null) {
            this.trainingSessions = trainingSessions;
        }
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int sessionCount) {
        if (sessionCount >= 0 && sessionCount <= trainingSessions.length) {
            this.sessionCount = sessionCount;
        }
    }

    public void setScanner(Scanner scanner) {
        this.scanner = (scanner != null) ? scanner : new Scanner(System.in);
    }

    public ClubManager getClubManager() {
        return clubManager;
    }

    public void setClubManager(ClubManager clubManager) {
        this.clubManager = clubManager;
    }

    public void createSession() {
        if (sessionCount >= trainingSessions.length) {
            System.out.println("Training session list is full!");
            return;
        }

        int typeChoice = readSessionTypeChoice();
        if (typeChoice == -1) {
            return;
        }

        String sessionId = readSessionId();
        String title = readRequiredText("Enter training title: ", "Title cannot be empty!");
        LocalDate date = readDateInput();
        LocalTime time = readTimeInput();
        String location = readRequiredText("Enter location: ", "Location cannot be empty!");
        String coachName = readRequiredText("Enter coach name: ", "Coach name cannot be empty!");

        TrainingSession session;

        if (typeChoice == 2) {
            String intensityLevel = readRequiredText("Enter intensity level: ", "Intensity level cannot be empty!");
            String fitnessGoal = readRequiredText("Enter fitness goal: ", "Fitness goal cannot be empty!");
            session = new FitnessTrainingSession(sessionId, title, date, time, location, coachName,
                    intensityLevel, fitnessGoal);
        } else if (typeChoice == 3) {
            String formation = readRequiredText("Enter formation: ", "Formation cannot be empty!");
            String tacticFocus = readRequiredText("Enter tactic focus: ", "Tactic focus cannot be empty!");
            session = new TacticalTrainingSession(sessionId, title, date, time, location, coachName,
                    formation, tacticFocus);
        } else if (typeChoice == 4) {
            String recoveryMethod = readRequiredText("Enter recovery method: ", "Recovery method cannot be empty!");
            int durationMinutes = readPositiveInteger("Enter duration in minutes: ");
            session = new RecoveryTrainingSession(sessionId, title, date, time, location, coachName,
                    recoveryMethod, durationMinutes);
        } else {
            session = new TrainingSession(sessionId, title, date, time, location, coachName);
        }

        session.setPlayerProvider(clubManager);
        trainingSessions[sessionCount] = session;
        sessionCount++;

        System.out.println("Training session created successfully!");
    }

    public void recordAttendance() {
        String sessionId;
        TrainingSession session = null;
        while (true) {
            System.out.print("Enter session ID: ");
            sessionId = scanner.nextLine().trim();
            session = findSessionById(sessionId);
            if (session == null) {
                System.out.println("Session not found! Please try again.");
                continue;
            }
            break;
        }

        if (clubManager == null) {
            System.out.println("Player Management is not connected. Cannot verify player ID.");
            return;
        }

        if (clubManager.getCount() == 0) {
            System.out.println("No players found. Please add players before recording attendance.");
            return;
        }

        Player player;
        while (true) {
            System.out.print("Enter player ID: ");
            String playerId = scanner.nextLine().trim();
            if (playerId.isEmpty()) {
                System.out.println("Player ID cannot be empty! Please try again.");
                continue;
            }

            player = clubManager.getPlayerById(playerId);
            if (player == null) {
                System.out.println("Player not found! Please try again.");
                continue;
            }

            System.out.println("Player found: " + player.getName());
            break;
        }

        if (player.getStatus() != null && player.getStatus().equalsIgnoreCase("Inactive")) {
            System.out.println("Cannot record attendance for an inactive player.");
            return;
        }

        String status;
        while (true) {
            System.out.print("Enter status (Present/Absent/Late): ");
            status = scanner.nextLine().trim();
            if (!isValidStatus(status)) {
                System.out.println("Invalid status. Allowed values: Present, Absent, Late.");
                continue;
            }
            break;
        }

        System.out.print("Enter note: ");
        String note = scanner.nextLine();

        boolean result = session.addAttendance(player.getId(), status, note);

        if (result) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Cannot record attendance. The player may already be present, the list may be full, or the input is invalid.");
        }
    }

    public void updateSession() {
        if (sessionCount == 0) {
            System.out.println("No training sessions found!");
            return;
        }

        TrainingSession session = readExistingSession("Enter session ID to update: ");

        session.setTitle(readRequiredText("Enter new training title: ", "Title cannot be empty!"));
        session.setDate(readDateInput());
        session.setTime(readTimeInput());
        session.setLocation(readRequiredText("Enter new location: ", "Location cannot be empty!"));
        session.setCoachName(readRequiredText("Enter new coach name: ", "Coach name cannot be empty!"));
        session.updateSpecificInfo(scanner);

        System.out.println("Training session updated successfully!");
    }

    public void deleteSession() {
        if (sessionCount == 0) {
            System.out.println("No training sessions found!");
            return;
        }

        System.out.print("Enter session ID to delete: ");
        String sessionId = scanner.nextLine().trim();

        for (int i = 0; i < sessionCount; i++) {
            if (trainingSessions[i] != null && trainingSessions[i].getSessionId().equalsIgnoreCase(sessionId)) {
                for (int j = i; j < sessionCount - 1; j++) {
                    trainingSessions[j] = trainingSessions[j + 1];
                }
                trainingSessions[sessionCount - 1] = null;
                sessionCount--;
                System.out.println("Training session deleted successfully!");
                return;
            }
        }

        System.out.println("Session not found!");
    }

    public void searchSession() {
        String sessionId;
        while (true) {
            System.out.print("Enter session ID to search: ");
            sessionId = scanner.nextLine().trim();
            TrainingSession session = findSessionById(sessionId);
            if (session == null) {
                System.out.println("Session not found! Please try again.");
                continue;
            }
            // Dynamic dispatch
            session.displayInfo();
            break;
        }
    }

    public void viewHistory() {
        if (sessionCount == 0) {
            System.out.println("No training history found!");
            return;
        }

        System.out.println("\n===== TRAINING HISTORY =====");

        for (int i = 0; i < sessionCount; i++) {
            // Dynamic dispatch
            trainingSessions[i].displayInfo();
            System.out.println("Attendance count: " + trainingSessions[i].getAttendanceCount());
            System.out.println("----------------------------");
        }
    }

    /**
     * Saves all training sessions to file.
     */
    public void saveToFile() {
        try {
            TrainingSessionFileIO.saveTrainingSessions(trainingSessions, sessionCount);
        } catch (IOException e) {
            System.out.println("Error saving training sessions: " + e.getMessage());
        }
    }

    /**
     * Loads training sessions from file.
     */
    public void loadFromFile() {
        try {
            TrainingSession[] loadedSessions = TrainingSessionFileIO.loadTrainingSessions();
            int loadedCount = TrainingSessionFileIO.getLoadedSessionCount();

            // Copy loaded sessions to current array
            for (int i = 0; i < loadedCount && i < 100; i++) {
                if (loadedSessions[i] != null) {
                    trainingSessions[i] = loadedSessions[i];
                    if (clubManager != null) {
                        trainingSessions[i].setPlayerProvider(clubManager);
                    }
                }
            }
            sessionCount = loadedCount;
        } catch (IOException e) {
            System.out.println("Error loading training sessions: " + e.getMessage());
        }
    }

    /**
     * Deletes a training session by ID from file.
     */
    public void deleteFromFile() {
        System.out.print("Enter session ID to delete from file: ");
        String sessionId = scanner.nextLine().trim();
        
        try {
            boolean deleted = TrainingSessionFileIO.deleteTrainingSessionFromFile(sessionId);
            if (deleted) {
                // Also remove from current array if exists
                deleteSession(sessionId);
            }
        } catch (IOException e) {
            System.out.println("Error deleting training session: " + e.getMessage());
        }
    }

    /**
     * Helper method to delete a session by ID from current array
     */
    private void deleteSession(String sessionId) {
        for (int i = 0; i < sessionCount; i++) {
            if (trainingSessions[i] != null && trainingSessions[i].getSessionId().equalsIgnoreCase(sessionId)) {
                for (int j = i; j < sessionCount - 1; j++) {
                    trainingSessions[j] = trainingSessions[j + 1];
                }
                trainingSessions[sessionCount - 1] = null;
                sessionCount--;
                return;
            }
        }
    }

    private TrainingSession findSessionById(String sessionId) {
        for (int i = 0; i < sessionCount; i++) {
            if (trainingSessions[i] != null && trainingSessions[i].getSessionId().equalsIgnoreCase(sessionId)) {
                return trainingSessions[i];
            }
        }

        return null;
    }

    private TrainingSession readExistingSession(String prompt) {
        while (true) {
            System.out.print(prompt);
            String sessionId = scanner.nextLine().trim();
            TrainingSession session = findSessionById(sessionId);
            if (session == null) {
                System.out.println("Session not found! Please try again.");
                continue;
            }
            return session;
        }
    }

    private boolean isValidStatus(String status) {
        return status != null && (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent") || status.equalsIgnoreCase("Late"));
    }

    private int readSessionTypeChoice() {
        while (true) {
            System.out.println("Choose training session type:");
            System.out.println("1. General Training");
            System.out.println("2. Fitness Training");
            System.out.println("3. Tactical Training");
            System.out.println("4. Recovery Training");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
            }
        }
    }

    private String readSessionId() {
        while (true) {
            System.out.print("Enter session ID: ");
            String sessionId = scanner.nextLine().trim();
            if (sessionId.isEmpty()) {
                System.out.println("Session ID cannot be empty! Please try again.");
                continue;
            }
            if (findSessionById(sessionId) != null) {
                System.out.println("Session ID already exists! Please try again.");
                continue;
            }
            return sessionId;
        }
    }

    private String readRequiredText(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println(errorMessage);
                continue;
            }
            return value;
        }
    }

    private LocalDate readDateInput() {
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateInput = scanner.nextLine().trim();
            try {
                return LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }

    private LocalTime readTimeInput() {
        while (true) {
            System.out.print("Enter time (HH:mm): ");
            String timeInput = scanner.nextLine().trim();
            try {
                return LocalTime.parse(timeInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }
    }

    private int readPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a positive integer.");
            }
        }
    }
}
