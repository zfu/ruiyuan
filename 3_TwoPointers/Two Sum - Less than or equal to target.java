/**
 * 609. Two Sum - Less than or equal to target Given an array of integers, find
 * how many pairs in the array such that their sum is less than or equal to a
 * specific target number. Please return the number of pairs.
 * 
 * Example Given nums = [2, 7, 11, 15], target = 24. Return 5. 2 + 7 < 24 2 + 11
 * < 24 2 + 15 < 24 7 + 11 < 24 7 + 15 < 25
 */

 /**
  * 用双指针，count += right - left；是从小的数字开始算的，最小的数加上剩下的数都会小于target
  */
public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int count = 0;
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else {
                count += right - left;
                left++;
            }
        }
        return count;
    }
}