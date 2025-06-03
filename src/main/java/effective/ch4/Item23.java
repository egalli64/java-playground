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

import effective.ch4.i23.TaggedFigure;

/**
 * Prefer class hierarchies to tagged classes
 */
public class Item23 {
    private static Logger log = LoggerFactory.getLogger(Item23.class);

    /**
     * tagged classes are verbose, error-prone, and inefficient
     */
    private static void taggedClass() {
        TaggedFigure rectangle = new TaggedFigure(6, 7);
        log.info("The figure is a {} with area {}", rectangle.shape, rectangle.area());

        TaggedFigure circle = new TaggedFigure(3.66);
        log.info("The figure is a {} with area {}", circle.shape, circle.area());
    }

    public static void main(String[] args) {
        log.trace("Enter");

        taggedClass();

        log.trace("Exit");
    }
}
