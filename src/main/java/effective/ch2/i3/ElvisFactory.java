/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton based on static factory method
 */
public class ElvisFactory {
    private static Logger log = LoggerFactory.getLogger(ElvisFactory.class);

    private static final ElvisFactory INSTANCE = new ElvisFactory();

    private ElvisFactory() {
        log.trace("Instantiate Elvis Factory");
    }

    public static ElvisFactory getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        log.info("Elvis Factory is leaving the building");
    }
}
