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
 * A broken "immutable" time period class
 * <p>
 * Assume this is a legacy (and broken) class - do not use java.util.Date in
 * modern code
 */
public final class BrokenPeriod {
    private final Date start;
    private final Date end;

    /**
     * @param start the beginning of the period
     * @param end   the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException     if start or end is null
     */
    public BrokenPeriod(Date start, Date end) {
        if (start.compareTo(end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }

    // ... and more stuff
}
