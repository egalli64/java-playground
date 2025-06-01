/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consider implementing Comparable
 */
public class Item14 {
    private static Logger log = LoggerFactory.getLogger(Item14.class);

    /**
     * Being Comparable, the class String has a natural order for its objects
     */
    private static void comparableString() {
        String[] names = { "Tom", "Bob", "Joe" };
        System.out.println("The original string array: " + Arrays.toString(names));

        Arrays.sort(names);
        System.out.println("After natural sorting: " + Arrays.toString(names));

        // All elements inserted into the set must implement the Comparable interface
        Set<String> set = new TreeSet<>(List.of("Tom", "Bob", "Joe", "Tom", "Bob", "Joe"));
        System.out.println("Sorted and singlified by TreeSet: " + set);

        // The only method defined in Comparable is compareTo()
        if ("Bob".compareTo("Tom") < 0) {
            System.out.println("Strings are naturally sorted");
        } else {
            log.error("Unexpected!");
        }
    }

    /**
     * BigDecimal is Comparable, but comparedTo is inconsistent with equals!
     */
    private static void inconsistentBigDecimal() {
        BigDecimal x = new BigDecimal("1.0");
        BigDecimal y = new BigDecimal("1.00");

        // equals compare value and scale: here it gives false
        System.out.printf("Is %s equal to %s (equals)? %b\n", x, y, x.equals(y));
        // compareTo does not consider the scale: here gives 0
        System.out.printf("Is %s equal to %s (compareTo)? %b\n", x, y, x.compareTo(y) == 0); // true

        if (x.equals(y) != (x.compareTo(y) == 0)) {
            log.warn("Inconsistency between equals and compareTo for BigDecimal!");
        }

        // TreeSet use compareTo, so it considers x and y same
        Set<BigDecimal> set = new TreeSet<>(List.of(x, y));
        if (set.size() != 2) {
            log.warn("Beware of BigDecimal inconsistency!");
        }

    }

    public static void main(String[] args) {
        log.trace("Enter");

        comparableString();
        inconsistentBigDecimal();

        log.trace("Exit");
    }
}
