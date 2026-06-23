package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class RecoveryTrainingSession extends TrainingSession {
    private String recoveryMethod;
    private int durationMinutes;

    public RecoveryTrainingSession() {
        super();
        this.recoveryMethod = "";
        this.durationMinutes = 0;
    }

    public RecoveryTrainingSession(String sessionId, String title, LocalDate date, LocalTime time,
                                   String location, String coachName,
                                   String recoveryMethod, int durationMinutes) {
        super(sessionId, title, date, time, location, coachName);
        this.recoveryMethod = recoveryMethod;
        this.durationMinutes = durationMinutes;
    }

    public String getRecoveryMethod() {
        return recoveryMethod;
    }

    public void setRecoveryMethod(String recoveryMethod) {
        if (recoveryMethod != null && !recoveryMethod.trim().isEmpty()) {
            this.recoveryMethod = recoveryMethod.trim();
        }
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes > 0) {
            this.durationMinutes = durationMinutes;
        }
    }

    // Method overriding
    @Override
    public String getSessionType() {
        return "Recovery Training";
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.println("Recovery Method: " + recoveryMethod);
        System.out.println("Duration (minutes): " + durationMinutes);
    }

    @Override
    public void updateSpecificInfo(Scanner scanner) {
        System.out.print("Enter new recovery method: ");
        setRecoveryMethod(scanner.nextLine());

        while (true) {
            System.out.print("Enter new duration in minutes: ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    setDurationMinutes(value);
                    return;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a positive integer.");
            }
        }
    }
}
