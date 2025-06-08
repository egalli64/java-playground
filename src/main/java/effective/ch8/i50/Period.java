/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 8: Methods
 */
package effective.ch8.i50;

import java.util.Date;

/**
 * Fix by defensive copy
 * <p>
 * Do not use java.util.Date in modern code
 */
public final class Period {
    private final Date start;
    private final Date end;

    /**
     * @param start the beginning of the period
     * @param end   the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException     if start or end is null
     */
    public Period(Date start, Date end) {
        // defensive copy of dates
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        // use the copies
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(this.start + " after " + this.end);
    }

    /**
     * Fix - make defensive copy of field
     */
    public Date start() {
        return new Date(start.getTime());
    }

    /**
     * Fix - make defensive copy of field
     */
    public Date end() {
        return new Date(end.getTime());
    }

    // ... and more stuff
}
