/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch3.i10.PhoneNumber;
import effective.ch3.i12.PhoneNumberFix;

/**
 * Always override toString
 */
public class Item12 {
    private static Logger log = LoggerFactory.getLogger(Item12.class);

    public static void main(String[] args) {
        log.trace("Enter");

        // PhoneNumber in package 10 was incomplete, no toString() override
        PhoneNumber pn = new PhoneNumber(1, 2, 3);
        log.warn("Disappoining: {}", pn);

        // Fixed with a proper override
        PhoneNumberFix pnx = new PhoneNumberFix(1, 2, 3);
        log.info("Satisfactory: {}", pnx);

        log.trace("Exit");
    }
}
