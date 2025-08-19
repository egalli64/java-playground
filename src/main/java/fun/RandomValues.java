/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 */
package fun;

import java.util.concurrent.ThreadLocalRandom;

public class RandomValues {
    public static void main(String[] args) {
        // six values in [6..9]
        int[] values = ThreadLocalRandom.current().ints(6, 6, 10).toArray();

        // print the values bottom up
        for (int i = values.length - 1; i >= 0; i--) {
            System.out.println(values[i]);
        }
    }
}
