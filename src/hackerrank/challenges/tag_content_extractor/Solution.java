/**
 * HackerRank Java Strings: Tag Content Extractor
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/tag-content-extractor/problem
 *
 * Print the text inside XML tags found in the input string
 */
package hackerrank.challenges.tag_content_extractor;

import java.util.*;
import java.util.regex.*;

public class Solution {
    static final Pattern pattern = Pattern.compile("<(.+)>([^<]+)</\\1>");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < testCases; i++) {
            String line = scanner.nextLine();
            boolean missing = true;
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(matcher.group(2));
                missing = false;
            }
            if (missing) {
                System.out.println("None");
            }
        }
        scanner.close();
    }
}
