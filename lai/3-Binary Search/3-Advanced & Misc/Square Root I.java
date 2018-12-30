/**
 * 
 Given an integer number n, find its integer square root.

Assumption:

n is guaranteed to be >= 0.
Example:

Input: 18, Return: 4

Input: 4, Return: 2


 */

public class Solution {
    public int sqrt(int x) {
      // Write your solution here
      if (x <= 1) {
        return x;
      }
      int left = 1, right = x / 2;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (x / mid == mid) {
          return mid;
        } else if (x / mid > mid) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
      return right;
    }
  }
  