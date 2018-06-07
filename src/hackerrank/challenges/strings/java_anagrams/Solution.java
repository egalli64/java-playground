/**
 * HackerRank Java Strings: Java Anagrams
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/java-anagrams/problem
 *
 * Check if the second passed string is an anagram of the first one
 */
package hackerrank.challenges.strings.java_anagrams;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    static boolean isAnagram(String a, String b) {
        if(a.length() != b.length())
            return false;

        Map<Character, Integer> frequences = new HashMap<>();
        for(int i = 0, len = a.length(); i < len; i++) {
            Character key = Character.toLowerCase(a.charAt(i));
            Integer frequence = frequences.containsKey(key) ? frequences.get(key) : 0;
            frequences.put(key, frequence + 1);
        }

        for(int i = 0, len = b.length(); i < len; i++) {
            Character key = Character.toLowerCase(b.charAt(i));
            Integer frequence = frequences.get(key);
            if(frequence == null || frequence == 0)
                return false;

            frequences.put(key, frequence - 1);
        }
        
        return true;
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
