/**
 * Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).

If there does not exist such pair, return a zero length array.

Assumptions:

The given array is not null and has length of at least 2.
Examples:

A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == 2.
 */

/**
 * Input: new int[]{1,4,4,8,14}, 0
expected [1,2] but was: [2,1]
 */

public class Solution {
    public int[] twoDiff(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return new int[0];
      }
      
      int i = 0, j = 1;
      int[] res = new int[2];
      while (i < array.length && j < array.length) {
        if (i != j && array[j] - array[i] == Math.abs(target)) {
          if (target <= 0) {
            res[0] = j;
            res[1] = i;
          } else {
            res[0] = i;
            res[1] = j;
          }
          break;
        } else if (array[j] - array[i] < Math.abs(target)) {
          j++;
        } else {
          i++;
        }
      }
      
      if (res[0] == res[1]) {
        return new int[0];
      }
      
      return res;
    }
  }
  