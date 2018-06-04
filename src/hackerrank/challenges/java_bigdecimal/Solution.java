/**
 * HackerRank Java BigNumber: Java BigDecimal
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-bigdecimal/problem
 *
 * sort an array of strings representing (big!) real numbers
 * in descending order (as numbers) than print them in the original format
 */
package hackerrank.challenges.java_bigdecimal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n + 2];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        sc.close();
        
//
        Arrays.sort(s, 0, n, Collections.reverseOrder(
                (lhs, rhs)->new BigDecimal(lhs).compareTo(new BigDecimal(rhs)))
        );
//
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
    }
}
