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

import effective.ch2.i8.ResourceFinalize;

/**
 * Avoid finalizers and cleaners
 */
public class Item8 {
    private static Logger log = LoggerFactory.getLogger(Item8.class);

    public static void main(String[] args) {
        log.trace("Enter");

        log.info("Finalizer");
        for (int i = 0; i < 3; i++) {
            ResourceFinalize x = new ResourceFinalize(i);

            x.access();
        }

        // GC could help
        log.info("GC, please ...");
        System.gc();
        log.warn("Maybe the ResourceFinalize objects are finalized, maybe not");

        log.trace("Exit");
    }
}
