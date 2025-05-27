/**
 * Knapsack.java
 * 
 * Dynamic programming solution to Knapsack problem
 * More info: http://bitingcode.blogspot.com/2016/12/knapsack-with-repetitions.html
 *            http://bitingcode.blogspot.com/2016/12/knapsack-without-repetition.html
 * 
 * @author manny egalli64@gmail.com
 */
package fun;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.*;

import org.junit.jupiter.api.Test;

public class KnapsackTest {

    /**
     * Knapsack with repetition Based on algorithm discussed in chapter 6 of
     * Algorithms by S. Dasgupta, C.H. Papadimitriou, and U.V. Vazirani
     * 
     * more info:
     * http://bitingcode.blogspot.com/2016/12/knapsack-with-repetitions.html
     */
    @Test
    public void testWithRepetition() {
        int[] weights = new int[] { 2, 3, 4, 6 };
        int[] values = new int[] { 9, 14, 16, 30 };

        assertThat(Knapsack.withRepetition(values, weights, 10), is(48));
    }

    /**
     * Knapsack without repetition
     * 
     * more info:
     * http://bitingcode.blogspot.com/2016/12/knapsack-without-repetition.html
     */
    @Test
    public void testWithouRepetition() {
        int[] weights = new int[] { 2, 3, 4, 6 };
        int[] values = new int[] { 9, 14, 16, 30 };

        assertThat(Knapsack.withoutRepetition(values, weights, 10), is(46));
    }
}
