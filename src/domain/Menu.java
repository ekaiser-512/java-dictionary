package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//code to display menu options and handle user selection.
//the switch function works as a breaker panel of sorts. Flipping based on the case selected to match the
//corresponding method under the Manager class and then coming back to the Menu method for output based on
//manager results.

//Menu class is meant to handle user input and output and is a way to provide a separation of concerns
//for the Manager class.

public class Menu {
    static Manager manager;
    List<String> searchHistory = new ArrayList<>();

    public Menu(Manager manager) {
        this.manager = manager; //assigning passed manager to the field.
    }

    //designed to facilitate various dictionary operations.

    public void runMenu() {

                int userSelection = 0;
                do {
                    printMenu();
                    userSelection = Utilities.getUserMenuOption();

                    switch (userSelection) {  //calling my methods
                        case 1:
                            findWord();
                            break;
                        case 2:
                            findByDefinition();
                            break;
                        case 3:
                            firstLetter();
                            break;
                        case 4:
                            lastLetter();
                            break;
                        case 5:
                            wordContains();
                            break;
                        case 6:
                            addWord();
                            break;
                        case 7:
                            byeWord();
                            break;
                        case 8:
                            displayHistory();
                            break;
                        case 9:
                            creator();
                            break;
                        case 10:
                            exitMenu();
                            break;
                        default:
                            System.out.println("invalid option, please try again");
                    }waitForEnter();
                } while (userSelection !=10); //program runs until prompted to exit
        }

    //MENU OPTION 1
    private void findWord() { //takes scanner variable, Scanner
        System.out.println("Enter word you'd like to find");
        String requestedWord = Utilities.getUserTextInput();
        Word found = manager.findWord(requestedWord);
        if (found != null) {
            System.out.println("Word found: " + found.getWord());
            System.out.println("Part of speech: " + found.getPartOfSpeech());
            System.out.println("Definition: " + found.getDefinition());
            System.out.println("Example usage: " + found.getExampleUsage());
            searchHistory.add("Word found: " + requestedWord);
        } else {
            System.out.println("Word not found");
        }
    }

    //MENU OPTION 2
    private void findByDefinition() {
        System.out.println("provide definition below");
        String comboOfLetters = Utilities.getUserTextInput();
        List<Word> foundWords = manager.findByDefinition(comboOfLetters);
        System.out.println("Word associated with definition provided: ");
        foundWords.forEach(System.out::println);
        searchHistory.add("Words found by definition: " + comboOfLetters);
    }

    //MENU OPTION 3
    private void firstLetter() { //takes scanner variable, Scanner
        System.out.println("Enter first letter of word(s) you're looking for");
        String prefix = Utilities.getUserTextInput();
        List<String> foundWords = manager.firstLetter(prefix);
        if (foundWords.isEmpty()) {
            System.out.println("no words starting with " + prefix);
        } else {
            System.out.println("words with first letter " + prefix + ": " + foundWords);
        }
        searchHistory.add("Words that start with: " + prefix);
    }

    //MENU OPTION 4
    private void lastLetter() { //takes scanner variable, Scanner
        System.out.println("Enter last letter of word(s) you're looking for");
        String suffix = Utilities.getUserTextInput();
        List<String> foundWords = manager.lastLetter(suffix);
        if (foundWords.isEmpty()) {
            System.out.println("no words ending with " + suffix);
        } else {
            System.out.println("words with last letter " + suffix + ": " + foundWords);
        }
        searchHistory.add("Words that end with: " + suffix);
    }

    //MENU OPTION 5
    private void wordContains() { //takes scanner variable, Scanner
        System.out.println("Enter part of the word(s) you're looking for");
        String comboOfLetters = Utilities.getUserTextInput();
        List<String> foundWords = manager.wordContains(comboOfLetters);
        if (foundWords.isEmpty()) {
            System.out.println("no with " + comboOfLetters);
        } else {
            System.out.println("words containing " + "'" + comboOfLetters+ "'" + ": " + foundWords);
        }
        searchHistory.add("string contains: " + comboOfLetters);
    }

    //MENU OPTION 6
    public void addWord() {
        System.out.println("Enter new word: ");
        String word = Utilities.getUserTextInput();

        System.out.println("Enter part of speech for new word: ");
        String partOfSpeech = Utilities.getUserTextInput();

        System.out.println("Enter definition for new word: ");
        String definition = Utilities.getUserTextInput();

        System.out.println("Enter example usage for new word: ");
        String exampleUsage = Utilities.getUserTextInput();

        Word addedWord = new Word(word, partOfSpeech, definition, exampleUsage);
        if (manager.addWord(addedWord)) {
            System.out.println("Your new word has been added successfully");
        } else {
            System.out.println("failure to create word");
        }
    }

    //MENU OPTION 7 DELETE WORD
    public void byeWord() {
        System.out.println("Enter the word you would like to delete");
        String word = Utilities.getUserTextInput();
        boolean wordDeleted = manager.byeWord(word);
        if (wordDeleted) {
            System.out.println(word + " has been deleted from the dictionary");
        } else {
            System.out.println("word not found.");
        }
    }

    //MENU OPTION 8 HISTORY
    public void displayHistory() {
        if (searchHistory.isEmpty()) {
            System.out.println("No search history available");
        } else {
            System.out.println("Search History:");
            searchHistory.forEach(System.out::println);
        }
        System.out.println("Press Enter to return to Menu");
        Utilities.getUserTextInput();
    }

    //MENU OPTION 9
    public void creator () {
        System.out.println("created and managed by Emily Kaiser - ekaiser@indeed.com.");
        System.out.println(" Don't contact her though, she's too anxious to respond");
        System.out.println();
        System.out.println("press Enter to return to main menu");
    }

    //MENU OPTION 10
    private void exitMenu() {
        System.out.println("Exiting the Dictionary App");
        searchHistory.clear();
        System.exit(0);
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        Utilities.getUserTextInput();
    }

        public static void printMenu() {
                System.out.println("****************************************************");
                System.out.println("Welcome\n");
                System.out.println( "1. Find a word(s)");
                System.out.println("2. Find words by definition");
                System.out.println("3. Find all words that start with -");
                System.out.println("4. Find all words that end with -");
                System.out.println("5. Find all words containing -");
                System.out.println("6. Add a word");
                System.out.println("7. Delete a word");
                System.out.println("8. History");
                System.out.println("9. Creator");
                System.out.println("10. Exit");
                System.out.println("****************************************************");

                System.out.println("please choose number from menu");
        }
}
