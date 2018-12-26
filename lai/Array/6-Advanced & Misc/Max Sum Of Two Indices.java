/**
 * Given an array with integers, find two indices i and j  (j>=i),  such that the value of A[i]+A[j]+ (j - i) is maximized.

Return (i, j).

Assumptions:

The given array is not null and it has length of > 0.
Examples:

array = {1, 5, 3}, the max sum is array[1] + array[1] + (1 - 1) = 10, return {1, 1}

 */

public class Solution {
    public int[] maxSum(int[] array) {
      // Write your solution here
      if (array == null || array.length == 0) {
        return new int[0];
      }
      
      int maxI = Integer.MIN_VALUE;
      int maxIndexI = 0;
      for (int i = 0; i < array.length; i++) {
        if ((array[i] - i) > maxI) {
          maxI = array[i] - i;
          maxIndexI = i;
        }
      }
      
      int maxJ = Integer.MIN_VALUE;
      int maxIndexJ = 0;
      for (int i = 0; i < array.length; i++) {
        if ((maxI + array[i] + i) > maxJ) {
          maxJ = maxI + array[i] + i;
          maxIndexJ = i;
        }
      }
      return new int[]{maxIndexI, maxIndexJ};
    }
  }
  