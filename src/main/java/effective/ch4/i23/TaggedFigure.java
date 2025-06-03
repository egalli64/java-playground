/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 4: Classes and Interfaces
 */
package effective.ch4.i23;

/**
 * !!! A tagged class - prefer using a class hierarchy instead !!!
 * <p>
 * Suspiciously close to the design on a union in C language
 */
public class TaggedFigure {
    enum Shape {
        RECTANGLE, CIRCLE
    };

    // the object tag
    public final Shape shape;

    // fields for RECTANGLE
    private double length;
    private double width;

    // field for CIRCLE
    private double radius;

    /**
     * Constructor for circle
     */
    public TaggedFigure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    /**
     * Constructor for rectangle
     */
    public TaggedFigure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    public double area() {
        return switch (shape) {
        case RECTANGLE -> length * width;
        case CIRCLE -> Math.PI * (radius * radius);
        };
    }
}
