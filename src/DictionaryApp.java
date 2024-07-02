import domain.Manager;
import domain.Menu;
import domain.WordReader;
import domain.WordWriter;

import java.io.IOException;

//entry point (Main class) for the application.
//initializes interaction of various parts for application functionality.

public class DictionaryApp {
    public static void main(String[] args) throws IOException {

        String filePath = "./lib/dictionary.txt";               //specifies path to dictionary data file. Where word entries are stored and read from.

        WordReader wordReader = new WordReader(filePath);       //instance of WordReader class created with file path as argument. responsible for reading words from the file.
        WordWriter wordWriter = new WordWriter(filePath);       //instance of WordWriter class create with file path as argument. responsible for writing word entries to the file
                                                                //and allowing application to save changes made.

        Manager manager = new Manager(wordReader, wordWriter);  //initializes Manager class and provides user interface to allow for interaction with dictionary.txt
        manager.load();                                 //load method called to populate dictionary with entries from the file.

        Menu menu = new Menu(manager);                          //interlaces the Menu and Manager classes so that when runMenu method starts, it allows user to perform operations.
        menu.runMenu();
        }
    }

