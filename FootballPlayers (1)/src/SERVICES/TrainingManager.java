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
        this.trainingSessions = new TrainingSession[100];
        this.sessionCount = 0;
        this.scanner = new Scanner(System.in);
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
        String sessionId = scanner.nextLine();

        System.out.print("Enter training title: ");
        String title = scanner.nextLine();

        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter time (HH:mm): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();

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
        String playerName = scanner.nextLine();

        boolean result = session.addAttendance(playerName);

        if (result) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Cannot record attendance. The list may be full or invalid input.");
        }
    }

    public void viewHistory() {
        if (sessionCount == 0) {
            System.out.println("No training history found!");
            return;
        }

        System.out.println("\n===== TRAINING HISTORY =====");

        for (int i = 0; i < sessionCount; i++) {
            trainingSessions[i].displayInfo();
            System.out.println("----------------------------");
        }
    }

    private TrainingSession findSessionById(String sessionId) {
        for (int i = 0; i < sessionCount; i++) {
            if (trainingSessions[i].getSessionId().equalsIgnoreCase(sessionId)) {
                return trainingSessions[i];
            }
        }

        return null;
    }
}
