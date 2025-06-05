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

import effective.ch6.i34.Operation;
import effective.ch6.i34.Planet;

/**
 * Use enums instead of int constants
 */
public class Item34 {
    private static Logger log = LoggerFactory.getLogger(Item34.class);

    // 1. The int enum pattern - still seen in legacy code
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    // 2. Constants refactored using enum
    public enum Apple {
        FUJI, PIPPIN, GRANNY_SMITH
    }

    public enum Orange {
        NAVEL, TEMPLE, BLOOD
    }

    public static void main(String[] args) {
        log.trace("Enter");

        // 1. (Ab)using the int enum pattern
        int x = (APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN;
        log.warn("A meaningless value: {}", x);

        // 2. Still meaningless, but it is difficult to make it compilable
//        var y = (Apple.FUJI - Orange.TEMPLE) / (Apple.PIPPIN);
        System.out.println();

        // 3. Using a richer enum
        double earthWeight = 50.0;
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        log.info("Weight on Earth is {}, mass is {}", earthWeight, mass);
        for (Planet planet : Planet.values()) {
            System.out.printf("Weight on %s is %f\n", planet, planet.surfaceWeight(mass));
        }
        System.out.println();

        // 4. An enum with behavior
        for (Operation operation : Operation.values()) {
            System.out.printf("Applying %s gives: %f\n", operation, operation.apply(7, 6));
        }

        log.trace("Exit");
    }
}
