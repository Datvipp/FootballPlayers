package MODEL;

public class AttendanceRecord {
    private String playerId;
    private String status;
    private String note;

    public AttendanceRecord() {
        this("", "Present", "");
    }

    public AttendanceRecord(String playerId, String status, String note) {
        this.playerId = (playerId == null) ? "" : playerId.trim();
        this.status = (status == null || status.trim().isEmpty()) ? "Present" : status.trim();
        this.note = (note == null) ? "" : note.trim();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = (playerId == null) ? "" : playerId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status == null || status.trim().isEmpty()) ? "Present" : status.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = (note == null) ? "" : note.trim();
    }

    public void display() {
        System.out.println(toString());
        if (!note.isEmpty()) {
            System.out.println("  Note: " + note);
        }
    }

    @Override
    public String toString() {
        if (playerId.isEmpty()) {
            return "- [No ID] [" + status + "]";
        }

        return "- " + playerId + " [" + status + "]";
    }
}
