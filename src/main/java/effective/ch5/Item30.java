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

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Favor generic methods
 */
public class Item30 {
    private static Logger log = LoggerFactory.getLogger(Item30.class);

    /**
     * Use of raw types is admissible only in legacy code
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Set legacyUnion(Set s1, Set s2) {
        Set result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    /**
     * A generic method
     * <p>
     * Usually all parameters and return value are of the same type, see next item
     * for a more flexible scenario
     */
    public static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        log.trace("Enter");

        Set<Integer> left = Set.of(1, 2, 3);
        Set<Integer> right = Set.of(2, 3, 4);

        // legacy approach, the parameter type is lost
        log.info("Legacy union: {}", legacyUnion(left, right));

        // modern approach
        log.info("Legacy union: {}", union(left, right));

        log.trace("Exit");
    }
}
