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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prefer lambdas to anonymous classes
 */
public class Item42 {
    private static Logger log = LoggerFactory.getLogger(Item42.class);

    /**
     * Could still be seen in legacy code
     */
    private static void legacySorting(List<String> words) {
        log.info("Before sorting (by length): {}", words);
        Collections.sort(words, new Comparator<String>() {
            public int compare(String left, String right) {
                return Integer.compare(left.length(), right.length());
            }
        });
        log.info("After sorting (by length): {}", words);
    }

    private static void lambdaSorting(List<String> words) {
        log.info("Before sorting (by length): {}", words);
        Collections.sort(words, (left, right) -> Integer.compare(left.length(), right.length()));
        log.info("After sorting (by length): {}", words);
    }

    private static void methodReferenceSorting(List<String> words) {
        log.info("Before sorting (by length): {}", words);
        Collections.sort(words, Comparator.comparingInt(String::length));
        log.info("After sorting (by length): {}", words);
    }

    private static void methodReferenceInstanceSorting(List<String> words) {
        log.info("Before sorting (by length): {}", words);
        words.sort(Comparator.comparingInt(String::length));
        log.info("After sorting (by length): {}", words);
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<>(List.of("A", "BB", "GGG", "DDDD", "FFFFF"));
        log.trace("Enter");

        // 1: classic shuffling a list, then sort it
        Collections.shuffle(data);
        legacySorting(data);

        // 2 - modern plain
        Collections.shuffle(data);
        lambdaSorting(data);

        // 3 - modern compact
        Collections.shuffle(data);
        methodReferenceSorting(data);

        // 4 - modern compact on list instance
        Collections.shuffle(data);
        methodReferenceInstanceSorting(data);

        log.trace("Exit");
    }
}
