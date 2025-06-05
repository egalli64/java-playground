/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 6: Enums and Annotations
 */
package effective.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch6.i36.TextLegacy;

/**
 * Use EnumSet instead of bit fields
 */
public class Item36 {
    private static Logger log = LoggerFactory.getLogger(Item36.class);

    private static void legacyCombinable() {
        TextLegacy text = new TextLegacy();
        for (int i = 0; i < 16; i++) {
            text.applyStyles(i);
        }

        // typical usage
        System.out.print("Bold + underline: ");
        text.applyStyles(TextLegacy.STYLE_BOLD | TextLegacy.STYLE_UNDERLINE);
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // legacy way of managing combinable options
        legacyCombinable();

        log.trace("Exit");
    }
}
