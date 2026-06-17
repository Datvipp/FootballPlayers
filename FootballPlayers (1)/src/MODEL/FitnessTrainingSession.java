package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;

public class FitnessTrainingSession extends TrainingSession {
    private String intensityLevel;
    private String fitnessGoal;

    public FitnessTrainingSession() {
        super();
        this.intensityLevel = "";
        this.fitnessGoal = "";
    }

    public FitnessTrainingSession(String sessionId, String title, LocalDate date, LocalTime time,
                                  String location, String coachName,
                                  String intensityLevel, String fitnessGoal) {
        super(sessionId, title, date, time, location, coachName);
        this.intensityLevel = intensityLevel;
        this.fitnessGoal = fitnessGoal;
    }

    public String getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(String intensityLevel) {
        if (intensityLevel != null && !intensityLevel.trim().isEmpty()) {
            this.intensityLevel = intensityLevel.trim();
        }
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        if (fitnessGoal != null && !fitnessGoal.trim().isEmpty()) {
            this.fitnessGoal = fitnessGoal.trim();
        }
    }

    // Method overriding
    @Override
    public String getSessionType() {
        return "Fitness Training";
    }

    // Method overriding
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Intensity Level: " + intensityLevel);
        System.out.println("Fitness Goal: " + fitnessGoal);
    }
}
