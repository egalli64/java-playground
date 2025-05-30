/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch2.i8.ResourceCleaner;
import effective.ch2.i8.ResourceFinalize;

/**
 * Avoid finalizers and cleaners
 */
public class Item8 {
    private static Logger log = LoggerFactory.getLogger(Item8.class);

    public static void main(String[] args) {
        log.trace("Enter");

        // 1. finalize
        log.info("Finalizer");

        // creating three ResourceFinalize objects, immediately available to GC
        for (int i = 0; i < 3; i++) {
            new ResourceFinalize(i).access();
        }

        log.warn("Maybe the ResourceFinalize objects are finalized, maybe not");

        // 2. clean
        log.info("Cleaner");

        // creating three ResourceCleaner objects, immediately available to GC
        for (int i = 0; i < 3; i++) {
            new ResourceCleaner(i).access();
        }

        log.warn("Maybe the ResourceCleaner objects are cleaned, maybe not");

        log.info("Requesting GC (it could help) ...");
        System.gc();

        log.trace("Exit");
    }
}
