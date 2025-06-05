package effective.ch6.i36;

/**
 * Bit field enumeration constants - OBSOLETE!
 */
public class TextLegacy {
    public static final int STYLE_BOLD = 1 << 0; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

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
