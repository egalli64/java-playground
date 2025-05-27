/**
 * HackerRank Java Strings: Java Anagrams
 * 
 * @author: Manny egalli64@gmail.com
 * @see: http://thisthread.blogspot.com/
 *       https://www.hackerrank.com/challenges/equal/problem
 */
package hackerrank.challenges.strings.java_anagrams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void testGiven1() {
        assertFalse(Solution.isAnagram("anagramm", "marganaa"));
    }

    @Test
    void testGiven2() {
        assertTrue(Solution.isAnagram("Hello", "hello"));
    }

    @Test
    void testExtra() {
        assertTrue(Solution.isAnagram("Abul", "BulA"));
    }
    
    
    
}
