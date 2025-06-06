/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 7: Lambdas and Streams
 */
package effective.ch7;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prefer method references to lambdas
 */
public class Item43 {
    private static Logger log = LoggerFactory.getLogger(Item43.class);

    private static void lambdaMerge() {
        // given an empty map
        Map<Integer, Integer> map = new HashMap<>();
        log.info("A map: {}", map);

        // call merge() for key 42
        // create entry {42: 1} if key is not found
        // or increase the value by 1 if the key is found
        Integer key = 42;
        for (int i = 0; i < 3; i++) {
            map.merge(key, 1, (count, increase) -> count + increase);
            log.info("Now map is {}", map);
        }
    }

    /**
     * Refactored using the Integer::sum method reference
     */
    private static void methodReferenceMerge() {
        // given an empty map
        Map<Integer, Integer> map = new HashMap<>();
        log.info("A map: {}", map);

        Integer key = 42;
        for (int i = 0; i < 3; i++) {
            map.merge(key, 1, Integer::sum);
            log.info("Now map is {}", map);
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        lambdaMerge();
        methodReferenceMerge();

        log.trace("Exit");
    }
}
