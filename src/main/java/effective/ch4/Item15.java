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

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch4.i15.SecurityHole;
import effective.ch4.i15.Thing;

/**
 * Minimize the accessibility of classes and members
 * <p>
 * Make each class or member as inaccessible as possible
 */
public class Item15 {
    private static Logger log = LoggerFactory.getLogger(Item15.class);

    private static void arrayPublicConstantUnsafe() {
        log.info("The 'const' in SecurityHole is {}", Arrays.toString(SecurityHole.VALUES));

        SecurityHole.VALUES[1] = new Thing(42);
        log.warn("Now it is {}", Arrays.toString(SecurityHole.VALUES));
    }

    public static void main(String[] args) {
        log.trace("Enter");

        arrayPublicConstantUnsafe();

        log.trace("Exit");
    }
}
