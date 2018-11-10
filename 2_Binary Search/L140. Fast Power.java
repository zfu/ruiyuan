/**
 * 140. Fast Power Calculate the a^n % b where a, b and n are all 32bit positive
 * integers.
 * 
 * Example For 2^31 % 3 = 2
 * 
 * For 100^1000 % 1000 = 0
 * 
 * Challenge O(logn)
 */

public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (n == 0 && b == 1) {
            return 0;
        }
        
        long res = 1, temp = a;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = (res * temp) % b;
            }
            temp = (temp * temp) % b;
            n >>= 1;
        }
        
        return (int)res % b;
    }
}