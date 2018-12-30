/**
 * Given two binary strings, return their sum (also a binary string).

Input: a = “11”

           b = “1”

Output: “100”
 */

public class Solution {
    public String addBinary(String a, String b) {
      // Write your solution here
      if (a == null || b == null) {
        return null;
      }
      
      StringBuilder sb = new StringBuilder();
      int pa = a.length() - 1;
      int pb = b.length() - 1;
      int carry = 0;
      
      while (pa >= 0 || pb >= 0 || carry > 0) {
        int aVal = pa >= 0 ? (a.charAt(pa--) - '0') : 0;
        int bVal = pb >= 0 ? (b.charAt(pb--) - '0') : 0;
        int sum = aVal + bVal + carry;
        sb.insert(0, sum % 2);
        carry = sum / 2;    
      }
      return sb.toString();
    }
  }
  