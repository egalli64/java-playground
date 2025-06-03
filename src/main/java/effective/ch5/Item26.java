/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch5.i26.LegacyRawCollectionUser;

/**
 * Don’t use raw types
 */
public class Item26 {
    private static Logger log = LoggerFactory.getLogger(Item26.class);

    /**
     * Do not use raw collections (if you can)
     */
    private static void legacyRawCollection() {
        LegacyRawCollectionUser lu = new LegacyRawCollectionUser();
        log.warn("A raw collection (legacy code): {}", lu);

        try {
            lu.doSomething();
        } catch (ClassCastException ex) {
            log.error("Non-stamp detected in a stamp collection!", ex);
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        legacyRawCollection();

        log.trace("Exit");
    }
}
