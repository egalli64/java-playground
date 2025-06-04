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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Code is simpler and more robust when there is not mix between arrays and
 * collections
 */
public class Chooser2<T> {
    private final List<T> items;

    public Chooser2(Collection<T> choices) {
        items = new ArrayList<>(choices);
    }

    public Object choose() {
        int index = ThreadLocalRandom.current().nextInt(items.size());
        return items.get(index);
    }
}
