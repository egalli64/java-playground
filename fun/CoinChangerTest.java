/**
 * CoinChangerTest.java
 * 
 * Dynamic programming solution to the classic Change Making problem
 * More info: http://bitingcode.blogspot.com/2016/12/minimum-number-of-coins-to-make-change.html
 * 
 * @author manny egalli64@gmail.com
 */
package fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fun.CoinChanger;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class CoinChangerTest {
    static final List<Integer> EURO_COPPERS = Arrays.asList(1, 2, 5);
    static final List<Integer> FANCY_COINS = Arrays.asList(1, 5, 10, 20, 25);

    @Ignore
    public void testGreedyFancy40() {
        assertThat(CoinChanger.greedy(40, FANCY_COINS), is(2));
    }

    @Test(expected=AssertionError.class)
    public void testMinNumCoinsBadValue() {
        CoinChanger.minNumCoins(-1, EURO_COPPERS);
    }

    @Test(expected=AssertionError.class)
    public void testMinNumCoinsBadCoinsEmpty() {
        CoinChanger.minNumCoins(100, new ArrayList<>());
    }

    @Test(expected=AssertionError.class)
    public void testMinNumCoinsBadCoinsZero() {
        CoinChanger.minNumCoins(10, Arrays.asList(0));
    }

    @Test(expected=AssertionError.class)
    public void testMinNumCoinsBadCoins() {
        CoinChanger.minNumCoins(10, Arrays.asList(10));
    }

    @Test(expected=AssertionError.class)
    public void testMinNumCoinsUnsortedCoins() {
        CoinChanger.minNumCoins(10, Arrays.asList(10, 1));
    }

    @Test
    public void testMinNumCoins0() {
        assertThat(CoinChanger.minNumCoins(0, EURO_COPPERS), is(0));
    }

    @Test
    public void testMinNumCoins1() {
        assertThat(CoinChanger.minNumCoins(1, EURO_COPPERS), is(1));
    }

    @Test
    public void testMinNumCoins8() {
        assertThat(CoinChanger.minNumCoins(8, EURO_COPPERS), is(3));
    }

    @Test
    public void testMinNumCoins10() {
        assertThat(CoinChanger.minNumCoins(10, EURO_COPPERS), is(2));
    }

    @Test
    public void testMinNumCoinsFancy40() {
        assertThat(CoinChanger.minNumCoins(40, FANCY_COINS), is(2));
    }
}
