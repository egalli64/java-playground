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

public class Lexicon {
    public boolean contains(String word) {
        return word != null && word.equals("Java") ? true : false;
    }

    public List<String> close(String word) {
        return word == null || word.isBlank() ? List.of() : List.of(word.repeat(2), word.repeat(3));
    }
}
