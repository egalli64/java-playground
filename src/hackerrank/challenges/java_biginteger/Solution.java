/**
 * HackerRank Java BigNumber: Java BigInteger
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-biginteger/problem
 *
 * get two strings from stdin, convert them in (big!) integer, output their sum and product
 */
package hackerrank.challenges.java_biginteger;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BigInteger first = new BigInteger(scanner.nextLine());
        BigInteger second = new BigInteger(scanner.nextLine());

        System.out.println(first.add(second));
        System.out.println(first.multiply(second));

        scanner.close();
    }
}
