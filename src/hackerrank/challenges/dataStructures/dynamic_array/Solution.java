/**
 * HackerRank Java Data Structures: Dynamic Array
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/dynamic-array/problem
 *
 * Apply two different operation on a list of lists, accordingly to passed parameters 
 */
package hackerrank.challenges.dataStructures.dynamic_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static List<Integer> dynamicArray(int n, List<int[]> queries) {
        List<Integer> results = new ArrayList<>();
        int result = 0;

        List<List<Integer>> buffers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buffers.add(new ArrayList<Integer>());
        }

        for (int[] query: queries) {
            List<Integer> current = buffers.get((query[1] ^ result) % n);
            if (query[0] == 1) {
                current.add(query[2]);
            } else { // if (query[0] == 2)
                result = current.get(query[2] % current.size());
                results.add(result);
            }
        }

        
        return results;
    }
    
    public static void main(String[] args) {
        try(Scanner scan = new Scanner(System.in)) {
            int n = scan.nextInt();
            int counter = scan.nextInt();
            
            List<int[]> values = new ArrayList<>();
            for (int i = 0; i < counter; i++) {
                values.add(new int[] {scan.nextInt(), scan.nextInt(), scan.nextInt()});
            }
            dynamicArray(n, values).forEach(System.out::println);
        }
    }
}
