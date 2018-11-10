/**
 * 14. First Position of Target For a given sorted array (ascending order) and a
 * target number, find the first index of this number in O(log n) time
 * complexity.
 * 
 * If the target number does not exist in the array, return -1.
 * 
 * Example If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.
 * 
 * Challenge If the count of numbers is bigger than 2^32, can your code work
 * properly?
 */

public class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left < nums.length && nums[left] == target) {
            return left;
        }

        return -1;
    }
}