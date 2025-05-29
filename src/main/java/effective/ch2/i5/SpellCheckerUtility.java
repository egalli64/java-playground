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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Using a utility class here is inflexible & untestable
 */
public class SpellCheckerUtility {
    private static Logger log = LoggerFactory.getLogger(SpellCheckerUtility.class);

    private static final Lexicon dictionary = new Lexicon();

    /**
     * Suppress default constructor for non-instantiability
     */
    private SpellCheckerUtility() {
        log.error("Unexpected");
    }

    public static boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public static List<String> suggestions(String typo) {
        return dictionary.close(typo);
    }
}
