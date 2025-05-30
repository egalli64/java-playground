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

/**
 * Deprecated and unreliable finalizer example
 * <p>
 * !!! Do NOT use in production !!!
 */
public class ResourceFinalize {
    private static Logger log = LoggerFactory.getLogger(ResourceFinalize.class);

    private int id;

    public ResourceFinalize(int id) {
        this.id = id;
    }

    public void access() {
        log.info("Accessing {}", id);
    }

    @SuppressWarnings("removal")
    @Override
    protected void finalize() throws Throwable {
        log.info("Finalizing {}", id);

        super.finalize();
    }
}
