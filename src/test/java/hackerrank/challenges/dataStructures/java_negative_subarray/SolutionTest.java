/**
 * HackerRank Java Data Structures Challenges: Java Subarray
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-negative-subarray/problem
 *
 * count all negative subarrays in a given array of integers
 */
package hackerrank.challenges.dataStructures.java_negative_subarray;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void testNegativesProvided() {
        assertEquals(9, Solution.negatives(new int[] {1, -2, 4, -5, 1}));
    }
}
