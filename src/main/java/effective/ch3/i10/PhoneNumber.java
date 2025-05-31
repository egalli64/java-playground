/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3.i10;

/**
 * A class with a typical equals method
 */
public final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. identity check
        if (obj == this) {
            return true;
        }

        // 2. check the parameter type and 3. cast it when required
        if (obj instanceof PhoneNumber pn) {
            // 4. check each "significant" field
            return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
        } else {
            return false;
        }
    }

    // ... and more
    // hashCode() MUST be redefined with equals()
}
