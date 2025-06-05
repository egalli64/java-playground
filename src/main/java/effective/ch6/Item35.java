/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 6: Enums and Annotations
 */
package effective.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use instance fields instead of ordinals
 */
public class Item35 {
    private static Logger log = LoggerFactory.getLogger(Item35.class);

    public enum Ensemble {
        SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5), SEXTET(6), SEPTET(7), //
        OCTET(8), DOUBLE_QUARTET(8), NONET(9), DECTET(10), TRIPLE_QUARTET(12);

        private final int numberOfMusicians;

        Ensemble(int size) {
            this.numberOfMusicians = size;
        }

        public int numberOfMusicians() {
            return numberOfMusicians;
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // Ordinal is meant just for structural use of the enumerator
        for (Ensemble cur : Ensemble.values()) {
            System.out.printf("Ordinal of %s is %d\n", cur, cur.ordinal());
        }

        // use a field to carry information
        for (Ensemble cur : Ensemble.values()) {
            System.out.printf("Number of musicians in %s is %d\n", cur, cur.numberOfMusicians());
        }

        log.trace("Exit");
    }
}
