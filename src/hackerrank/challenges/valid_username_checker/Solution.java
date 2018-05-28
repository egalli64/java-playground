/**
 * HackerRank Java Strings: Valid Username Regular Expression
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/valid-username-checker/problem
 *
 * Check if a string is a valid username, accordingly to these rules:
 * - size in [8..30]
 * - alphanumeric and underscore
 * - first char alphabetic
 */
package hackerrank.challenges.valid_username_checker;

import java.util.Scanner;

class UsernameValidator {
    public static final String regularExpression = "^[[a-z][A-Z]]\\w{7,29}$";
}

public class Solution {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        while (n-- != 0) {
            String userName = scan.nextLine();

            if (userName.matches(UsernameValidator.regularExpression)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }           
        }
        scan.close();
    }
}
