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

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch6.i36.TextLegacy;
import effective.ch6.i36.TextModern;

/**
 * Use EnumSet instead of bit fields
 */
public class Item36 {
    private static Logger log = LoggerFactory.getLogger(Item36.class);

    private static void legacyCombinable() {
        TextLegacy text = new TextLegacy();

        // typical usage
        System.out.print("Classic bold + italic: ");
        text.applyStyles(TextLegacy.STYLE_BOLD | TextLegacy.STYLE_ITALIC);
    }

    private static void modernCombinable() {
        TextModern text = new TextModern();

        // typical usage
        System.out.print("Modern bold + italic: ");
        text.applyStyles(EnumSet.of(TextModern.Style.BOLD, TextModern.Style.ITALIC));
    }

    public static void main(String[] args) {
        log.trace("Enter");

        legacyCombinable();
        modernCombinable();

        log.trace("Exit");
    }
}
