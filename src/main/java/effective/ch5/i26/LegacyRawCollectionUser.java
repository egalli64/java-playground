/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5.i26;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Before Java 5, collections were raw (no parameter type)
 */
public class LegacyRawCollectionUser {
    // warning from the compiler, if possible make the class parametric
    @SuppressWarnings("rawtypes")
    // the name of the variable signal that I should put only stamps in it
    // !!! legacy code only (if it is not possible to refactor it) !!!
    private final Collection stamps;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public LegacyRawCollectionUser() {
        // creating a raw collection (with a warning from the compiler)
        // inserting correctly a stamp in it
        stamps = new HashSet(List.of(new Stamp()));

        // !!! this is a mistake, a coin where stamps are expected !!!
        // but the compiler could only warn about the unchecked call
        stamps.add(new Coin());
    }

    @SuppressWarnings("rawtypes")
    public void doSomething() {
        // !!! Assuming legacy code - don't use raw iterators in modern Java code !!!
        for (Iterator i = stamps.iterator(); i.hasNext();) {
            // throws ClassCastException when a non-stamp is detected
            Stamp stamp = (Stamp) i.next();
            System.out.println(stamp);
        }
    }

    @Override
    public String toString() {
        return "{" + stamps + "}";
    }
}
