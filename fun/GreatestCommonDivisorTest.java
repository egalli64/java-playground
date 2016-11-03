/**
 * GreatestCommonDivisorTest.java
 * 
 * Playing with the Greatest Common Divisor
 * 
 * @author manny egalli64@gmail.com
 */
package fun;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.core.Is.*;
import static fun.GreatestCommonDivisor.*;

/**
 * @see: http://bitingcode.blogspot.com/2016/11/greatest-common-divisor.html
 */
public class GreatestCommonDivisorTest {
    @Test
    public void testNaive17657() {
        assertThat(naive(28_851_538, 1183019), is(17657));
    }    
    
    @Test
    public void testNaiveMarkov69() {
        assertThat(naive(94_418_953, 94_418_954), is(1));
    }

    @Test
    public void testEuclid17657() {
        assertThat(euclid(28_851_538, 1183019), is(17657));
    }    
    
    @Test
    public void testEuclidMarkov69() {
        assertThat(euclid(94_418_953, 94_418_954), is(1));
    }
}

