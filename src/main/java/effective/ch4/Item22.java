/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch4.i22.LegacyPhysicalConstants;
import effective.ch4.i22.PhysicalOperations;

/**
 * Use interfaces only to define types
 */
public class Item22 {
    private static Logger log = LoggerFactory.getLogger(Item22.class);

    /**
     * Just for legacy code
     */
    private static void useLegacyConstants() {
        log.info("Avogadro's number is {} unit per mole", LegacyPhysicalConstants.AVOGADROS_NUMBER);
        log.info("Boltzmann constant is {} J/K", LegacyPhysicalConstants.BOLTZMANN_CONSTANT);
        log.info("Electron mass is {} kg", LegacyPhysicalConstants.ELECTRON_MASS);
    }

    private static void usePhysicalOperations() {
        log.info("Avogadro's number is {} unit per mole", PhysicalOperations.AVOGADROS_NUMBER);
        log.info("Boltzmann constant is {} J/K", PhysicalOperations.BOLTZMANN_CONSTANT);
        log.info("Electron mass is {} kg", PhysicalOperations.ELECTRON_MASS);
    }

    private static void useEnum() {
        log.trace("See Chapter 6 for enum type");
    }

    public static void main(String[] args) {
        log.trace("Enter");

        useLegacyConstants();
        usePhysicalOperations();
        useEnum();

        log.trace("Exit");
    }
}
