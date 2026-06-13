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

    public void setScanner(Scanner scanner) {
        this.scanner = (scanner != null) ? scanner : new Scanner(System.in);
    }

    public void createSession() {
        if (sessionCount >= trainingSessions.length) {
            System.out.println("Training session list is full!");
            return;
        }

        String sessionId;
        while (true) {
            System.out.print("Enter session ID: ");
            sessionId = scanner.nextLine().trim();
            if (sessionId.isEmpty()) {
                System.out.println("Session ID cannot be empty! Please try again.");
                continue;
            }
            if (findSessionById(sessionId) != null) {
                System.out.println("Session ID already exists! Please try again.");
                continue;
            }
            break;
        }

        String title;
        while (true) {
            System.out.print("Enter training title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty! Please try again.");
                continue;
            }
            break;
        }

        LocalDate date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateInput = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }

        LocalTime time;
        while (true) {
            System.out.print("Enter time (HH:mm): ");
            String timeInput = scanner.nextLine().trim();
            try {
                time = LocalTime.parse(timeInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }

        String location;
        while (true) {
            System.out.print("Enter location: ");
            location = scanner.nextLine().trim();
            if (location.isEmpty()) {
                System.out.println("Location cannot be empty! Please try again.");
                continue;
            }
            break;
        }

        String coachName;
        while (true) {
            System.out.print("Enter coach name: ");
            coachName = scanner.nextLine().trim();
            if (coachName.isEmpty()) {
                System.out.println("Coach name cannot be empty! Please try again.");
                continue;
            }
            break;
        }

        TrainingSession session = new TrainingSession(sessionId, title, date, time, location, coachName);

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

        String playerName;
        while (true) {
            System.out.print("Enter player name: ");
            playerName = scanner.nextLine().trim();
            if (playerName.isEmpty()) {
                System.out.println("Player name cannot be empty! Please try again.");
                continue;
            }
            break;
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

        boolean result = session.addAttendance(playerName, status, note);

        if (result) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Cannot record attendance. The player may already be present, the list may be full, or the input is invalid.");
        }
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
