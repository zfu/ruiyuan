/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
 */

public class Solution {
    public int threeSumSmaller(int[] num, int target) {
      // Write your solution here
      if (num == null || num.length < 3) {
        return 0;
      }
      Arrays.sort(num);
      int res = 0;
      for (int i = 0; i < num.length - 2; i++) {
        int value = target - num[i];
        
        int left = i + 1, right = num.length - 1;
        while (left < right) {
          int sum = num[left] + num[right];
          if (sum >= value) {
            right--;
          } else {
            res += right - left;
            left++;
          }
        }
      }
      return res;
    }
  }
  