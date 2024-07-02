package domain;

import interfaces.WordReader;
import interfaces.WordWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Manager {
    //This class should be responsible for managing the dictionary operations.
    //It should have methods for loading the dictionary from a data source (e.g., file), saving the dictionary, and performing operations like
    //searching, adding, and removing words. Utilize the WordReader and WordWriter classes to read and write word entries to and from the data source.
    private Map<String, Word> dictionary;
    private WordReader wordReader;
    private WordWriter wordWriter;


    public Manager(Map<String, Word> dictionary, WordReader wordReader, WordWriter wordWriter) {
        this.dictionary = dictionary;
        this.wordReader = wordReader;
        this.wordWriter = wordWriter;
    }
//    First constructor.
//    initializes with an existing dictionary
//    Map<String, Word> dictionary: map that holds words and their corresponding “Word objects.
//    WordReader/WordWriter objects implement their interfaces.
//            the ‘this’ keyword is used to differentiate between class fields & constructor parameters when names are the same.

    public Manager(WordReader wordReader, WordWriter wordWriter) {
        this(new HashMap<>(), wordReader, wordWriter);
    }
//    Second constructor. Overloaded
//    calls on first constructor utilizing constructor chaining.
//    creates empty hashmap object that as passed as the dictionary parameter to the first constructor.


    //MENU 1: Method to find a word, we want to get a true/false return on if the word exists.
    public Word findWord(String requestedWord) {
        return dictionary.get(requestedWord);
    }

//    Method Signature:
//    public: needs to be accessed outside of Manager class
//    Word: returns an object type of word
//    findWord: name of the method
//‘String requestedWord’: parameter. represents the word that the caller wants to find within the dictionary.
//
//    Method Body:
//    dictionary:
//            ‘Map<String, Word>’ field of the Manager class.
//    stores the words and their corresponding ‘Word’ objects.
//.get(requestedWord):
//            .get method is call on the dictionary map with the requestedWord as the argument.
//    retrieaves the value associated with the specified key from the map.


    //MENU 2: Method to find words by definition.

    public List<Word> findByDefinition(String comboOfLetters) {
        return dictionary.values().stream()
                .filter(word -> word.getDefinition().contains(comboOfLetters))
                .collect(Collectors.toList());
    }

    //List<Word> indicates that it returns a list of Word objects.
    //
    //Method Body:
    //dictionary.values().stream(): initiates a stream from the collection of ‘Word’ objects in the
    //            dictionary. the ‘values()’ method of the map returns a collection view of the values
    //             contained in the map, which are then converted into the stream for processing.
    //.filter(word -> word.get definition().contains(comboOfLetters))
    //             ‘filter’ operation selects only those Word objects that contain the called upon combo of letter. The lambda expression checks if the getDefinition() method of a word object returns a string that contains the comboOfLetters.
    //.collect(Collectors.toList())
    //             gathers the filtered word objects into a list.


    //MENU 3: method to find words beginning with a specific letter. We want to get a list of words from the results.
    public List<String> firstLetter(String prefix) {
        return dictionary.keySet().stream()
                .filter(word -> word.startsWith(prefix))
                .collect(Collectors.toList());
    }

    //List<String>  returns a list of Strings
    //dictionary.keySet().stream()
    //      initiates a stream for the set of keys in the dictionary map.
    //      the 'keySet() returns a set view of the keys, which are the words themselves. Stream processes them
    //.filter(word -> word.startsWith(prefix))
    //      filter selects only those words that start with the requested first letter (intermediate stream operation)
    //      lambda checks if true
    //.collect(Collectors.toList())
    //      gathers the filtered words into a list and accumulates the elements into a new list.


    //MENU 4: Method to find words ending with a specific letter. We want to get a list of words from the results.
    public List<String> lastLetter(String suffix) {
        return dictionary.keySet().stream()
                .filter(word -> word.endsWith(suffix))
                .collect(Collectors.toList());
    }

    //List<String> returns a list of Strings
    //dictionary.keySet().Stream()
    //      initiates a stream for the set of keys in the dictionary map.
    //      the 'keySet()' returns a set view of the keys contained in the map, which are the words themselves. Stream Processes them
    //.filter(word -> word.endswith(suffix))
    //      filter selects only those words that end with the requested last letter (intermediate stream operation)
    //      lambda checks if true
    //.collect(Collectors.toList())
    //      gathers the filtered words into a list and accumulates the elements into a new list.

    //MENU 5: Method to find words containing a specific string of letters. We want a list of words from the results.
    public List<String> wordContains(String comboOfLetters) {
            return dictionary.keySet().stream()
                    .filter(word -> word.contains(comboOfLetters))
                    .collect(Collectors.toList());
    }
    //works similarly to 3 & 4


    //MENU 6: METHOD TO ADD A WORD
    public boolean addWord(Word word) {
        return dictionary.put(word.getWord(), word) == null && wordWriter.writeWord(word);
    }
    //Method Signature:
    //    boolean specifies whether the word was successfully added
    //Method Body:
    //  .put(word.getWord(), word)
    //      .put method is called on the dictionary map with 2 arguments, word.getWord() and word
    //      checks if the word already exists.
    //  == null checks the returns value given by .put(word.getWord()) to avoid duplications.
    //  && wordWriter.writeWord(word)
    //      calls the writeWord method on the WordWriter object and passing word as argument.
    //      responsible for writing the word to a file.

    //MENU 7: METHOD TO DELETE A WORD
    public boolean byeWord(String word) {
        if (!dictionary.containsKey(word)) {
            return false;
        }
        return dictionary.remove(word) != null && wordWriter.overwriteWords(dictionary.values().stream().toList());
    }

    //Method Signature:
    //    boolean specifies whether the word was successfully added
    //Method Body
    //  if (!dictionary.containsKey(word))
    //      dictionary.containsKey(word) attempts to remove the entry.
    //      ! checks if the return value of the remove method is not null. indicates that word was removed.
    //  wordWriter.overwriteWords(dictionary.values().stream().toList())
    //      calls overwriteWords method passing a list of the remaining Word objects in the dictionary as an argument.
    //      values() methods returns a collection view of the values. (intermediate stream operator)
    //      toList() converted to a list.
    //responsible for updating the data source to reflect that the word has been removed.


    public void load() throws IOException {
        wordReader.readAllWords().forEach(word -> dictionary.put(word.getWord(), word));
    }
    //This method ensures that the dictionary is initialized or updated with the contents of the data source,
    // making the words available for dictionary operations such as searching, adding, and removing


    //Method Signature:
    //  void
    //      specifies that it doesn't return any value
    //      represents the path to the data sources that contains word entries (dictionary.txt)
    //  throws IOException
    //      may throw an exception.
    //Method Body:
    //  .readAllWords()
    //      called from WordReader Interface
    //      expected to read all word entries from the data sources and return them as a stream of word objects.
    //  forEach(word-> dictionary.put(word.getWord(),word))
    //      terminal stream operation
    //      lambda expression retrieves the string of the word from the Word object and adds the word to the dictionary
    //      or updates the value of the word if it already exists within the dictionary.
}
