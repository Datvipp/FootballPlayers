package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class TrainingSession {
    // Milestone 3 inheritance evidence
    protected String sessionId;
    protected String title;
    protected LocalDate date;
    protected LocalTime time;
    protected String location;
    protected String coachName;

    protected AttendanceRecord[] attendanceList;
    protected int attendanceCount;

    public TrainingSession() {
        this("", "", LocalDate.now(), LocalTime.now(), "", "");
    }

    public TrainingSession(String sessionId, String title, LocalDate date, LocalTime time, String location, String coachName) {
        this.sessionId = sessionId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.coachName = coachName;
        this.attendanceList = new AttendanceRecord[50];
        this.attendanceCount = 0;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        if (sessionId != null && !sessionId.trim().isEmpty()) {
            this.sessionId = sessionId.trim();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title.trim();
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        if (time != null) {
            this.time = time;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.location = location.trim();
        }
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        if (coachName != null && !coachName.trim().isEmpty()) {
            this.coachName = coachName.trim();
        }
    }

    public AttendanceRecord[] getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceRecord[] attendanceList) {
        if (attendanceList != null) {
            this.attendanceList = attendanceList;
        }
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        if (attendanceCount >= 0 && attendanceCount <= attendanceList.length) {
            this.attendanceCount = attendanceCount;
        }
    }

    // Milestone 3 polymorphism evidence
    public String getSessionType() {
        return "General Training";
    }

    public boolean addAttendance(String playerName) {
        return addAttendance(playerName, "Present", "");
    }

    public boolean addAttendance(String playerName, String status, String note) {
        return addAttendance("", playerName, status, note);
    }

    public boolean addAttendance(String playerId, String playerName, String status, String note) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return false;
        }

        String normalizedId = (playerId == null) ? "" : playerId.trim();
        String normalizedName = playerName.trim();

        for (int i = 0; i < attendanceCount; i++) {
            if (attendanceList[i] != null) {
                String recordedId = attendanceList[i].getPlayerId();
                boolean samePlayerId = !normalizedId.isEmpty() && recordedId.equalsIgnoreCase(normalizedId);
                boolean samePlayerName = normalizedId.isEmpty()
                        && attendanceList[i].getPlayerName().equalsIgnoreCase(normalizedName);

                if (samePlayerId || samePlayerName) {
                    return false;
                }
            }
        }

        if (attendanceCount >= attendanceList.length) {
            return false;
        }

        attendanceList[attendanceCount] = new AttendanceRecord(normalizedId, normalizedName, status, note);
        attendanceCount++;
        return true;
    }

    public void displayAttendance() {
        if (attendanceCount == 0) {
            System.out.println("Attendance: No players recorded");
            return;
        }

        System.out.println("Attendance:");
        for (int i = 0; i < attendanceCount; i++) {
            if (attendanceList[i] != null) {
                attendanceList[i].display();
            }
        }
    }

    public void displayAttendanceSummary() {
        int present = 0;
        int absent = 0;
        int late = 0;

        for (int i = 0; i < attendanceCount; i++) {
            if (attendanceList[i] != null) {
                String status = attendanceList[i].getStatus();

                if (status.equalsIgnoreCase("Present")) {
                    present++;
                } else if (status.equalsIgnoreCase("Absent")) {
                    absent++;
                } else if (status.equalsIgnoreCase("Late")) {
                    late++;
                }
            }
        }

        System.out.println("Attendance Summary: Present=" + present
                + ", Absent=" + absent
                + ", Late=" + late);
    }

    protected void displaySpecificInfo() {
    }

    public void updateSpecificInfo(Scanner scanner) {
    }

    // Method overriding
    public void displayInfo() {
        System.out.println("Session Type: " + getSessionType());
        System.out.println("Session ID: " + sessionId);
        System.out.println("Title: " + title);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Location: " + location);
        System.out.println("Coach: " + coachName);
        displaySpecificInfo();
        displayAttendance();
        displayAttendanceSummary();
    }
}
