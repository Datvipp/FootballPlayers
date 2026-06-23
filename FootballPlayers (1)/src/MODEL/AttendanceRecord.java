package MODEL;

public class AttendanceRecord {
    private String playerId;
    private String playerName;
    private String status;
    private String note;

    public AttendanceRecord() {
        this("", "", "Present", "");
    }

    public AttendanceRecord(String playerName, String status, String note) {
        this("", playerName, status, note);
    }

    public AttendanceRecord(String playerId, String playerName, String status, String note) {
        this.playerId = (playerId == null) ? "" : playerId.trim();
        this.playerName = (playerName == null) ? "" : playerName.trim();
        this.status = (status == null || status.trim().isEmpty()) ? "Present" : status.trim();
        this.note = (note == null) ? "" : note.trim();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = (playerId == null) ? "" : playerId.trim();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = (playerName == null) ? "" : playerName.trim();
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
        if (playerId.isEmpty()) {
            System.out.println("- " + playerName + " [" + status + "]");
        } else {
            System.out.println("- " + playerId + " - " + playerName + " [" + status + "]");
        }
        if (!note.isEmpty()) {
            System.out.println("  Note: " + note);
        }
    }
}
