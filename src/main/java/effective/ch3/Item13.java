/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch3.i13.PhoneNumber;
import effective.ch3.i13.Stack;

/**
 * Override clone judiciously
 * <p>
 * When possible, copy constructor or copy factory are preferred approaches.
 */
public class Item13 {
    private static Logger log = LoggerFactory.getLogger(Item13.class);

    private static void noRefToMutableState() {
        PhoneNumber pn1 = new PhoneNumber(1, 2, 3);
        PhoneNumber pn2 = pn1.clone();

        if (pn1 != pn2 && pn1.getClass() == pn2.getClass() && pn1.equals(pn2)) {
            log.info("Not mandatory but expected");
        } else {
            log.warn("Unexpected!");
        }
    }

    private static void withRefToMutableState() {
        Stack st1 = new Stack();
        st1.push(42);
        Stack st2 = st1.clone();

        if (st1 != st2 && st1.getClass() == st2.getClass() && st1.equals(st2)) {
            log.info("Not mandatory but expected");
        } else {
            log.warn("Unexpected!");
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        noRefToMutableState();
        withRefToMutableState();

        log.trace("Exit");
    }
}
