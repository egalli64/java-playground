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

public enum ElvisEnum {
    INSTANCE;

    private static Logger log = LoggerFactory.getLogger(ElvisEnum.class);

    public void leaveTheBuilding() {
        log.info("Elvis Enum is leaving the building");
    }
}
