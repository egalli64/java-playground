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
 * Enum with behavior - split data definition and behavior
 */
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        // given the nature of the switch expression, all the enumerated values should
        // be present, or the code would not compile
        return switch (this) {
        case PLUS -> x + y;
        case MINUS -> x - y;
        case TIMES -> x * y;
        case DIVIDE -> x / y;
        };
    }
}
