import java.util.Scanner;


public class TrainingSession {
    // Private attributes
    private int trainingID;
    private String date;
    private String location;
    private String topic;
    
    // Constructor
    public TrainingSession() {
        this.trainingID = 0;
        this.date = "";
        this.location = "";
        this.topic = "";
    }
    
    // Constructor
    public TrainingSession(int trainingID, String date, String location, String topic) {
        this.trainingID = trainingID;
        this.date = date;
        this.location = location;
        this.topic = topic;
    }
    
    // Getters
    public int getTrainingID() {
        return this.trainingID;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getTopic() {
        return this.topic;
    }
    
    // Setters
    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    // Input method
    void createTrainingSession() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Training ID: ");
        this.trainingID = sc.nextInt();
        
        System.out.println("Enter date (YYYY-MM-DD): ");
        sc = new Scanner(System.in);
        this.date = sc.nextLine();
        
        System.out.println("Enter location: ");
        sc = new Scanner(System.in);
        this.location = sc.nextLine();
        
        System.out.println("Enter topic: ");
        sc = new Scanner(System.in);
        this.topic = sc.nextLine();
    }
    
    // Display method
    void displayTrainingSession() {
        System.out.println("===== TRAINING SESSION INFORMATION =====");
        System.out.println("Training ID: " + this.trainingID);
        System.out.println("Date: " + this.date);
        System.out.println("Location: " + this.location);
        System.out.println("Topic: " + this.topic);
    }
}
