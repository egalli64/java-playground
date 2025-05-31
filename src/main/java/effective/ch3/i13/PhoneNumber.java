/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3.i13;

/**
 * A clonable class - No reference to mutable state
 * <p>
 * Just for demonstration purpose. Being an immutable class, the cloning of its
 * object is just a waste.
 */
public final class PhoneNumber implements Cloneable {
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
        if (obj == this) {
            return true;
        }

        return (obj instanceof PhoneNumber pn) //
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
     * Notice the covariant return type and the mandatory the exception management,
     * due to the super.clone() signature.
     */
    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }
}
