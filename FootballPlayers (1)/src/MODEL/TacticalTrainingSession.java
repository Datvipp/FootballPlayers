package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;

public class TacticalTrainingSession extends TrainingSession {
    private String formation;
    private String tacticFocus;

    public TacticalTrainingSession() {
        super();
        this.formation = "";
        this.tacticFocus = "";
    }

    public TacticalTrainingSession(String sessionId, String title, LocalDate date, LocalTime time,
                                  String location, String coachName,
                                  String formation, String tacticFocus) {
        super(sessionId, title, date, time, location, coachName);
        this.formation = formation;
        this.tacticFocus = tacticFocus;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        if (formation != null && !formation.trim().isEmpty()) {
            this.formation = formation.trim();
        }
    }

    public String getTacticFocus() {
        return tacticFocus;
    }

    public void setTacticFocus(String tacticFocus) {
        if (tacticFocus != null && !tacticFocus.trim().isEmpty()) {
            this.tacticFocus = tacticFocus.trim();
        }
    }

    // Method overriding
    @Override
    public String getSessionType() {
        return "Tactical Training";
    }

    // Method overriding
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Formation: " + formation);
        System.out.println("Tactic Focus: " + tacticFocus);
    }
}
