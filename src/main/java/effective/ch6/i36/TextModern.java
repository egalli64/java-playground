/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 6: Enums and Annotations
 */
package effective.ch6.i36;

import java.util.Set;

/**
 * Refactored to support EnumSet
 */
public class TextModern {
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    /**
     * @param styles any Set, but EnumSet is clearly best
     */
    public void applyStyles(Set<Style> styles) {
        System.out.print("Styling text: [ ");
        if (styles.contains(Style.BOLD)) {
            System.out.print("bold ");
        }
        if (styles.contains(Style.ITALIC)) {
            System.out.print("italic ");
        }
        if (styles.contains(Style.UNDERLINE)) {
            System.out.print("underline ");
        }
        if (styles.contains(Style.STRIKETHROUGH)) {
            System.out.print("strikethrough ");
        }
        System.out.println("]");
    }
}
