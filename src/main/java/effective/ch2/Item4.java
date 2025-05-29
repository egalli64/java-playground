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

import effective.ch2.i4.UtilityClass;

/**
 * Enforce non-instantiability with a private constructor
 */
public class Item4 {
    private static Logger log = LoggerFactory.getLogger(Item4.class);

    public static void main(String[] args) {
        log.trace("Enter");

        // compile, but it doesn't make sense
        // new UtilityClassSloppy().aUsefulMethod();

        // won't compile: The constructor UtilityClass() is not visible
//        new UtilityClass();

        UtilityClass.aUsefulMethod();

        log.trace("Exit");
    }
}
