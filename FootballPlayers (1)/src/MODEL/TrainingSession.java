package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingSession {
    private String sessionId;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String coachName;

    private String[] attendanceList;
    private int attendanceCount;

    public TrainingSession() {
        this.attendanceList = new String[50];
        this.attendanceCount = 0;
    }

    public TrainingSession(String sessionId, String title, LocalDate date, LocalTime time, String location, String coachName) {
        this.sessionId = sessionId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.coachName = coachName;
        this.attendanceList = new String[50];
        this.attendanceCount = 0;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        if (sessionId != null && !sessionId.trim().isEmpty()) {
            this.sessionId = sessionId;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
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
            this.location = location;
        }
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        if (coachName != null && !coachName.trim().isEmpty()) {
            this.coachName = coachName;
        }
    }

    public String[] getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(String[] attendanceList) {
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

    public boolean addAttendance(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return false;
        }

        if (attendanceCount >= attendanceList.length) {
            return false;
        }

        attendanceList[attendanceCount] = playerName;
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
            System.out.println("- " + attendanceList[i]);
        }
    }

    public void displayInfo() {
        System.out.println("Session ID: " + sessionId);
        System.out.println("Title: " + title);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Location: " + location);
        System.out.println("Coach: " + coachName);
        displayAttendance();
    }
}
