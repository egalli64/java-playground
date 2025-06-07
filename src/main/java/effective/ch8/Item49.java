/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 8: Methods
 */
package effective.ch8;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Check parameters for validity
 */
public class Item49 {
    private static Logger log = LoggerFactory.getLogger(Item49.class);

    /**
     * A method expecting a positive value
     * 
     * @param value a positive integer
     * @throws IllegalArgumentException when a non-positive argument is passed
     */
    private static void positiveExpected(int value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("%d is not an acceptable argument", value));
        }

        System.out.println("You passed me " + value);
    }

    /**
     * Reference should always be checked against null
     * 
     * @param value
     */
    private static void nonNullExpected(Integer value) {
        Objects.requireNonNull(value, "The value argument should not be null");

        System.out.println("You passed me " + value);
    }

    public static void main(String[] args) {
        log.trace("Enter");

        try {
            positiveExpected(0);
        } catch (IllegalArgumentException ex) {
            log.error("Bad arg", ex);
        }

        try {
            nonNullExpected(null);
        } catch (NullPointerException ex) {
            log.error("Bad arg", ex);
        }

        log.trace("Exit");
    }
}
