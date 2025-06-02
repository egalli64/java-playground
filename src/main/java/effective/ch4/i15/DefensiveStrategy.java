/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4.i15;

/**
 * Only constants should be public in a class and, being constants, should be
 * static.
 * <p>
 * But an array is mutable, so being final doesn't make it constant! So, make it
 * private, and provide a defensive accessor method that makes a copy of it.
 */
public class DefensiveStrategy {
    private static final Thing[] PRIVATE_VALUES = { new Thing(1), new Thing(2), new Thing(3) };

    public static final Thing[] values() {
        return PRIVATE_VALUES.clone();
    }
}
