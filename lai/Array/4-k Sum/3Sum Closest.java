/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example

           For example, given array S = {-1 2 1 -4}, and target = 1.

          The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

public class Solution {
    public int threeSumClosest(int[] num, int target) {
      // Write your solution here
      if (num == null || num.length < 3) {
        return 0;
      }
      int res = 0;
      int min = Integer.MAX_VALUE;
      Arrays.sort(num);
      for (int i = 0; i < num.length - 2; i++) {
        if (i != 0 && num[i] == num[i - 1]) {
          continue;
        }
        int left = i + 1, right = num.length - 1;
        while (left < right) {
          int sum = num[i] + num[left] + num[right];
          int diff = Math.abs(target - sum);
          if (diff == 0) {
            return sum;
          }
          if (diff < min) {
            res = sum;
            min = diff;
          } else if (sum < target) {
            left++;
          } else {
            right--;
          }
        }
      }
      return res;
    }
  }
  