/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3.i11;

/**
 * A class with the the worst possible legal hashCode implementation
 * <p>
 * !!! DO NOT DO THIS IN REAL CODE !!!
 */
public final class PhoneNumberBad {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumberBad(int areaCode, int prefix, int lineNum) {
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
        if (obj == this) {
            return true;
        }

        if (obj instanceof PhoneNumberBad pn) {
            return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
        } else {
            return false;
        }
    }

    /**
     * The worst possible legal hashCode implementation
     * <p>
     * !!! DO NOT DO THIS IN REAL CODE !!!
     */
    @Override
    public int hashCode() {
        return 42;
    }
}
