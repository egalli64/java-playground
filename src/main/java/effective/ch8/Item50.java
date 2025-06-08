/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 8: Methods
 */
package effective.ch8;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch8.i50.BrokenPeriod;
import effective.ch8.i50.Period;

/**
 * Make defensive copies when needed
 */
public class Item50 {
    private static Logger log = LoggerFactory.getLogger(Item50.class);

    /**
     * First attack the internals of a Period instance
     * <p>
     * Notice that Date::setYear() is deprecated
     */
    @SuppressWarnings("deprecation")
    private static void firstAttackBrokenPeriod() {
        Date start = new Date();
        Date end = new Date();
        BrokenPeriod period = new BrokenPeriod(start, end);
        log.info("End is " + period.end());

        // Modifies internals of p!
        end.setYear(78);

        log.warn("Now the end is " + period.end());
    }

    /**
     * Second attack the internals of a Period instance
     * <p>
     * Notice that Date::setYear() is deprecated
     */
    @SuppressWarnings("deprecation")
    private static void secondAttackBrokenPeriod() {
        Date start = new Date();
        Date end = new Date();
        BrokenPeriod period = new BrokenPeriod(start, end);

        // Modifies internals of p!
        Date periodEnd = period.end();
        log.info("End is " + periodEnd);
        periodEnd.setYear(78);
        log.warn("Now the end is " + period.end());
    }

    @SuppressWarnings("deprecation")
    private static void fixedPeriod() {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);

        // attack (1) does not succeed
        log.info("End is " + period.end());
        end.setYear(78);
        log.info("End is still " + period.end());

        // attack (2) does not succeed
        Date periodEnd = period.end();
        log.info("End is " + periodEnd);
        periodEnd.setYear(78);
        log.warn("End is still " + period.end());
    }

    public static void main(String[] args) {
        log.trace("Enter");

        firstAttackBrokenPeriod();
        secondAttackBrokenPeriod();
        fixedPeriod();

        log.trace("Exit");
    }
}
