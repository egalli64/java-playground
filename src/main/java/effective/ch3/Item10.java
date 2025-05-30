/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch3.i10.CaseInsensitiveString;
import effective.ch3.i10.CaseInsensitiveStringFix;

/**
 * Obey the general contract when overriding equals
 * <p>
 * A value class usually requires an equals() override - two different objects
 * could be logically equivalent. (Exceptions: classes that uses instance
 * control, as the enumerators)
 */
public class Item10 {
    private static Logger log = LoggerFactory.getLogger(Item10.class);

    // remove the annotation to let Java signal "strange" calls to equals()
    @SuppressWarnings("unlikely-arg-type")
    private static void symmetryViolation() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        if (cis.equals(s) != s.equals(cis)) {
            log.warn("Symmetry violation detected between {} and {}", s, cis);
        }

        if (cis.equals(s)) {
            log.info("{} is equal to {}", cis, s);
        }
        if (!s.equals(cis)) {
            log.warn("{} is not equal to {}", s, cis);
        }
    }

    // remove the annotation to let Java signal "strange" calls to equals()
    @SuppressWarnings("unlikely-arg-type")
    private static void symmetryRespected() {
        CaseInsensitiveStringFix cis = new CaseInsensitiveStringFix("Polish");
        String s = "polish";

        if (cis.equals(s) == s.equals(cis)) {
            log.warn("No symmetry violation between {} and {}", s, cis);
        }

        if (!cis.equals(s)) {
            log.info("{} not equal to {}", cis, s);
        }
        if (!s.equals(cis)) {
            log.warn("{} not equal to {}", s, cis);
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // symmetry must be respected!
        symmetryViolation();
        symmetryRespected();

        log.trace("Exit");
    }
}
