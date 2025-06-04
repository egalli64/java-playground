/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5.i29;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Refactoring a class from Object based to generic
 * <p>
 * Initial code in Item 7: the object references are converted to generic ones
 */
public class Stack<T> {
    // we want to keep an array here, this is a low level data structure
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * We must ensure that elements are T objects, unfortunately the compiler can't
     * do it for us
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        // Generic and array don't get along well
        // A possible solution is casting and ignore warning
        elements = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        T result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
