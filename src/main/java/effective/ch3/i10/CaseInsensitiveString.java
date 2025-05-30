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
 * !!! Broken - violates symmetry !!!
 */
public final class CaseInsensitiveString {
    private final String str;

    public CaseInsensitiveString(String str) {
        this.str = Objects.requireNonNull(str);
    }

    /**
     * !!! Broken - violates symmetry !!!
     */
    @Override
    public boolean equals(Object obj) {
        // pattern matching for switch (Java 21)
        return switch (obj) {
        case CaseInsensitiveString cis -> str.equalsIgnoreCase(cis.str);
        case String t -> str.equalsIgnoreCase(t);
        default -> false;
        };
    }

    @Override
    public String toString() {
        return "{CIS: " + str + "}";
    }

    // ... and more
}
