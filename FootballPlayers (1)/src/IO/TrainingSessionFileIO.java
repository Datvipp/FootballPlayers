package IO;

import MODEL.TrainingSession;
import MODEL.FitnessTrainingSession;
import MODEL.TacticalTrainingSession;
import MODEL.RecoveryTrainingSession;
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

    private static final String TRAINING_FILE = "training_sessions.txt";

    private TrainingSessionFileIO() {
        // Utility class
    }

    /**
     * Saves an array of training sessions to file.
     * Format: TYPE|sessionId|title|date|time|location|coachName|[specific fields]
     */
    public static void saveTrainingSessions(TrainingSession[] sessions, int count) throws IOException {
        List<String> lines = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (sessions[i] != null) {
                String line = convertSessionToLine(sessions[i]);
                lines.add(line);
            }
        }

        FileIOhelper.writeLines(TRAINING_FILE, lines);
        System.out.println("Training sessions saved successfully!");
    }

    /**
     * Loads training sessions from file and returns them as an array.
     */
    public static TrainingSession[] loadTrainingSessions() throws IOException {
        TrainingSession[] sessions = new TrainingSession[100];
        int count = 0;

        try {
            List<String> lines = FileIOhelper.readLines(TRAINING_FILE);

            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                TrainingSession session = parseSessionFromLine(line);
                if (session != null) {
                    sessions[count] = session;
                    count++;
                }
            }

            System.out.println("Training sessions loaded successfully! (" + count + " sessions)");
        } catch (IOException e) {
            System.out.println("No existing training data file found. Starting fresh.");
        }

        // Return array with actual count (will be set separately)
        return sessions;
    }

    /**
     * Returns the number of training sessions from file.
     */
    public static int getLoadedSessionCount() throws IOException {
        try {
            List<String> lines = FileIOhelper.readLines(TRAINING_FILE);
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
    public static boolean deleteTrainingSessionFromFile(String sessionId) throws IOException {
        try {
            List<String> lines = FileIOhelper.readLines(TRAINING_FILE);
            List<String> updatedLines = new ArrayList<>();
            boolean found = false;

            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length >= 2 && parts[1].equalsIgnoreCase(sessionId)) {
                    found = true;
                    // Skip this line (delete)
                } else {
                    updatedLines.add(line);
                }
            }

            if (found) {
                FileIOhelper.writeLines(TRAINING_FILE, updatedLines);
                System.out.println("Training session deleted from file successfully!");
            } else {
                System.out.println("Training session not found in file!");
            }

            return found;
        } catch (IOException e) {
            System.out.println("Error deleting training session: " + e.getMessage());
            return false;
        }
    }

    /**
     * Converts a TrainingSession object to a file line format.
     */
    private static String convertSessionToLine(TrainingSession session) {
        String type = session.getSessionType();
        StringBuilder sb = new StringBuilder();

        // Determine session type
        if (type.equals("Fitness Training")) {
            FitnessTrainingSession fs = (FitnessTrainingSession) session;
            sb.append("FITNESS|").append(session.getSessionId()).append("|")
                    .append(session.getTitle()).append("|")
                    .append(session.getDate()).append("|")
                    .append(session.getTime()).append("|")
                    .append(session.getLocation()).append("|")
                    .append(session.getCoachName()).append("|")
                    .append(fs.getIntensityLevel()).append("|")
                    .append(fs.getFitnessGoal());
        } else if (type.equals("Tactical Training")) {
            TacticalTrainingSession ts = (TacticalTrainingSession) session;
            sb.append("TACTICAL|").append(session.getSessionId()).append("|")
                    .append(session.getTitle()).append("|")
                    .append(session.getDate()).append("|")
                    .append(session.getTime()).append("|")
                    .append(session.getLocation()).append("|")
                    .append(session.getCoachName()).append("|")
                    .append(ts.getFormation()).append("|")
                    .append(ts.getTacticFocus());
        } else if (type.equals("Recovery Training")) {
            RecoveryTrainingSession rs = (RecoveryTrainingSession) session;
            sb.append("RECOVERY|").append(session.getSessionId()).append("|")
                    .append(session.getTitle()).append("|")
                    .append(session.getDate()).append("|")
                    .append(session.getTime()).append("|")
                    .append(session.getLocation()).append("|")
                    .append(session.getCoachName()).append("|")
                    .append(rs.getRecoveryMethod()).append("|")
                    .append(rs.getDurationMinutes());
        } else {
            // General Training
            sb.append("GENERAL|").append(session.getSessionId()).append("|")
                    .append(session.getTitle()).append("|")
                    .append(session.getDate()).append("|")
                    .append(session.getTime()).append("|")
                    .append(session.getLocation()).append("|")
                    .append(session.getCoachName());
        }

        return sb.toString();
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
