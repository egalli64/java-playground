/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 6: Enums and Annotations
 */
package effective.ch6.i34;

/**
 * An enum with data and behavior
 */
public enum Planet {
    MERCURY(3.302e+23, 2.439e6), VENUS(4.869e+24, 6.052e6), EARTH(5.975e+24, 6.378e6), MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7), SATURN(5.685e+26, 6.027e7), URANUS(8.683e+25, 2.556e7), NEPTUNE(1.024e+26, 2.477e7);

    /** In kilograms */
    private final double mass;
    /** In meters */
    private final double radius;
    /** In m / s^2 */
    private final double surfaceGravity;
    /** Universal gravitational constant in m^3 / kg s^2 */
    private static final double G = 6.67300E-11;

    /**
     * Constructor - implicitly private
     * 
     * @param mass
     * @param radius
     */
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    /**
     * From F = ma
     */
    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }
}
