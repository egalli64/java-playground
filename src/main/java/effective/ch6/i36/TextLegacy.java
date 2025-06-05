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

/**
 * Legacy use of bit field enumeration constants
 */
public class TextLegacy {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    /**
     * @param styles Bitwise OR of zero or more STYLE_ constants
     */
    public void applyStyles(int styles) {
        System.out.print("Styling text: [ ");
        if ((styles & STYLE_BOLD) != 0) {
            System.out.print("bold ");
        }
        if ((styles & STYLE_ITALIC) != 0) {
            System.out.print("italic ");
        }
        if ((styles & STYLE_UNDERLINE) != 0) {
            System.out.print("underline ");
        }
        if ((styles & STYLE_STRIKETHROUGH) != 0) {
            System.out.print("strikethrough ");
        }
        System.out.println("]");
    }
}
