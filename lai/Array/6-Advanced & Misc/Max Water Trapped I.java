/**
 * Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.

Assumptions

The given array is not null
Examples

{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
 */

public class Solution {
    public int maxTrapped(int[] array) {
      // Write your solution here
      if (array == null || array.length < 3) {
        return 0;
      }
      
      int left = 0, right = array.length - 1;
      int minHeight = 0;
      int res = 0;
      
      while (left < right) {
        minHeight = Math.max(minHeight, Math.min(array[left], array[right]));
        
        if (array[left] <= array[right]) {
          res += (minHeight - array[left]);
          left++;
        } else {
          res += (minHeight - array[right]);
          right--;
        }
      }
      return res;
    }
  }
  