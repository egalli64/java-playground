package effective.ch6.i34;

// Enum type with constant-specific class bodies and data
public enum OperationExt {
    PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/");

    private final String symbol;

    OperationExt(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

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
