/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch4.i17.Complex;

/**
 * Minimize mutability
 */
public class Item17 {
    private static Logger log = LoggerFactory.getLogger(Item17.class);

    public static void main(String[] args) {
        log.trace("Enter");

        Complex c1 = new Complex(1, 2);
        Complex c2 = new Complex(2, 3);
        log.info("Two complex values: {}, {}", c1, c2);

        Complex c3 = c1.plus(c2);
        log.info("Adding up two complex values: {}", c3);

        log.trace("Exit");
    }
}
