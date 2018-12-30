/**
 * Given a non-negative number represented as an array of digits, plus one to the number.

Input: [2, 5, 9]

Output: [2, 6, 0]
 */

public class Solution {
    public int[] plus(int[] digits) {
      // Write your solution here
      if (digits == null || digits.length == 0) {
        return new int[0];
      }
      
      int carry = 1, digit = 0;
      for (int i = digits.length - 1; i >= 0; i--) {
        digit = (digits[i] + carry) % 10;
        carry = (digits[i] + carry) / 10;
        digits[i] = digit;
        if (carry == 0) {
          break;
        }
      }
      if (carry == 1) {
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        for (int i = 1; i < digits.length; i++) {
          res[i] = digits[i - 1];
        }
        return res;
      }
      return digits;
    }
  }
  