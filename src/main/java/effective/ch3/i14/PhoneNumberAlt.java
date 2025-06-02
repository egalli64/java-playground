/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3.i14;

import java.util.Comparator;

/**
 * Multiple primitive field Comparable class
 * <p>
 * Alternative modern approach to comparison
 */
public final class PhoneNumberAlt implements Comparable<PhoneNumberAlt> {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    private static final Comparator<PhoneNumberAlt> COMPARATOR = //
            Comparator.comparingInt((PhoneNumberAlt pn) -> pn.areaCode) //
                    .thenComparingInt((PhoneNumberAlt pn) -> pn.prefix) //
                    .thenComparingInt((PhoneNumberAlt pn) -> pn.lineNum);

    public PhoneNumberAlt(int areaCode, int prefix, int lineNum) {
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

        return (obj instanceof PhoneNumberAlt pn) //
                ? pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode
                : false;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    /**
     * Cascading style compareTo implementation
     */
    @Override
    public int compareTo(PhoneNumberAlt other) {
        return COMPARATOR.compare(this, other);
    }

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }
}
