package domain;

//Implement the WordWriter interface. Handles writing word entries to dictionary.txt

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WordWriter implements interfaces.WordWriter {
    String filePath;

    public WordWriter(String filePath) {
        this.filePath = filePath;
    }

    //constructor. specifies the path to dictionary.txt where entries will be written.

    @Override
    public boolean writeWord(Word word) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(word.fileString());
            writer.close();
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    //implements interface writeWord method. appends single Word object to the end of file.
    //used to get the string representation formatted for the storage.
    //returns true/false based on success of operation.

    @Override
    public boolean overwriteWords(List<Word> words) {
        try {
            FileWriter writer = new FileWriter(filePath);
            words.forEach(word -> {
                try {
                    writer.write(word.fileString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

        //implements interface overwriteWords method.
        //overwrites the file with string representation of Word objects.
        //returns true/false based on success of operation.
    }
}

