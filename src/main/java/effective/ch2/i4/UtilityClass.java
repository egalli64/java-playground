/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityClass {
    private static Logger log = LoggerFactory.getLogger(UtilityClass.class);

    /**
     * Suppress default constructor for non-instantiability
     */
    private UtilityClass() {
        // extra safety check
        throw new AssertionError();
    }

    public static void aUsefulMethod() {
        log.info("Something useful");
    }
}
