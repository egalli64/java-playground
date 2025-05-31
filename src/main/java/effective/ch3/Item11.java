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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch3.i10.PhoneNumber;
import effective.ch3.i11.PhoneNumberBad;
import effective.ch3.i11.PhoneNumberFix;

/**
 * Always override hashCode when you override equals
 * <p>
 * If two objects are equals(), they should return the same hashCode() - or the
 * hashing mechanism in classes like HashMap won't work
 */
public class Item11 {
    private static Logger log = LoggerFactory.getLogger(Item11.class);

    /**
     * PhoneNumber from Item 10 is incomplete. It has an equals() override but not a
     * hashCode() one. So it won't work in a hash based data structure.
     */
    private static void noHashCodeOverride() {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        // Two different objects, even if logically equal, have different hash code!
        if (m.get(new PhoneNumber(707, 867, 5309)) == null) {
            log.error("Can't find Jenny!");
        }
    }

    /**
     * The PhoneNumber class used here has a hashCode() override, but it returns
     * always the same value. It works, but it causes the hash based data structures
     * to perform horribly.
     */
    private static void worseHashCodeOverride() {
        Map<PhoneNumberBad, String> m = new HashMap<>();
        m.put(new PhoneNumberBad(707, 867, 5309), "Jenny");
        // Two different objects, even if logically equal, have different hash code!
        if (m.get(new PhoneNumberBad(707, 867, 5309)) != null) {
            log.info("I can find Jenny");
        }
    }

    private static void reasonableHashCodeOverride() {
        Map<PhoneNumberFix, String> m = new HashMap<>();
        m.put(new PhoneNumberFix(707, 867, 5309), "Jenny");
        // Two different objects, even if logically equal, have different hash code!
        if (m.get(new PhoneNumberFix(707, 867, 5309)) != null) {
            log.info("I can find Jenny (usually, fast!)");
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        noHashCodeOverride();
        worseHashCodeOverride();
        reasonableHashCodeOverride();

        log.trace("Exit");
    }
}
