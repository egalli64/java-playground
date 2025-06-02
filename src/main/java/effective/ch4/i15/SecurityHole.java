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
 * But an array is mutable, so being final doesn't make it constant! (So,
 * actually, using the SNAKE_CASE naming convention is kind of misleading)
 */
public class SecurityHole {
    public static final Thing[] VALUES = { new Thing(1), new Thing(2), new Thing(3) };
}
