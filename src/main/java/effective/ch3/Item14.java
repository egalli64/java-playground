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

import effective.ch3.i14.CaseInsensitiveString;
import effective.ch3.i14.PhoneNumber;
import effective.ch3.i14.PhoneNumberAlt;

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

        // equals compare value and scale: here it returns false
        System.out.printf("Is %s equal to %s (equals)? %b\n", x, y, x.equals(y));
        // compareTo does not consider the scale: here it returns 0
        System.out.printf("Is %s equal to %s (compareTo)? %b\n", x, y, x.compareTo(y) == 0); // true

        if (x.equals(y) != (x.compareTo(y) == 0)) {
            log.warn("Inconsistency between equals and compareTo for BigDecimal!");
        }

        // TreeSet uses compareTo for comparisons, so it considers x and y equal
        Set<BigDecimal> set = new TreeSet<>(List.of(x, y));
        if (set.size() != 2) {
            log.warn("Beware of BigDecimal inconsistency!");
        }
    }

    /**
     * User defined class with its own comparator (single field)
     */
    private static void singleFieldComparable() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Java");
        CaseInsensitiveString cis2 = new CaseInsensitiveString("java");

        if (cis.compareTo(cis2) == 0 && cis.equals(cis2)) {
            log.info("This class natural comparator works as expected (single field)");
        }
    }

    /**
     * User defined class with its own comparator (multiple field)
     */
    private static void multiFieldComparable() {
        PhoneNumber pn = new PhoneNumber(1, 2, 3);
        PhoneNumber pn2 = new PhoneNumber(1, 2, 3);

        if (pn.compareTo(pn2) == 0 && pn.equals(pn2)) {
            log.info("This class natural comparator works as expected (multi field)");
        }
    }

    /**
     * User defined class with its own comparator (alternative multiple field)
     */
    private static void multiFieldComparableAlt() {
        PhoneNumberAlt pn = new PhoneNumberAlt(1, 2, 3);
        PhoneNumberAlt pn2 = new PhoneNumberAlt(1, 2, 3);

        if (pn.compareTo(pn2) == 0 && pn.equals(pn2)) {
            log.info("This class natural comparator works as expected (alt multi field)");
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        comparableString();
        inconsistentBigDecimal();
        singleFieldComparable();
        multiFieldComparable();
        multiFieldComparableAlt();

        log.trace("Exit");
    }
}
