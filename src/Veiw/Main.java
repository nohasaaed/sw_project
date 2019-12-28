/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veiw;

import Controller.UserController;
import java.util.Scanner;
/**
 *
 * @author Abdulrhman
 */
public class Main {
    
    public static void signUp(String gender, String firstName, String lastName, String address, String birthDate, String email, String password) {
        user.signUp(gender, firstName, lastName, address, birthDate, email, password);
    }
    
    public static void logIn(String email, String password) {
        int choice;
        String result = user.logIn(email, password);
        if (result.endsWith("invaid Email or Password")) {
            System.out.println(result);
        } else {
            do {
                System.out.println("\n\nWelcome " + result);
                if (user.checkPrem().equals("no")) {
                    System.out.print("1-Add Frind\n2-Freinds Requests\n3-Upgrade Premium\n4-Log Out\nEnter Choice:");
                }
                else
                    System.out.print("1-Add Frind\n2-Freinds Requests\n3-Log Out\nEnter Choice:");
                choice = input.nextInt();
                if(user.checkPrem().equals("yes")&& choice>2)
                    choice++;
                switch (choice) {
                    case 1:
                        input.nextLine();
                        System.out.print("Enter Freind Mail:");
                        String mail = input.nextLine();
                        result = user.addFreind(mail);
                        System.out.println(result);
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.println(user.acceptRequest());
                        break;
                    case 3:
                        user.upgradePremuim();
                        break;
                    case 4:
                        user.logOut();
                }
            } while (choice != 4);
            
        }
    }
    static Scanner input = new Scanner(System.in);
    static UserController user = new UserController();
    
    public static void main(String[] args){
        String first;
        String last;
        String address;
        String bitrh;
        String email;
        String password;
        String gender;
        int gen;
        int choice = 0;
        do {
            System.out.println("ــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ(Social Network)ـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
            System.out.println("\n1-Sign up\n2-Log in\n3-Exite");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    input.nextLine();
                    System.out.print("First Name:");
                    first = input.nextLine();
                    System.out.print("Last Name:");
                    last = input.nextLine();
                    System.out.print("Address:");
                    address = input.nextLine();
                    System.out.print("Birth Date:");
                    bitrh = input.nextLine();
                    System.out.print("1-Male  2-Female");
                    gen = input.nextInt();
                    while (gen < 1 && gen > 2) {
                        System.out.println("Enter Correct Choice:");
                        gen = input.nextInt();
                    }
                    if (gen == 1) {
                        gender = "Male";
                    } else {
                        gender = "Female";
                    }
                    System.out.print("Email:");
                    input.nextLine();
                    email = input.nextLine();
                    System.out.print("Password:");
                    password = input.nextLine();
                    signUp(gender, first, last, address, bitrh, email, password);
                    logIn(email, password);
                    break;
                case 2:
                    input.nextLine();
                    System.out.print("Enter Email:");
                    email = input.nextLine();
                    System.out.print("Enter Password:");
                    password = input.nextLine();
                    logIn(email, password);
                    break;
                case 3:
                    System.exit(0);
            }
        } while (choice != 3);
////        String firs="Abdo";
        ////        String last="Adel";
        ////        String email="abdulrhmanadel60@gmail.com";
        ////        String password="123456";
        ////        boolean gender=false;
        ////        UserController user=new UserController();
        ////        user.signUp(gender,firs,last,address,bitrh,email,password);
        //        UserController user=new UserController();
        //        //user.logIn("abdulrhmanadel60@gmail.com","123456");
        //        user.logIn("hamza.aboo78@gmail.com","123456");
        //        user.upgradePremuim();
    }
}
