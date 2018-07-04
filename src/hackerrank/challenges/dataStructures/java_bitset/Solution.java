/**
 * HackerRank - Java - Data Structures - Java BitSet
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-bitset/problem
 *
 * n = size of two bitsets
 * m = number of operations performed on them
    AND set set 
    OR set set
    XOR set set
    FLIP set index
    SET set index

input:
5 4
AND 1 2
SET 1 4
FLIP 2 2
OR 2 1

output number of set bits for the two collections:
0 0  -- {0, 0, 0, 0, 0} {0, 0, 0, 0, 0}
1 0  -- {0, 0, 0, 0, 1} {0, 0, 0, 0, 0}
1 1  -- {0, 0, 0, 0, 1} {0, 0, 1, 0, 0}
1 2  -- {0, 0, 0, 0, 1} {0, 0, 1, 0, 1}
 */
package hackerrank.challenges.dataStructures.java_bitset;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static List<List<Integer>> solution(InputStream is) {
        List<List<Integer>> results = new ArrayList<>();

        try (Scanner scanner = new Scanner(is)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            BitSet[] bs = new BitSet[] { new BitSet(n), new BitSet(n) };

            for (int i = 0; i < m; i++) {
                String operation = scanner.next();
                int lhs = scanner.nextInt();
                int rhs = scanner.nextInt();

                switch (operation) {
                case "AND":
                    bs[lhs - 1].and(bs[rhs - 1]);
                    break;
                case "OR":
                    bs[lhs - 1].or(bs[rhs - 1]);
                    break;
                case "XOR":
                    bs[lhs - 1].xor(bs[rhs - 1]);
                    break;
                case "FLIP":
                    bs[lhs - 1].flip(rhs);
                    break;
                case "SET":
                    bs[lhs - 1].set(rhs);
                    break;
                default:
                    // an exception should be probably thrown
                }

                // this code should run as Java 8!
                // results.add(List.of(bs[0].cardinality(), bs[1].cardinality()));
                results.add(Arrays.asList(bs[0].cardinality(), bs[1].cardinality()));
            }
        }

        return results;
    }

    public static void main(String[] args) {
        solution(System.in).stream().forEach(x -> {
            System.out.println(x.get(0) + " " + x.get(1));
        });
    }
}
