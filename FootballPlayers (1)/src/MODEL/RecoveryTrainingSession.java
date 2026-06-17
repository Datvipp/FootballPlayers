package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // Method overriding
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Recovery Method: " + recoveryMethod);
        System.out.println("Duration (minutes): " + durationMinutes);
    }
}
