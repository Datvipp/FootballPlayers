import java.util.Scanner;
 
/*
 * Training Session Management
 * @author manhd
 */
public class TrainingSession {
    int trainingID;
    String date;
    String location;
    String topic;
    
    void createTrainingSession(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Training ID: ");
        trainingID = sc.nextInt();
        
        System.out.println("Enter date (YYYY-MM-DD): ");
        sc = new Scanner(System.in);
        date = sc.nextLine();
        
        System.out.println("Enter location: ");
        sc = new Scanner(System.in);
        location = sc.nextLine();
        
        System.out.println("Enter topic: ");
        sc = new Scanner(System.in);
        topic = sc.nextLine();
    }
    
    void displayTrainingSession(){
        System.out.println("===== TRAINING SESSION INFORMATION =====");
        System.out.println("Training ID: " + trainingID);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Topic: " + topic);
    }
}