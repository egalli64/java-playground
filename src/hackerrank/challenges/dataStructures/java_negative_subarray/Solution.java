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

import java.util.Scanner;

public class Solution {
    public static int negatives(int[] data) {
        int result = 0;

        for(int i = 0; i < data.length; i++) {
            int current = 0;
            for(int j = i; j < data.length; j++) {
                current += data[j];
                if(current < 0)
                    result += 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] data = null;
        try(Scanner scanner = new Scanner(System.in)) {
            int sz = scanner.nextInt();
            data = new int[sz];
            
            for(int i = 0; i < sz; i++) {
                data[i] = scanner.nextInt();
            }
        }
                
        System.out.println(negatives(data));
    }
}
