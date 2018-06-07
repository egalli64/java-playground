/**
 * HackerRank Java Strings: Java String Tokens
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-string-tokens/problem
 *
 * Split the input against " !,?._'@",
 *  output the number of resulting (non empty) tokens, and each of them on a new line
 */
package hackerrank.challenges.strings.java_string_tokens;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();        
        scanner.close();

        List<String> tokens = Arrays.stream(input.split("[ !,?._'@]+"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        System.out.println(tokens.size());
        tokens.forEach(token -> { System.out.println(token); });
    }
}
