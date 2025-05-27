/**
 * Fibonacci.java
 * 
 * Having fun with Fibonacci numbers
 * 
 * @author manny egalli64@gmail.com
 */

package fun;

public class Fibonacci {
    public long naive(int n) {
        if (n <= 1)
            return n;
        return naive(n - 1) + naive(n - 2);
    }

    public long caching(int n) {
        long[] results = new long[n + 1];
        results[0] = 0;
        results[1] = 1;
        for (int i = 2; i <= n; ++i) {
            results[i] = results[i - 1] + results[i - 2];
        }

        return results[n];
    }

    /**
     * lastDigitNaive
     * 
     * Naive solution to the problem of finding the last digit of a Fibonacci number
     * @see http://bitingcode.blogspot.com/2016/10/last-digit-of-fibonacci-number.html
     *  
     * @param n Fibonacci number, should be positive
     * @return last digit of the Fibonacci number
     */
    public int lastDigitNaive(int n) {
        if (n < 2)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 1; i < n; ++i) {
            int older = previous;
            previous = current;
            current = (older + previous) % 10;
        }

        return current;
    }

    /**
     * lastDigit60
     * 
     * Improved solution to the problem of finding the last digit of a Fibonacci number
     * @see http://bitingcode.blogspot.com/2016/10/last-digit-of-fibonacci-number.html
     *  
     * @param n Fibonacci number, should be positive
     * @return last digit of the Fibonacci number
     */
    public int lastDigit60(int n) {
        int[] fibLast = new int[60];
        fibLast[0] = 0;
        fibLast[1] = 1;
        for (int i = 2; i < 60; ++i) {
            fibLast[i] = (fibLast[i - 1] + fibLast[i - 2]) % 10;
        }

        return fibLast[n%60];
    }

}
