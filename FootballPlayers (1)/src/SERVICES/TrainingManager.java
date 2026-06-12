package SERVICES;

import MODEL.TrainingSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class TrainingManager {
    private TrainingSession[] trainingSessions;
    private int sessionCount;
    private Scanner scanner;

    public TrainingManager() {
        this(new Scanner(System.in));
    }

    public TrainingManager(Scanner scanner) {
        this.trainingSessions = new TrainingSession[100];
        this.sessionCount = 0;
        this.scanner = (scanner != null) ? scanner : new Scanner(System.in);
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

    public void createSession() {
        if (sessionCount >= trainingSessions.length) {
            System.out.println("Training session list is full!");
            return;
        }

        System.out.print("Enter session ID: ");
        String sessionId = scanner.nextLine().trim();

        if (sessionId.isEmpty()) {
            System.out.println("Session ID cannot be empty!");
            return;
        }

        if (findSessionById(sessionId) != null) {
            System.out.println("Session ID already exists!");
            return;
        }

        System.out.print("Enter training title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }

        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return;
        }

        System.out.print("Enter time (HH:mm): ");
        LocalTime time;
        try {
            time = LocalTime.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid time format. Please use HH:mm.");
            return;
        }

        System.out.print("Enter location: ");
        String location = scanner.nextLine().trim();

        if (location.isEmpty()) {
            System.out.println("Location cannot be empty!");
            return;
        }

        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine().trim();

        if (coachName.isEmpty()) {
            System.out.println("Coach name cannot be empty!");
            return;
        }

        TrainingSession session = new TrainingSession(sessionId, title, date, time, location, coachName);

        trainingSessions[sessionCount] = session;
        sessionCount++;

        System.out.println("Training session created successfully!");
    }

    public void recordAttendance() {
        System.out.print("Enter session ID: ");
        String sessionId = scanner.nextLine();

        TrainingSession session = findSessionById(sessionId);

        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine().trim();

        if (playerName.isEmpty()) {
            System.out.println("Player name cannot be empty!");
            return;
        }

        System.out.print("Enter status (Present/Absent/Late): ");
        String status = scanner.nextLine().trim();

        if (!isValidStatus(status)) {
            System.out.println("Invalid status. Allowed values: Present, Absent, Late.");
            return;
        }

        System.out.print("Enter note: ");
        String note = scanner.nextLine();

        boolean result = session.addAttendance(playerName, status, note);

        if (result) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Cannot record attendance. The player may already be present, the list may be full, or the input is invalid.");
        }
    }

    public void searchSession() {
        System.out.print("Enter session ID to search: ");
        String sessionId = scanner.nextLine();

        TrainingSession session = findSessionById(sessionId);

        if (session == null) {
            System.out.println("Session not found!");
            return;
        }

        session.displayInfo();
    }

    public void viewHistory() {
        if (sessionCount == 0) {
            System.out.println("No training history found!");
            return;
        }

        System.out.println("\n===== TRAINING HISTORY =====");

        for (int i = 0; i < sessionCount; i++) {
            trainingSessions[i].displayInfo();
            System.out.println("Attendance count: " + trainingSessions[i].getAttendanceCount());
            System.out.println("----------------------------");
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

    private boolean isValidStatus(String status) {
        return status != null && (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent") || status.equalsIgnoreCase("Late"));
    }
}
