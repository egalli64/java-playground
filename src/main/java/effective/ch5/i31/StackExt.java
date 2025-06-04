/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5.i31;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * A stack with two push all methods
 */
public class StackExt<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public StackExt() {
        elements = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * inflexible: the elements should be of the base parameter type
     */
    public void pushAll(Iterable<T> src) {
        for (T e : src)
            push(e);
    }

    /**
     * Wild-card type for a parameter that serves as a T producer
     */
    public void wildPushAll(Iterable<? extends T> src) {
        for (T e : src)
            push(e);
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
