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

import effective.ch2.i3.ElvisEnum;
import effective.ch2.i3.ElvisFactory;
import effective.ch2.i3.ElvisField;

/**
 * Enforce the singleton property with a private constructor or an enum type
 */
public class Item3 {
    private static Logger log = LoggerFactory.getLogger(Item3.class);

    public static void main(String[] args) {
        log.trace("enter");

        // accessing Elvis as field
        ElvisField.INSTANCE.leaveTheBuilding();

        // accessing Elvis by static factory method
        ElvisFactory.getInstance().leaveTheBuilding();

        // accessing Elvis as enum
        ElvisEnum.INSTANCE.leaveTheBuilding();

        log.trace("exit");
    }
}
