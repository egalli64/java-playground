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
 * Singleton based on public final field
 */
public class ElvisField {
    private static Logger log = LoggerFactory.getLogger(ElvisField.class);

    public static final ElvisField INSTANCE = new ElvisField();

    private ElvisField() {
        log.trace("Instantiate Elvis Field");
    }

    public void leaveTheBuilding() {
        log.info("Elvis Field is leaving the building");
    }
}
