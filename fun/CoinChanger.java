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
import java.util.Collections;
import java.util.List;

public class CoinChanger {
    /**
     * Find the minimum number of coins required for the specified amount. An
     * infinite amount of coins is supposed to be available.
     * 
     * @param amount
     *            The amount to be generated.
     * @param coins
     *            The denomination of the available coins.
     * @return Minimum number of coins required.
     */
    public static int minNumCoins(int amount, List<Integer> coins) {
        assert (amount >= 0);
        assert (isConsistent(coins));

        List<Integer> results = new ArrayList<>(amount + 1);
        results.add(0);
        for(int i = 1; i <= amount; ++i) {
            results.add(Integer.MAX_VALUE);
            for(int coin : coins) {
                if(coin > i)
                    break;

                int tentative = results.get(i - coin) + 1;
                if(tentative < results.get(i)) {
                    results.set(i, tentative);
                }
            }
        }

        return results.get(results.size() - 1);
    }

    private static boolean isConsistent(List<Integer> coins) {
        if(coins == null || coins.isEmpty())
            return false;
        if(coins.get(0) != 1)
            return false;
        for(int i = 1; i < coins.size(); ++i) {
            if(coins.get(i) <= coins.get(i - 1))
                return false;
        }
        return true;
    }

    public static int greedy(int amount, List<Integer> coins) {
        assert (amount > 0);
        assert (!coins.isEmpty());

        List<Integer> descendingCoins = new ArrayList<>(coins);
        descendingCoins.sort(Collections.reverseOrder());

        int counter = 0;
        for(int coin : descendingCoins) {
            while(amount >= coin) {
                amount -= coin;
                ++counter;
                if(amount == 0)
                    return counter;
            }
        }

        // Unexpected. Possibly an inconsistent list of coins has been passed
        // in.
        return -1;
    }
}
