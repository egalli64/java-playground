/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 5: Generics
 */
package effective.ch5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch5.i31.StackExt;

/**
 * Use bounded wild-cards to increase API flexibility
 * <p>
 * Following the rule PECS: producer-extends, consumer-super
 */
public class Item31 {
    private static Logger log = LoggerFactory.getLogger(Item31.class);

    private static void inflexiblePushAll() {
        StackExt<Number> numbers = new StackExt<>();
        log.info("An empty stack of numbers: {}", numbers);
        Iterable<Integer> values = List.of(1, 2, 3);
        log.info("An iterable of integers: {}", values);
        // won't compile!
        // numbers.pushAll(values);
    }

    private static void flexiblePushAll() {
        StackExt<Number> numbers = new StackExt<>();
        Iterable<Integer> values = List.of(1, 2, 3);
        numbers.wildPushAll(values);
        log.info("The first value popped is: {}", numbers.pop());
    }

    private static void inflexiblePopAll() {
        // prepare a Stack of Number
        StackExt<Number> numbers = new StackExt<>();
        numbers.wildPushAll(List.of(1, 2, 3));

        // try to pop all the elements
        Collection<Object> objects = new ArrayList<>();
        log.info("An empty collection of objects: {}", objects);
        // won't compile!
        // numbers.popAll(objects);
    }

    private static void flexiblePopAll() {
        // prepare a Stack of Number
        StackExt<Number> numbers = new StackExt<>();
        numbers.wildPushAll(List.of(1, 2, 3));

        // pop all the elements
        Collection<Number> result = new ArrayList<>();
        numbers.wildPopAll(result);
        log.info("A collection of numbers: {}", result);
    }

    /**
     * A more flexible management of parameters
     * <p>
     * This magic can't be used on return type
     */
    public static <T> Set<T> union(Set<? extends T> s1, Set<? extends T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // <T> vs <? extends T>
        inflexiblePushAll();
        flexiblePushAll();

        // <T> vs <? super T>
        inflexiblePopAll();
        flexiblePopAll();

        // combining different types in a method call by <? extends T>
        Set<Integer> integers = Set.of(1, 3, 5);
        Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
        Set<Number> numbers = union(integers, doubles);
        log.info("The resulting union: {}", numbers);

        log.trace("Exit");
    }
}
