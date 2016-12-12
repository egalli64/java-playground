/**
 * Knapsack.java
 * 
 * Dynamic programming solution to Knapsack problem
 * More info: http://bitingcode.blogspot.com/2016/12/knapsack-with-repetitions.html
 * 
 * @author manny egalli64@gmail.com
 */
package fun;

public class Knapsack {
    /**
     * Knapsack with repetition
     * Based on algorithm discussed in chapter 6 of
     * Algorithms by S. Dasgupta, C.H. Papadimitriou, and U.V. Vazirani
     * 
     * more info: http://bitingcode.blogspot.com/2016/12/knapsack-with-repetitions.html
     * 
     * @param values value associated to each item
     * @param weights weight associated to each item
     * @param weight weight the knapsack could carry
     * @return the heighest value could be put in the knapsack
     */
    public static int withRepetition(int[] values, int[] weights, int weight) {
        assert(values.length == weights.length);
        
        int[] results = new int[weight + 1];
        for(int i = 1; i < results.length; ++i) {
            for(int j = 0; j < weights.length; ++j) {
                if(weights[j] > i)
                    continue;
                int candidate = results[i - weights[j]] + values[j];
                if(candidate > results[i])
                    results[i] = candidate;
            }
        }

        return results[results.length - 1];
    }
}
