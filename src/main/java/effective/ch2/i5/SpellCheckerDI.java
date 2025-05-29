/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i5;

import java.util.List;

/**
 * Using Dependency Injection for flexibility and testability
 */
public class SpellCheckerDI {
    private final Lexicon dictionary;

    public SpellCheckerDI(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        return dictionary.close(typo);
    }
}
