/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i2;

import java.util.Objects;

public class PizzaNewYork extends Pizza {
    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public PizzaNewYork build() {
            return new PizzaNewYork(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private PizzaNewYork(Builder builder) {
        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {
        return "{size=" + size + ", toppings=" + toppings + "}";
    }
}
