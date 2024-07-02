package domain;

import java.util.Scanner;

public class Utilities {
    private static final Scanner scanner = new Scanner(System.in);

    //creates single instance of the Scanner class. Cannot be changed and is accessible only within Utilities class.

    public static int getUserMenuOption() {
        int userSelection = scanner.nextInt();
        scanner.nextLine();
        return userSelection;
    }

    //Method used to read which menu option the user has selected.

    public static String getUserTextInput() {
        return scanner.nextLine();
    }

    //Method used to read a line of input from the reader. i.e. a string for a defition, a word requested etc.

}


//this is "helper clas"
//
//facilitates user interation with the Menu class by handling user input.
//2 functionalities:
//      Getting user menu option:
//          reads as an integer input from user
//          corresponds to selection from Menu options
//      Getting User Text Input:
//          reads a line of text input from the user
//          gathers necessary information from the user to perform the requested operation.
//
//cleans up logic within the Menu class - separation of concerns so that Menu ca focus on handling menu option logic.