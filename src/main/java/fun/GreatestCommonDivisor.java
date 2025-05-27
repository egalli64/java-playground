/**
 * GreatestCommonDivisor.java
 * 
 * Playing with the Greatest Common Divisor
 * 
 * @author manny egalli64@gmail.com
 */

package fun;

/**
 * @see http://bitingcode.blogspot.com/2016/11/greatest-common-divisor.html
 */
public class GreatestCommonDivisor {
    static public int naive(int a, int b) {
        int result = 1;
        for (int i = 2; i <= Math.min(a, b); ++i) {
            if (a % i == 0 && b % i == 0) {
                result = i;
            }
        }
        return result;
    }
    
    public static int euclid(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
