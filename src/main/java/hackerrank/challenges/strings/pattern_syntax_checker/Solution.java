/**
 * HackerRank Java Strings: Pattern Syntax Checker
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/pattern-syntax-checker/problem
 *
 * Check if a string is a valid regex pattern
 */
package hackerrank.challenges.strings.pattern_syntax_checker;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            String pattern = in.nextLine();
            try {
                Pattern.compile(pattern);
                System.out.println("Valid");
            } catch(PatternSyntaxException pse) {
                System.out.println("Invalid");
            }
        }
        in.close();
    }
}
