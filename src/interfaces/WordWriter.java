package interfaces;

import domain.Word;

import java.io.IOException;
import java.util.List;

//outlines usage for any class that wants to implement WordWriter class

public interface WordWriter {
   boolean writeWord(Word word);
   //adds a single Word object to the dictionary.txt
   //returns a boolean indicating success or failure of operation.

   boolean overwriteWords(List<Word> words);
   //overwrites with a list of Word objects.
   //useful for batch updating word entries (would be nice to start doing next)
   //or when entire dictionary needs to be saved.
   //returns boolean indicating success/failure.
}

