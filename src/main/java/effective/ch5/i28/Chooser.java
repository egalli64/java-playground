/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5.i28;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mixing arrays an collections is not ideal
 */
public class Chooser<T> {
    // if items is generic, T[], the toArray() call doesn't compile
    private final Object[] items;

    /**
     * Converting collection to array is painful.
     */
    public Chooser(Collection<T> choices) {
        // even if items is a T[], there's no way to explain the compiler how to cast
        // the toArray() result to T[], casting here is useless
        items = choices.toArray();
    }

    public Object choose() {
        int index = ThreadLocalRandom.current().nextInt(items.length);
        return items[index];
    }
}
