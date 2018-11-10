/**
 * 
 * 159. Find Minimum in Rotated Sorted Array Suppose a sorted array is rotated
 * at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * Example Given [4, 5, 6, 7, 0, 1, 2] return 0
 * 
 * Notice You may assume no duplicate exists in the array.
 */

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0)
            return 0;
        
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}