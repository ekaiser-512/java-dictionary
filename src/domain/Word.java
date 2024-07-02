package domain;

public class Word {
    //This class should represent a word entry in the dictionary. It encapsulates the word, definition, part of speech, and example usage.
    //Provide appropriate constructors, getters, and setters for these attributes.

    private String word;
    private String partOfSpeech;
    private String definition;
    private String exampleUsage;

    //declares the private attributes of the 'Word' class.
    //Each object will have its own set of these attributes to make a dictionary entry.

    public Word(String word, String partOfSpeech, String definition, String exampleUsage) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.exampleUsage = exampleUsage;
    }

    //constructor that initializes a new Word object with the provided attributes.
    //this keyword used to differentiate between class attributes and constructor parameters.

    //GETTERS
    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExampleUsage() {
        return exampleUsage;
    }

    //SETTERS
    public void setWord(String word) {
        this.word = word;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setExampleUsage(String exampleUsage) {
        this.exampleUsage = exampleUsage;
    }

    //allow other classes to access and modify attributes of a word

    @Override
    public String toString() {
        return  "word = " + word +
                "\n partOfSpeech = " + partOfSpeech +
                "\n definition = " + definition +
                "\n exampleUsage = " + exampleUsage;
    }
    //provides a string representation of the 'Word' object for printing a word entry requested.

    public String fileString() {
        return "\n" + word + " | " + partOfSpeech + " | " + definition + " | " + exampleUsage;
    }
    //provides string representation of the 'Word' object formatted for file storage or manipulation.

}
