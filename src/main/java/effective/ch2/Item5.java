/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch2.i5.Lexicon;
import effective.ch2.i5.SpellCheckerDI;
import effective.ch2.i5.SpellCheckerSingleton;
import effective.ch2.i5.SpellCheckerUtility;

/**
 * Prefer dependency injection to hard-wiring resources
 */
public class Item5 {
    private static Logger log = LoggerFactory.getLogger(Item5.class);

    public static void main(String[] args) {
        log.trace("Enter");

        log.info("Utility class");
        System.out.println(SpellCheckerUtility.isValid("Java"));
        System.out.println(SpellCheckerUtility.suggestions("Ja"));

        log.info("Singleton");
        System.out.println(SpellCheckerSingleton.INSTANCE.isValid("Java"));
        System.out.println(SpellCheckerSingleton.INSTANCE.suggestions("Ja"));

        log.info("Dependency Injection");
        SpellCheckerDI sc = new SpellCheckerDI(new Lexicon());
        System.out.println(sc.isValid("Java"));
        System.out.println(sc.suggestions("Ja"));

        log.trace("Exit");
    }
}
