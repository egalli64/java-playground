/**
 * HackerRank Java Data Structures: Dynamic Array
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/dynamic-array/problem
 */
package hackerrank.challenges.dataStructures.dynamic_array;

import java.util.List;

import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.Is.*;

class SolutionTest {

    @Test
    void testGiven() {
        List<int[]> queries = List.of( //
                new int[] { 1, 0, 5 }, //
                new int[] { 1, 1, 7 }, //
                new int[] { 1, 0, 3 }, //
                new int[] { 2, 1, 0 }, //
                new int[] { 2, 1, 1 });

        List<Integer> result = Solution.dynamicArray(2, queries);
        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(7));
        assertThat(result.get(1), is(3));
    }

}
