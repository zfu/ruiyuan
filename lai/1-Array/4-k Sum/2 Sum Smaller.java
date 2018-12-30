/**
 * Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.

Assumptions

The given array is not null and has length of at least 2
Examples

A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
 */

public class Solution {
    public int smallerPairs(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return 0;
      }
      
      Arrays.sort(array);
      int res = 0;
      
      int left = 0, right = array.length - 1;
      while (left < right) {
        int sum = array[left] + array[right];
        if (sum >= target) {
          right--;
        } else {
          res += right - left;
          left++;
        }
      }
      return res;
    }
  }
  