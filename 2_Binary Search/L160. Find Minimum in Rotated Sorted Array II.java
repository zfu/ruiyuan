/**
 * 160. Find Minimum in Rotated Sorted Array II Suppose a sorted array is
 * rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * Example Given [4,4,5,6,7,0,1,2] return 0.
 * 
 * Notice The array may contain duplicates.
 */

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1, res = nums[0];

        while (left < right && nums[left] == nums[right]) {
            left++;
            right--;
        }

        res = Math.min(nums[left], res);

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                //res = Math.min(res, nums[mid]);
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return Math.min(res, nums[left]);
    }
}