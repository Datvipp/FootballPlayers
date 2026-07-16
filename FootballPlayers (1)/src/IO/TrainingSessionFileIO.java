package IO;

import MODEL.FitnessTrainingSession;
import MODEL.RecoveryTrainingSession;
import MODEL.TacticalTrainingSession;
import MODEL.TrainingSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling File I/O operations for Training Sessions.
 * Handles saving, loading, and deleting training sessions to/from files.
 */
public class TrainingSessionFileIO {
    /**
     * Saves an array of training sessions to file.
     * Format: TYPE|sessionId|title|date|time|location|coachName|[specific fields]
     */
    public static void saveTrainingSessions(TrainingSession[] sessions,int count, String fileName) throws IOException {

    List<String> lines = new ArrayList<>();

    for (int i = 0; i < count; i++) {
        if (sessions[i] != null) {
            lines.add(convertSessionToLine(sessions[i]));
        }
    }

    FileIOhelper.writeLines(fileName + ".txt", lines);
    System.out.println("Training sessions saved to " + fileName + ".txt");
}

    /**
     * Loads training sessions from file and returns them as an array.
     */
    public static TrainingSession[] loadTrainingSessions(String fileName) throws IOException {

    TrainingSession[] sessions = new TrainingSession[100];
    int count = 0;

    try {
        List<String> lines = FileIOhelper.readLines(fileName + ".txt");

        for (String line : lines) {

            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            TrainingSession session = parseSessionFromLine(line);

            if (session != null) {
                sessions[count++] = session;
            }
        }

        System.out.println("Loaded " + count + " training sessions.");

    } catch (IOException e) {
        System.out.println("File not found!");
    }

    return sessions;
}

    /**
     * Returns the number of training sessions from file.
     */
    public static int getLoadedSessionCount(String fileName) throws IOException {

    try {

        List<String> lines = FileIOhelper.readLines(fileName + ".txt");

        int count = 0;

        for (String line : lines) {
            if (line != null && !line.trim().isEmpty()) {
                count++;
            }
        }

        return count;

    } catch (IOException e) {
        return 0;
    }
}

    /**
     * Deletes a training session by ID from file.
     */
    public static boolean deleteTrainingSessionFromFile(String fileName, String sessionId) throws IOException {

    try {

        List<String> lines = FileIOhelper.readLines(fileName + ".txt");
        List<String> updatedLines = new ArrayList<>();

        boolean found = false;

        for (String line : lines) {

            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|");

            if (parts.length >= 2 &&
                    parts[1].equalsIgnoreCase(sessionId)) {

                found = true;

            } else {

                updatedLines.add(line);
            }
        }

        if (found) {

            FileIOhelper.writeLines(fileName + ".txt", updatedLines);

            System.out.println("Training session deleted.");
        }

        return found;

    } catch (IOException e) {

        System.out.println("Error: " + e.getMessage());
        return false;
    }
}

    /**
     * Converts a TrainingSession object to a file line format.
     */
    private static String convertSessionToLine(TrainingSession session) {
    return session.toFileString();
}

    /**
     * Parses a file line and converts it back to a TrainingSession object.
     */
    private static TrainingSession parseSessionFromLine(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 7) {
                return null;
            }

            String type = parts[0];
            String sessionId = parts[1];
            String title = parts[2];
            LocalDate date = LocalDate.parse(parts[3]);
            LocalTime time = LocalTime.parse(parts[4]);
            String location = parts[5];
            String coachName = parts[6];

            TrainingSession session;

            if (type.equals("FITNESS") && parts.length >= 9) {
                String intensityLevel = parts[7];
                String fitnessGoal = parts[8];
                session = new FitnessTrainingSession(sessionId, title, date, time, location, coachName,
                        intensityLevel, fitnessGoal);
            } else if (type.equals("TACTICAL") && parts.length >= 9) {
                String formation = parts[7];
                String tacticFocus = parts[8];
                session = new TacticalTrainingSession(sessionId, title, date, time, location, coachName,
                        formation, tacticFocus);
            } else if (type.equals("RECOVERY") && parts.length >= 9) {
                String recoveryMethod = parts[7];
                int durationMinutes = Integer.parseInt(parts[8]);
                session = new RecoveryTrainingSession(sessionId, title, date, time, location, coachName,
                        recoveryMethod, durationMinutes);
            } else {
                // General Training
                session = new TrainingSession(sessionId, title, date, time, location, coachName);
            }

            return session;
        } catch (Exception e) {
            System.out.println("Error parsing training session line: " + e.getMessage());
            return null;
        }
    }
}
