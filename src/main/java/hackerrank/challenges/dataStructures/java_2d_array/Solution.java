/**
 * HackerRank Java Data Structures Challenges: Java 2D Array
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-2d-array/problem
 *
 * find the biggest "hourglass" in a matrix 6x6 of values from [-9..9]
 */
package hackerrank.challenges.dataStructures.java_2d_array;

import java.util.Scanner;

public class Solution {
    private final static int SZ = 6;
    private final static int MIN_VAL = -9;
    private final static int HOURGLASS_SZ = 7;
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[][] data = new int[SZ][SZ];
        for(int i = 0; i < SZ; i++)
            for(int j = 0; j < SZ; j++) {
                data[i][j] = scanner.nextInt();
            }
        
        scanner.close();

        int result = MIN_VAL * HOURGLASS_SZ;
        for(int i = 0; i < SZ-2; i++)
            for(int j = 0; j < SZ-2; j++) {
                int candidate = data[i][j] + data[i][j+1] + data[i][j+2] +
                        data[i+1][j+1] + data[i+2][j] + data[i+2][j+1] + data[i+2][j+2];

                if (candidate > result)
                    result = candidate;
            }

        System.out.println(result);
    }
}
