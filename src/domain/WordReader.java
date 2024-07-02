package domain;

//implements WordReader interface.
//provides specific guidelines for reading word entries from the dictionary.txt file.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WordReader implements interfaces.WordReader {

    FileReader reader;

    public WordReader(String filePath) throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    //filePath initializes FileReader to read from the specified file.
    //setup to read contents of dictionary.txt

    @Override
    public List<Word> readAllWords() throws IOException {

        int character;

        StringBuilder content = new StringBuilder();          //concatenates characters read from the file.
        while((character = reader.read()) != -1) {            //reads file character by character
            content.append((char) character);                 //each char read appended to an object
        }
        String string = String.valueOf(content);              //converted to a String object named string

        String[] stringArrayLines = string.split("\\n");//split into array of strings using split method.

        return Arrays.stream(stringArrayLines)                //stringArrayLines is coverted into a stream
        .map(WordReader::lineToWord)                          //map method used to convert each line to an object
        .filter(Objects::nonNull)                             //filter method used to remove any null entries made by lineToWord
        .collect(Collectors.toList());                        //collect word objects into a list returned by method.
    }
    //Overrides the method in WordReader Interface. Reads entire content of file into a Stringbuilder.
    //splits it into lines
    //process each line to create Word objects.
    //returns a list of 'Word' objects created from the file's content.


    public static Word lineToWord(String line) {
        String[] fields = line.split(" \\| ");
        if(fields.length < 4) {                               //makes sure that all fields are filled out.
            return null;
        }
        return new Word(fields[0], fields[1], fields[2], fields[3]);
    }
    //helper method
    //takes line from the file, splits it into the specific pieces of a dictionary entry and creates
    //a new Word objects if the object has all four fields.
    //used within the readAllWords method.
    
}
