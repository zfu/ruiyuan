/**
 * 585. Maximum Number in Mountain Sequence Given a mountain sequence of n
 * integers which increase firstly and then decrease, find the mountain top.
 * 
 * Example Given nums = [1, 2, 4, 8, 6, 3] return 8 Given nums = [10, 9, 8, 7],
 * return 10
 * 
 */

public class Solution {
    /**
     * @param nums: a mountain sequence which increase firstly and then decrease
     * @return: then mountain top
     */
    public int mountainSequence(int[] nums) {
        // write your code here
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}