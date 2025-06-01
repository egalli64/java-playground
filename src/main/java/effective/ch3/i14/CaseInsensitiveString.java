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

import java.util.Objects;

/**
 * Single field Comparable class
 * <p>
 * Usually objects of a given class can be compared only among themselves
 */
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
    private final String str;

    public CaseInsensitiveString(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CaseInsensitiveString other) ? other.str.equalsIgnoreCase(this.str) : false;
    }

    @Override
    public String toString() {
        return "{CISF: " + str + "}";
    }

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(str, cis.str);
    }

    // ... and more
}
