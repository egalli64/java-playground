/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 3: Methods Common to All Objects
 */
package effective.ch3.i10;

import java.util.Objects;

/**
 * To fix the problem, make the ColorPoint a composition of Color and Point
 */
public class ColorPointFix {
    private final Point point;
    private final Color color;

    public ColorPointFix(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point point() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ColorPointFix other) //
                ? other.point.equals(point) && other.color.equals(color)
                : false;
    }

    @Override
    public String toString() {
        return "{" + color + " " + point + "}";
    }

    // ... and more
}
