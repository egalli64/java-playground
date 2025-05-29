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

import effective.ch2.i6.RomanNumerals;

/**
 * Avoid creating unnecessary objects
 */
public class Item6 {
    private static Logger log = LoggerFactory.getLogger(Item6.class);

    /**
     * Strings have their own custom memory management (String pool)
     */
    private static void stringCreation() {
        // DON'T DO THIS!
        String s1 = new String("bikini");
        System.out.println("String s1 id: " + System.identityHashCode(s1));

        String s2 = new String("bikini");
        System.out.println("String s2 id: " + System.identityHashCode(s2));

        // Do this instead
        String t1 = "bikini";
        System.out.println("String t1 id: " + System.identityHashCode(t1));

        String t2 = "bikini";
        System.out.println("String t2 id: " + System.identityHashCode(t2));
    }

    private static void booleanCreation() {
        // Don't do this
        @SuppressWarnings("removal")
        Boolean b1 = new Boolean("true");
        System.out.println("Boolean b1 id: " + System.identityHashCode(b1));

        @SuppressWarnings("removal")
        Boolean b2 = new Boolean("true");
        System.out.println("Boolean b2 id: " + System.identityHashCode(b2));

        // Do this instead
        Boolean b3 = Boolean.valueOf("true");
        System.out.println("Boolean b3 id: " + System.identityHashCode(b3));

        Boolean b4 = Boolean.valueOf("true");
        System.out.println("Boolean b4 id: " + System.identityHashCode(b4));
    }

    private static long sloppySum() {
        log.trace("Start");
        Long sum = 0L;
        for (int i = 1; i <= 10_000_000; i++)
            sum += i;
        log.trace("End");
        return sum;
    }

    private static long sum() {
        log.trace("Start");
        long sum = 0L;
        for (int i = 1; i <= 10_000_000; i++)
            sum += i;
        log.trace("End");
        return sum;
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // do not create strings by new
        stringCreation();

        // prefer static factory methods, when available
        booleanCreation();

        // cache expensive objects
        System.out.println(RomanNumerals.isRomanNumeral("MMXXV"));
        System.out.println(RomanNumerals.isRomanNumeral("MMMCMLXXVIII"));
        System.out.println(RomanNumerals.isRomanNumeral("MCDLXXXXII"));

        // avoid using boxed primitives when not required
        System.out.println(sloppySum());
        System.out.println(sum());

        log.trace("Exit");
    }
}
