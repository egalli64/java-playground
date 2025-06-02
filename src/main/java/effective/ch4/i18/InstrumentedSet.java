/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4.i18;

import java.util.Collection;
import java.util.Set;

/**
 * Wrapper class - uses composition in place of inheritance
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    /**
     * Inject the passed set into the object
     */
    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
