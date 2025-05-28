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

import effective.ch2.i2.Pizza;
import effective.ch2.i2.Pizza.Topping;
import effective.ch2.i2.PizzaCalzone;
import effective.ch2.i2.PizzaNewYork;
import effective.ch2.i2.PizzaNewYork.Size;

/**
 * Consider a builder when faced with many constructor parameters
 * <p>
 * Builder pattern for class hierarchies
 */
public class Item2b {
    private static Logger log = LoggerFactory.getLogger(Item2b.class);

    public static void main(String[] args) {
        Pizza ny = new PizzaNewYork.Builder(Size.SMALL) //
                .addTopping(Topping.SAUSAGE).addTopping(Topping.ONION) //
                .build();
        log.info("Pizza New York: {}", ny);

        Pizza calzone = new PizzaCalzone.Builder().addTopping(Topping.HAM).sauceInside().build();
        log.info("Calzone: {}", calzone);
    }
}
