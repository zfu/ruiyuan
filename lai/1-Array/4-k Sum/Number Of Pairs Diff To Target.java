/**
 * Given an integer array, determine how many pairs of elements, the absolute value of the difference between the two elements is the given target value.

Assumptions:

There could be elements with duplicate value in the array, and each of the elements is considered different.
The given array is not null and has length >= 2.
Examples:

array = {3, 1, 2, 1}, target = 2, there are 2 pairs { (3, 1), (3, 1) }
 */

public class Solution {
    public int pairs(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return 0;
      }
      
      int res = 0;
      int left = 0, right = 0;
      Arrays.sort(array);
      while (right < array.length) {
        if (array[right] - array[left] == target) {
          res++;
          left++;
        } else if (array[right] - array[left] > target) {
          left++;
        } else {
          right++;
        }
      }
      return res;
    }
  }
  