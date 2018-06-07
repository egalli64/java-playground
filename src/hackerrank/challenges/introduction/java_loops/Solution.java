/**
 * https://www.hackerrank.com/challenges/java-loops
 * HackerRank Java Introduction Java Loops II
 */
package hackerrank.challenges.introduction.java_loops;

import java.util.*;

class Solution {
    public static int[] solution(int a, int b, int n) {
        int[] results = new int[n];
        
        results[0] = a + b;
        for(int i = 1; i < n; i++) {
            b *= 2;
            results[i] = results[i-1] + b; 
        }

        return results;
    }

    public static void main(String[] argh) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            
            int[] results = solution(a, b, n);
            for(int value: results) {
                System.out.printf("%d ", value);
            }
            System.out.println();
        }
        in.close();
    }
}
