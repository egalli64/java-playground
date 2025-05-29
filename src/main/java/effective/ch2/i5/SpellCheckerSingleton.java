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
 * Using a singleton here is inflexible & untestable
 */
public class SpellCheckerSingleton {
    private static Logger log = LoggerFactory.getLogger(SpellCheckerSingleton.class);

    private static final Lexicon dictionary = new Lexicon();
    public static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();

    private SpellCheckerSingleton() {
        log.trace("Whatever is needed");
    }

    public boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        return dictionary.close(typo);
    }
}
