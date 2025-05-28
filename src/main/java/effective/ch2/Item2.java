/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import effective.ch2.i2.NutritionFacts;
import effective.ch2.i2.NutritionFactsBean;
import effective.ch2.i2.NutritionFactsTelescopic;

/**
 * Consider a builder when faced with many constructor parameters
 */
public class Item2 {
    private static Logger log = LoggerFactory.getLogger(Item2.class);

    public static void main(String[] args) {
        // telescoping constructor pattern: complex and lacking readability
        NutritionFactsTelescopic teleCola = new NutritionFactsTelescopic(240, 8, 100, 0, 35, 27);
        log.info("Tele Cola: {}", teleCola);

        // Java Bean pattern: requires mutability, allows inconsistency
        NutritionFactsBean beanCola = new NutritionFactsBean();
        beanCola.setServingSize(240);
        beanCola.setServings(8);
        beanCola.setCalories(100);
        beanCola.setSodium(35);
        beanCola.setCarbohydrate(27);
        log.info("Bean Cola: {}", beanCola);

        // Builder pattern: the built object is immutable,
        // the building pipeline is explicit
        NutritionFacts buildCola = new NutritionFacts.Builder(240, 8) // (1) mandatory arguments
                .calories(100).sodium(35).carbohydrate(27) // (2) optional arguments
                .build(); // (3) freeze
        log.info("Build Cola: {}", buildCola);
    }
}
