/**
 * HackerRank Java Data Structures: Java Arraylist
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-arraylist/problem
 *
 * push integers in a bidimensional arraylist then query for them
 */
package hackerrank.challenges.dataStructures.java_arraylist;

import java.util.ArrayList;
import java.util.Scanner;
//import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int nRows = scanner.nextInt();
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] data = new ArrayList[nRows];
            for (int i = 0; i < nRows; i++) {
                int len = scanner.nextInt();
                data[i] = new ArrayList<>(len);
                for (int j = 0; j < len; j++) {
                    data[i].add(scanner.nextInt());
                }

            }

            // Stream.of(data).forEach(System.out::println);

            int nTest = scanner.nextInt();
            for (int test = 0; test < nTest; test++) {
                int i = scanner.nextInt() - 1;
                int j = scanner.nextInt() - 1;

                if (i < data.length && j < data[i].size()) {
                    System.out.println(data[i].get(j));
                } else {
                    System.out.println("ERROR!");
                }
            }
        }
    }
}
