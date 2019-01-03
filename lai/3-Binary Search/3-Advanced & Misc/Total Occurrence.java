/**
 * 
 Given a target integer T and an integer array A sorted in ascending order, Find the total number of occurrences of T in A.

Examples

A = {1, 2, 3, 4, 5}, T = 3, return 1
A = {1, 2, 2, 2, 3}, T = 2, return 3
A = {1, 2, 2, 2, 3}, T = 4, return 0
Corner Cases

What if A is null? We should return 0 in this case.
 */

public class Solution {
    public int totalOccurrence(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length == 0) {
        return 0;
      }
      
      int n = array.length;
      if (array[n - 1] < target || array[0] > target) {
        return 0;
      }
      
      int left = 0, right = n - 1;
      int start = 0;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (array[mid] >= target) {
          right = mid - 1;
          start = mid;
        } else {
          left = mid + 1;
        }
      }
      
      if (array[start] != target) {
        return 0;
      }
      
      int end = n - 1;
      left = 0;
      right = n - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (array[mid] <= target) {
          end = mid;
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
  
      return end - start + 1;
    }
  }
  