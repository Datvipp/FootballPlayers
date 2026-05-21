
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manhd
 */
public class Players {
    int id, age, number;
    String name, national, type, status, position;
    double salary;
   
    void inputPlayers(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter player ID: ");
        id=sc.nextInt();
        
        System.out.println("Enter full name: ");
        sc=new Scanner(System.in);
        name=sc.nextLine();
        
        System.out.println("Enter age: ");
        sc=new Scanner(System.in);
        age=sc.nextInt();
        
        System.out.println("Enter nationality: ");
        sc=new Scanner(System.in);
        national=sc.nextLine();
        
        System.out.println("Enter position: ");
        sc=new Scanner(System.in);
        position=sc.nextLine();
        
        System.out.println("Enter shirt number: ");
        sc=new Scanner(System.in);
        number=sc.nextInt();
        
        System.out.println("Enter base salary: ");
        sc=new Scanner(System.in);
        salary=sc.nextDouble();
        
        System.out.println("Enter player type (Regular Player|Star Player): ");
        sc=new Scanner(System.in);
        type=sc.nextLine();
        
        System.out.println("Enter status (Active|Inactive): ");
        sc=new Scanner(System.in);
        status=sc.nextLine();
    }
    
    void displayPlayer(){
        System.out.println("===== PLAYER INFORMATION =====");
        System.out.println("Player ID: " + id);
        System.out.println("Full Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Nationality: " + national);
        System.out.println("Position: " + position);
        System.out.println("Shirt Number: " + number);
        System.out.println("Base Salary: " + salary);
        System.out.println("Player Type: " + type);
        System.out.println("Status: " + status);
    }  
}
