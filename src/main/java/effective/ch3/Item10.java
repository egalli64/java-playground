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
import effective.ch3.i10.Color;
import effective.ch3.i10.ColorPoint;
import effective.ch3.i10.ColorPointFix;
import effective.ch3.i10.PhoneNumber;
import effective.ch3.i10.Point;

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

    // remove the annotation to let the compiler signal "strange" calls to equals()
    @SuppressWarnings("unlikely-arg-type")
    private static void symmetryRespected() {
        CaseInsensitiveStringFix cis = new CaseInsensitiveStringFix("Polish");
        String s = "polish";

        if (cis.equals(s) == s.equals(cis)) {
            log.warn("No symmetry violation between {} and {}", s, cis);
        }

        if (!cis.equals(s)) {
            log.info("{} is not equal to {}", cis, s);
        }
        if (!s.equals(cis)) {
            log.warn("{} is not equal to {}", s, cis);
        }
    }

    private static void transitivityViolation() {
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

        if (p1.equals(p2) != p1.equals(p3) || p1.equals(p2) != p2.equals(p3)) {
            log.warn("Transitivity violation detected among {}, {} and {}", p1, p2, p3);
        }

        if (p1.equals(p2)) {
            log.info("{} equals {}", p1, p2);
        }
        if (p2.equals(p3)) {
            log.info("{} equals {}", p2, p3);
        }
        if (!p1.equals(p3)) {
            log.info("{} is not equal to {}", p1, p3);
        }
    }

    // remove the annotation to let the compiler signal "strange" calls to equals()
    @SuppressWarnings("unlikely-arg-type")
    private static void transitivityRespected() {
        ColorPointFix p1 = new ColorPointFix(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPointFix p3 = new ColorPointFix(1, 2, Color.BLUE);

        if (p1.equals(p2) == p1.equals(p3) && p1.equals(p2) == p2.equals(p3)) {
            log.warn("No transitivity violation detected among {}, {} and {}", p1, p2, p3);
        }

        if (!p1.equals(p2)) {
            log.info("{} is not equal to {}", p1, p2);
        }
        if (!p2.equals(p3)) {
            log.info("{} is not equal to {}", p2, p3);
        }
        if (!p1.equals(p3)) {
            log.info("{} is not equal to {}", p1, p3);
        }
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // symmetry must be respected!
        symmetryViolation();
        symmetryRespected();

        // transitivity must be respected!
        transitivityViolation();
        transitivityRespected();

        // a class with a symmetric, transitive, consistent equals() method
        PhoneNumber pn1 = new PhoneNumber(12, 42, 8998);
        PhoneNumber pn2 = new PhoneNumber(12, 42, 8997);

        if (!pn1.equals(pn2)) {
            log.info("{} is not equal to {}", pn1, pn2);
        }

        log.trace("Exit");
    }
}
