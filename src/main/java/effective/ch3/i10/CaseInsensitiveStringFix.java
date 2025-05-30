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

import java.util.Objects;

/**
 * Fix, removing the String compatibility
 */
public final class CaseInsensitiveStringFix {
    private final String str;

    public CaseInsensitiveStringFix(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CaseInsensitiveStringFix other) ? other.str.equalsIgnoreCase(this.str) : false;
    }

    @Override
    public String toString() {
        return "{CISF: " + str + "}";
    }

    // ... and more
}
