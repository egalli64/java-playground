/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch4.i18.InstrumentedHashSet;
import effective.ch4.i18.InstrumentedSet;

/**
 * Favor composition over inheritance
 * <p>
 * Inheritance violates encapsulation
 */
public class Item18 {
    private static Logger log = LoggerFactory.getLogger(Item18.class);

    private static void fragileBaseClass() {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("Snap", "Crackle", "Pop"));

        // the derived class should know internal details of the base class to work
        // properly, but those internal details could change in the future
        log.info("Size of set is {}", s.size());
        log.warn("Counter for add gives {}", s.getAddCount());
    }

    private static void compositionWithForwarding() {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
        s.addAll(List.of("Snap", "Crackle", "Pop"));

        // The logic for instrumenting is all in the wrapper
        log.info("Size of set is {}", s.size());
        log.info("Counter for add gives {}", s.getAddCount());
    }

    public static void main(String[] args) {
        log.trace("Enter");

        fragileBaseClass();
        compositionWithForwarding();

        log.trace("Exit");
    }
}
