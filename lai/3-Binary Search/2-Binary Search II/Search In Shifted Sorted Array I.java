/**
 * 
 Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

Assumptions

There are no duplicate elements in the array.
Examples

A = {3, 4, 5, 1, 2}, T = 4, return 1
A = {1, 2, 3, 4, 5}, T = 4, return 3
A = {3, 5, 6, 1, 2}, T = 4, return -1
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case.
 */

public class Solution {
    public int search(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length == 0) {
        return -1;
      }
      int left = 0, right = array.length - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (array[mid] == target) {
          return mid;
        } else if (array[mid] < array[right]) {
          if (array[mid] < target && target <= array[right]) {
            left = mid + 1;
          } else {
            right = mid - 1;
          }
        } else {
          if (array[left] <= target && target < array[mid]) {
            right = mid - 1;
          } else {
            left = mid + 1;
          }
        }
      }
      return -1;
    }
  }
  