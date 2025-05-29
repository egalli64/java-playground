/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i7;

import java.util.Arrays;
import java.util.EmptyStackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeakingStack {
    private static Logger log = LoggerFactory.getLogger(LeakingStack.class);

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public LeakingStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * Buggy pop
     * 
     * @return reference to the popped object
     */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        // !!! unintentional object retentions !!!
        return elements[--size];
    }

    /**
     * Fix to the buggy pop above
     * 
     * @return reference to the popped object
     */
    public Object popFix() {
        if (size == 0)
            throw new EmptyStackException();

        Object result = elements[--size];
        // Eliminate obsolete reference
        elements[size] = null;
        return result;
    }

    public void checkElements() {
        log.trace("Enter");

        int count = 0;
        for (int i = size; i < elements.length; i++) {
            if (elements[i] != null) {
                log.warn("Zombie element detected in position {}: {}", i, elements[i]);
                count += 1;
            }
        }

        log.trace("Exit with {} warning(s)", count);
    }

    /**
     * Ensure space for at least one more element, roughly doubling the capacity
     * each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
