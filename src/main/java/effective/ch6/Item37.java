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

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch6.i37.Plant;

/**
 * Use EnumMap instead of ordinal indexing
 * <p>
 * Ordinal is meant for internal use, not for carrying information
 */
public class Item37 {
    private static Logger log = LoggerFactory.getLogger(Item37.class);

    private static void mappingEnumToData() {
        Plant[] garden = new Plant[] { //
                new Plant("A", Plant.LifeCycle.ANNUAL), //
                new Plant("B", Plant.LifeCycle.BIENNIAL), //
                new Plant("C", Plant.LifeCycle.PERENNIAL), //
                new Plant("D", Plant.LifeCycle.ANNUAL), //
                new Plant("E", Plant.LifeCycle.BIENNIAL), //
                new Plant("F", Plant.LifeCycle.PERENNIAL), //
        };
        System.out.println("The plants: " + Arrays.toString(garden));

        // Using an EnumMap to associate data with an enum
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

        for (Plant p : garden) {
            plantsByLifeCycle.get(p.getLifeCycle()).add(p);
        }

        System.out.println("Plants by life cycle: " + plantsByLifeCycle);
    }

    public static void main(String[] args) {
        log.trace("Enter");

        mappingEnumToData();

        log.trace("Exit");
    }
}
