/**
 * 604. Window Sum 
 * Given an array of n integer, and a moving window(size k),
 * move the window at each iteration from the start of the array, find the sum
 * of the element inside the window at each moving.
 * 
 * Example For array [1,2,7,8,5], moving window size k = 3. 1 + 2 + 7 = 10 
 * 2 + 7 + 8 = 17 
 * 7 + 8 + 5 = 20 
 * return [10,17,20]
 */

public class Solution {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        
        int[] res = new int[nums.length - k + 1];
        
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        res[0] = sum;
        int left = 0, right = k;
        int i = 1;
        while (right < nums.length) {
            sum = sum + nums[right] - nums[left];
            res[i++] = sum;
            right++;
            left++;
        }
        return res;
    }
}