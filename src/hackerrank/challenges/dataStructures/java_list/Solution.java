/**
 * HackerRank Java Data Structures: Java List
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-list/problem
 *
 * insert/delete integers from a list on demand
 */
package hackerrank.challenges.dataStructures.java_list;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LinkedList<Integer> data = new LinkedList<>();
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                data.add(scanner.nextInt());
            }

            for (int q = scanner.nextInt(); q > 0; q--) {
                if (scanner.next().equals("Insert")) {
                    data.add(scanner.nextInt(), scanner.nextInt());
                } else {
                    data.remove(scanner.nextInt());
                }
            }

            for (Integer value : data) {
                System.out.print(value + " ");
            }
        }
    }
}
