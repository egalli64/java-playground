/**
 * Levenshtein.java
 * 
 * Dynamic programming solution to the classic Change Making problem
 * More info: http://bitingcode.blogspot.com/2016/12/levenshtein-distance.html
 * 
 * @author manny egalli64@gmail.com
 */
package fun;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class LevenshteinTest {

    @Test
    public void testEmpty() {
        Levenshtein lev = new Levenshtein("", "");
        assertThat(lev.getDistance(), is(0));
        assertTrue(lev.equals(new int[][] { { 0 } }));
    }

    @Test
    public void testSingleInsert() {
        Levenshtein lev = new Levenshtein("", "a");
        assertThat(lev.getDistance(), is(1));
        assertTrue(lev.equals(new int[][] { { 0, 1 } }));
    }

    @Test
    public void testSingleDeletion() {
        Levenshtein lev = new Levenshtein("a", "");
        assertThat(lev.getDistance(), is(1));
        assertTrue(lev.equals(new int[][] { { 0 }, { 1 } }));
    }

    @Test
    public void testSingleSubstitution() {
        Levenshtein lev = new Levenshtein("a", "b");
        assertThat(lev.getDistance(), is(1));
        assertTrue(lev.equals(new int[][] { { 0, 1 }, { 1, 1 } }));
    }

    @Test
    public void testKitten() {
        Levenshtein lev = new Levenshtein("kitten", "");
        assertThat(lev.getDistance(), is(6));
        assertTrue(lev.equals(new int[][] { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 } }));
    }

    @Test
    public void testKittenS() {
        Levenshtein lev = new Levenshtein("kitten", "s");
        assertThat(lev.getDistance(), is(6));
        assertTrue(lev.equals(new int[][] {
            { 0, 1 }, 
            { 1, 1 }, 
            { 2, 2 }, 
            { 3, 3 }, 
            { 4, 4 }, 
            { 5, 5 }, 
            { 6, 6 } 
        }));
    }

    @Test
    public void testKittenSitting() {
        Levenshtein lev = new Levenshtein("kitten", "sitting");
        assertThat(lev.getDistance(), is(3));
        assertTrue(lev.equals(new int[][] { 
            { 0, 1, 2, 3, 4, 5, 6, 7 },
            { 1, 1, 2, 3, 4, 5, 6, 7 },
            { 2, 2, 1, 2, 3, 4, 5, 6 },
            { 3, 3, 2, 1, 2, 3, 4, 5 },
            { 4, 4, 3, 2, 1, 2, 3, 4 },
            { 5, 5, 4, 3, 2, 2, 3, 4 },
            { 6, 6, 5, 4, 3, 3, 2, 3 }
        }));
    }

    @Test
    public void testIndustryInterest() {
        Levenshtein lev = new Levenshtein("industry", "interest");
        assertThat(lev.getDistance(), is(6));
        assertTrue(lev.equals(new int[][] { 
            { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, 
            { 1, 0, 1, 2, 3, 4, 5, 6, 7 },
            { 2, 1, 0, 1, 2, 3, 4, 5, 6 }, 
            { 3, 2, 1, 1, 2, 3, 4, 5, 6 },
            { 4, 3, 2, 2, 2, 3, 4, 5, 6 },
            { 5, 4, 3, 3, 3, 3, 4, 4, 5 },
            { 6, 5, 4, 3, 4, 4, 4, 5, 4 },
            { 7, 6, 5, 4, 4, 4, 5, 5, 5 },
            { 8, 7, 6, 5, 5, 5, 5, 6, 6 } 
        }));
    }

    @Test
    public void testHorseRos() {
        Levenshtein lev = new Levenshtein("horse", "ros");
        assertThat(lev.getDistance(), is(3));
        assertTrue(lev.equals(new int[][] { 
            { 0, 1, 2, 3 },
            { 1, 1, 2, 3 },
            { 2, 2, 1, 2 },
            { 3, 2, 2, 2 },
            { 4, 3, 3, 2 },
            { 5, 4, 4, 3 }
        }));
    }
}
