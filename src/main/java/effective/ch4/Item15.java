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

import effective.ch4.i15.DefensiveStrategy;
import effective.ch4.i15.DefensiveStrategy2;
import effective.ch4.i15.SecurityHole;
import effective.ch4.i15.Thing;

/**
 * Minimize the accessibility of classes and members
 * <p>
 * Make each class or member as inaccessible as possible
 */
public class Item15 {
    private static Logger log = LoggerFactory.getLogger(Item15.class);

    /**
     * Very bad
     */
    private static void arrayPublicConstantUnsafe() {
        log.info("The values are {}", Arrays.toString(SecurityHole.VALUES));

        SecurityHole.VALUES[1] = new Thing(42);
        log.warn("Now they are {}", Arrays.toString(SecurityHole.VALUES));
    }

    /**
     * If I want to allow local changes to the array
     */
    private static void defensiveAccessor() {
        log.info("The values are {}", Arrays.toString(DefensiveStrategy.values()));

        Thing[] values = DefensiveStrategy.values();
        values[1] = new Thing(42);
        System.out.println("I can change my local copy " + Arrays.toString(values));

        log.info("The original values are still {}", Arrays.toString(DefensiveStrategy.values()));
    }

    /**
     * If I like to stress that the values should be used without changes
     */
    private static void defensiveAccessor2() {
        log.info("The values are {}", DefensiveStrategy2.VALUES);

        try {
            DefensiveStrategy2.VALUES.set(1, new Thing(42));
        } catch (UnsupportedOperationException ex) {
            log.warn("An unmodifiable list can't be modified!");
        }

        log.info("The original values are still {}", Arrays.toString(DefensiveStrategy.values()));
    }

    public static void main(String[] args) {
        log.trace("Enter");

        arrayPublicConstantUnsafe();
        defensiveAccessor();
        defensiveAccessor2();

        log.trace("Exit");
    }
}
