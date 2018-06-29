/**
 * HackerRank - Java - Data Structures - Java Hashset
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-hashset/problem
 *
 * Push pairs of strings in a set, count them each time
 */
package hackerrank.challenges.dataStructures.java_hashset;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int t = scanner.nextInt();
			String[] pair_left = new String[t];
			String[] pair_right = new String[t];

			for (int i = 0; i < t; i++) {
				pair_left[i] = scanner.next();
				pair_right[i] = scanner.next();
			}

			Set<String> keys = new HashSet<>(t);
			for (int i = 0; i < t; i++) {
				keys.add(pair_left[i] + '_' + pair_right[i]);
				System.out.println(keys.size());
	        }
		}
	}
}
