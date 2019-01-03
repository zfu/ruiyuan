/**
 * 
 Given two sorted arrays of integers, find the Kth smallest number.

Assumptions

The two given arrays are not null and at least one of them is not empty

K >= 1, K <= total lengths of the two sorted arrays

Examples

A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.

A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
 */

public class Solution {
    public int kth(int[] a, int[] b, int k) {
      // Write your solution here
      if (a == null && b == null) {
        return -1;
      }
      return kthHelper(a, 0, b, 0, k);
    }
    
    private int kthHelper(int[] a, int aStart, int[] b, int bStart, int k) {
      if (aStart >= a.length) {
        return b[bStart + k - 1];
      }
      
      if (bStart >= b.length) {
        return a[aStart + k - 1];
      }
      
      if (k == 1) {
        return Math.min(a[aStart], b[bStart]);    
      }
      
      int aMid = aStart + k / 2 - 1;
      int bMid = bStart + k / 2 - 1;
      
      int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
      int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
      
      if (aVal >= bVal) {
        return kthHelper(a, aStart, b, bMid + 1, k - k / 2);
      } else {
        return kthHelper(a, aMid + 1, b, bStart, k - k / 2);
      }
    }
  }
  