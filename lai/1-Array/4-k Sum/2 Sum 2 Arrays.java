/**
 * Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array, that sums to the given target number.

Assumptions

The two given arrays are not null and have length of at least 1
Examples

A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)

A = {1, 3, 5}, B = {2, 8}, target = 6, return false
 */

public class Solution {
    public boolean existSum(int[] a, int[] b, int target) {
      // Write your solution here
      if (a == null || b == null || a.length < 1 || b.length < 1) {
        return false;
      }
      Arrays.sort(b);
      for (int i = 0; i < a.length; i++) {
        int val = target - a[i];
        int left = 0, right = b.length - 1;
        while (left <= right) {
          int mid = left + (right - left) / 2;
          if (b[mid] == val) {
            return true;
          } else if (b[mid] < val) {
            left = mid + 1;
          } else {
            right = mid - 1;
          }
        }
      }
      return false;
    }
  }
  