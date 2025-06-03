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
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch5.i26.LegacyRawCollectionUser;
import effective.ch5.i26.ParametrizedCollectionUser;

/**
 * Don’t use raw types
 */
public class Item26 {
    private static Logger log = LoggerFactory.getLogger(Item26.class);

    /**
     * Do not use raw collections (if you can)
     */
    private static void legacyRawCollection() {
        LegacyRawCollectionUser lu = new LegacyRawCollectionUser();
        log.warn("A raw collection (legacy code): {}", lu);

        try {
            lu.doSomething();
        } catch (ClassCastException ex) {
            log.error("Non-stamp detected in a stamp collection!", ex);
        }
    }

    private static void refactoredCollection() {
        ParametrizedCollectionUser pu = new ParametrizedCollectionUser();
        log.warn("A parametrized collection: {}", pu);
        pu.doSomething();
    }

    /**
     * !!! Acceptable only as legacy code (that should be refactored) !!!
     * 
     * @param list a raw collection - the compiler warns about its usage
     * @param obj  the object to be added
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void unsafeAdd(List list, Object obj) {
        // the compiler warns about the dangerous call
        list.add(obj);
    }

    /**
     * The compiler is smarter enough to check the arguments
     * <p>
     * package-private to allow commenting out its usage
     */
    static void saferAdd(List<Object> list, Object obj) {
        list.add(obj);
    }

    // Use of raw type for unknown element type - don't do this!
    /**
     * !!! Acceptable only as legacy code (that should be refactored) !!!
     * <p>
     * The parametric type is not relevant for the algorithm
     */
    @SuppressWarnings("rawtypes")
    private static int numElementsInCommonUnsafe(Set left, Set right) {
        int result = 0;

        // the compiler can only issue a warning
//        left.add(42);

        for (Object obj : left) {
            if (right.contains(obj)) {
                result += 1;
            }
        }

        return result;
    }

    /**
     * Refactored for safety
     * <p>
     * Can't add anything (but null) if the parametric type is unknown
     */
    private static int numElementsInCommon(Set<?> left, Set<?> right) {
        int result = 0;

        // won't compile
//        left.add(42);

        for (Object obj : left) {
            if (right.contains(obj)) {
                result += 1;
            }
        }

        return result;
    }

    /**
     * Classic use of instanceof on generic type
     */
    private static void downcasterClassic(Object obj) {
        if (obj instanceof Set) {
            Set<?> set = (Set<?>) obj;
            log.info("A set: {}", set);
        }
    }

    /**
     * Refactored to use pattern matching for instanceof
     */
    private static void downcaster(Object obj) {
        if (obj instanceof Set<?> set) {
            log.info("A set: {}", set);
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        legacyRawCollection();
        refactoredCollection();

        // raw collection vs Object parametrized collection
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        try {
            System.out.println(strings.get(0));
        } catch (ClassCastException ex) {
            log.error("Collection misuse detected at runtime", ex);
        }

        // won't compile
//        saferAdd(strings, Integer.valueOf(42));

        // raw collection vs unbounded wild-card type collection
        Set<Integer> values = Set.of(1, 2, 3);
        Set<Integer> values2 = Set.of(1, 42, 35);

        int x = numElementsInCommonUnsafe(values, values2);
        log.warn("The two sets have {} elements in common", x);

        x = numElementsInCommon(values, values2);
        log.warn("The two sets have {} elements in common", x);

        // cast to generic type
        downcasterClassic(values);
        downcaster(values);

        log.trace("Exit");
    }
}
