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
 * The resource should be managed in a try-with-resources, or explicitly closed
 * by close(). Cleaner is kept as fallback mechanism.
 */
public class ResourceCloser implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(ResourceCloser.class);

    private static final Cleaner cleaner = Cleaner.create();

    private final int id;
    private final State state;
    private final Cleaner.Cleanable cleanable;

    /**
     * Required by Cleaner
     */
    private static class State implements Runnable {
        private final int id;
        private boolean cleaned = false;

        State(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            clean();
        }

        synchronized void clean() {
            if (!cleaned) {
                log.info("Close/cleaning resource {}", id);
                cleaned = true;
            }
        }
    }

    public ResourceCloser(int id) {
        this.id = id;
        this.state = new State(id);
        // Fallback strategy, in case close() isn't called
        this.cleanable = cleaner.register(this, state);
    }

    public void access() {
        log.info("Accessing {}", id);
    }

    @Override
    public void close() {
        // Perform cleanup
        state.clean();
        // No need for further use of Cleaner
        cleanable.clean();
    }
}
