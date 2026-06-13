package MODEL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Objects;

/**
 * TrainingSession class represents a training session for the team
 * Inherits from Entity base class
 */
public class TrainingSession extends Entity {
    private String sessionId;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String coachName;

    private AttendanceRecord[] attendanceList;
    private int attendanceCount;

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

    public boolean addAttendance(String playerName) {
        return addAttendance(playerName, "Present", "");
    }

    public boolean addAttendance(String playerName, String status, String note) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return false;
        }

        String normalizedName = playerName.trim();

        for (int i = 0; i < attendanceCount; i++) {
            if (attendanceList[i].getPlayerName().equalsIgnoreCase(normalizedName)) {
                return false;
            }
        }

        if (attendanceCount >= attendanceList.length) {
            return false;
        }

        attendanceList[attendanceCount] = new AttendanceRecord(normalizedName, status, note);
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
            attendanceList[i].display();
        }
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public void input(Scanner sc) {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        System.out.print("Input Session ID: ");
        this.sessionId = sc.nextLine();
        
        System.out.print("Input Title: ");
        this.title = sc.nextLine();
        
        System.out.print("Input Date (YYYY-MM-DD): ");
        try {
            this.date = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            this.date = LocalDate.now();
        }
        
        System.out.print("Input Time (HH:MM:SS): ");
        try {
            this.time = LocalTime.parse(sc.nextLine());
        } catch (Exception e) {
            this.time = LocalTime.now();
        }
        
        System.out.print("Input Location: ");
        this.location = sc.nextLine();
        
        System.out.print("Input Coach Name: ");
        this.coachName = sc.nextLine();
    }

    @Override
    public void output() {
        System.out.println(this.toString());
    }

    @Override
    public int getId() {
        try {
            return Integer.parseInt(sessionId);
        } catch (NumberFormatException e) {
            return sessionId.hashCode();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== TRAINING SESSION INFORMATION =====\n");
        sb.append("Session ID: ").append(sessionId).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Time: ").append(time).append("\n");
        sb.append("Location: ").append(location).append("\n");
        sb.append("Coach: ").append(coachName).append("\n");
        
        if (attendanceCount == 0) {
            sb.append("Attendance: No players recorded\n");
        } else {
            sb.append("Attendance:\n");
            for (int i = 0; i < attendanceCount; i++) {
                sb.append("  - ").append(attendanceList[i].getPlayerName())
                  .append(" [").append(attendanceList[i].getStatus()).append("]\n");
                if (!attendanceList[i].getNote().isEmpty()) {
                    sb.append("    Note: ").append(attendanceList[i].getNote()).append("\n");
                }
            }
        }
        sb.append("========================================");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TrainingSession)) {
            return false;
        }
        TrainingSession other = (TrainingSession) obj;
        return Objects.equals(this.sessionId, other.sessionId)
                && Objects.equals(this.date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sessionId, this.date);
    }
}
