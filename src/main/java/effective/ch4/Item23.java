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

import effective.ch4.i23.Circle;
import effective.ch4.i23.Figure;
import effective.ch4.i23.Rectangle;
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

    private static void classHierarchy() {
        Figure rectangle = new Rectangle(6, 7);
        log.info("This rectangle has area {}", rectangle.area());

        Figure circle = new Circle(3.66);
        log.info("This circle has area {}", circle.area());
    }

    public static void main(String[] args) {
        log.trace("Enter");

        taggedClass();
        classHierarchy();

        log.trace("Exit");
    }
}
