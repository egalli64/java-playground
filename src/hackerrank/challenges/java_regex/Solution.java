/**
 * HackerRank Java Strings: Java Regex
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-regex/problem
 *
 * 
 * Write a regular expression for this definition of IP address:
 * 
 * String in the form "A.B.C.D"
 * where the value of A, B, C, and D may range from 0 to 255
 * Leading zeros are allowed
 * The length of A, B, C, or D can't be greater than 3
 */
package hackerrank.challenges.java_regex;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }
        in.close();
    }
}

class MyRegex {
    static private String block = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";

    String pattern = "(" + block + "\\.){3}" + block;
}
