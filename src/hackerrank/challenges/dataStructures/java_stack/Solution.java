/**
 * HackerRank - Java - Data Structures - Java Stack
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-stack/problem
 *
 * Check if parentheses are balanced
 */
package hackerrank.challenges.dataStructures.java_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {
	private static Deque<Character> stack = new ArrayDeque<>();

	private static boolean isBalanced(String input) {
		stack.clear();
		int len = input.length();
		if (len % 2 != 0) {
			return false;
		}

		for (int i = 0; i < len; i++) {
			char c = input.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}

				char matcher = c == ')' ? '(' : c == ']' ? '[' : c == '}' ? '{' : '\0';
				if (stack.pop() != matcher) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String input = scanner.next();
				System.out.println(isBalanced(input));
			}
		}
	}
}
