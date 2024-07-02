package interfaces;

import domain.Word;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//outlines usage for any class that wants to implement WordReader class.

public interface WordReader {
    List<Word> readAllWords() throws IOException;
}

//reads all word entries from dictionary.txt and returns them as list of objects.

//allows for different implementations of WordReader without changing rest of the application's code.
