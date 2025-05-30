/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Cleaner;

/**
 * Introduced in JDK 9 as finalize() replacement. Still non-deterministic and
 * unreliable.
 * <p>
 * !!! To be used only as last resort !!!
 */
public class ResourceCleaner {
    private static final Logger log = LoggerFactory.getLogger(ResourceCleaner.class);

    private static final Cleaner cleaner = Cleaner.create();

    private final int id;
    private final State state;

    private static class State implements Runnable {
        private final int id;

        State(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            log.info("Cleaning up resource {}", id);
        }
    }

    public ResourceCleaner(int id) {
        this.id = id;
        this.state = new State(id);
        cleaner.register(this, state);
    }

    public void access() {
        log.info("Accessing {}", id);
    }
}
