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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch5.i28.Chooser;
import effective.ch5.i28.Chooser2;

/**
 * Prefer lists to arrays
 */
public class Item28 {
    private static Logger log = LoggerFactory.getLogger(Item28.class);

    /**
     * Covariance is bad in this context
     */
    private static void arrayCovariance() {
        // covariance: Long[] is-a Object[]
        Object[] objectArray = new Long[1];

        try {
            objectArray[0] = "I don't fit in";
        } catch (ArrayStoreException ex) {
            log.error("It's a Long array!", ex);
        }
    }

    private static void collectionToArray() {
        Chooser<String> chooser = new Chooser<>(List.of("A", "B", "C"));
        log.info("Randomly picked: {}", chooser.choose());
    }

    private static void collectionsOnly() {
        Chooser2<String> chooser = new Chooser2<>(List.of("A", "B", "C"));
        log.info("Randomly picked: {}", chooser.choose());
    }

    private static void listInvariance() {
        // Type mismatch: cannot convert from ArrayList<Long> to List<Object>
        // List<Object> objectList = new ArrayList<Long>();
        // objectList.add("I don't fit in");
    }

    public static void main(String[] args) {
        log.trace("Enter");

        arrayCovariance();
        listInvariance();

        collectionToArray();
        collectionsOnly();

        log.trace("Exit");
    }
}
