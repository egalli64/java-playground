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

/**
 * !!! BAD The non-instantiability is not enforced !!!
 */
public class UtilityClassSloppy {
    private static Logger log = LoggerFactory.getLogger(UtilityClassSloppy.class);

    public static void aUsefulMethod() {
        log.info("Something useful");
    }
}
