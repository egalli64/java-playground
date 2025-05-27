/**
 * FibonacciTest.java
 * 
 * Having fun with Fibonacci numbers
 * 
 * @author manny egalli64@gmail.com
 */

package fun;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

public class FibonacciTest {
    static final Fibonacci fib = new Fibonacci();

    @Test
    public void testNaive30() {
        assertThat(fib.naive(30), is(832040L));
    }

    @Test
    public void testCaching30() {
        assertThat(fib.caching(30), is(832040L));
    }

    @Test
//    @Ignore
    public void testNaive40() {
        assertThat(fib.naive(40), is(102334155L));
    }

    @Test
    public void testCaching40() {
        assertThat(fib.caching(40), is(102334155L));
    }

    @Test
    public void testLastDigitNaive40() {
        assertThat(fib.lastDigitNaive(40), is(5));
    }

    @Test
    public void testLastDigitNaive331() {
        assertThat(fib.lastDigitNaive(331), is(9));
    }

    @Test
    public void testLastDigitNaive327305() {
        assertThat(fib.lastDigitNaive(327305), is(5));
    }

    @Test
    public void testLastDigitNaive100mio() {
        assertThat(fib.lastDigitNaive(100_000_000), is(5));
    }

    @Test
    public void testLastDigit60_40() {
        assertThat(fib.lastDigit60(40), is(5));
    }

    @Test
    public void testLastDigit60_331() {
        assertThat(fib.lastDigit60(331), is(9));
    }

    @Test
    public void testLastDigit60_327305() {
        assertThat(fib.lastDigit60(327305), is(5));
    }

    @Test
    public void testLastDigit60_100mio() {
        assertThat(fib.lastDigit60(100_000_000), is(5));
    }
}
