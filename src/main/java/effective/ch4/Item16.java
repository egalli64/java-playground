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

import effective.ch4.i16.Point3D;

/**
 * In public classes, use accessor methods, not public fields
 * <p>
 * Make each class or member as inaccessible as possible
 */
public class Item16 {
    private static Logger log = LoggerFactory.getLogger(Item16.class);

    private static void publicFields() {
        // no encapsulation!
        Point point = new Point();
        point.x = 42.24;
        point.y = 23.32;
        log.info("Accessing the point fields: x={}, y={}", point.x, point.y);
    }

    private static void encapsulatedFields() {
        Point2 point = new Point2(42.24, 23.32);
        log.info("Accessing the point fields: x={}, y={}", point.getX(), point.getY());
    }

    /**
     * Being the fields constant, there is no encapsulation violation. Still, many
     * ones don't like it
     */
    private static void constantFields() {
        Point3D point = new Point3D(1.0, 2.0, 3.0);
        log.info("Accessing the point fields: x={}, y={}, z={}", point.x, point.y, point.z);
    }

    public static void main(String[] args) {
        log.trace("Enter");

        publicFields();
        encapsulatedFields();
        constantFields();

        log.trace("Exit");
    }
}

/**
 * A super-simple DTO
 * <p>
 * Being package-protected, public field could be considered acceptable
 */
class Point {
    public double x;
    public double y;
}

/**
 * A JavaBean-style DTO
 * <p>
 * The improved robustness is required, when the class is public (see record for
 * immutability and reducing the boilerplate code)
 */
class Point2 {
    private double x;
    private double y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
