/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.
For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
 */

/**
 * Approach: Two Pointers (Sliding Window)
 * This question is about Sliding Window, You can learn something about it here:
 * Sliding Window Template:
 * https://github.com/cherryljr/LeetCode/blob/master/Sliding%20Window%20Template.java
 * Of course, this question could be solved by more concise code, so we don't need to use the template.
 * But the main idea is the same.
 *
 * Algorithm
 * Initialize left pointer to 0 and sum to 0
 * Iterate over the nums:
 *  Add nums[right] to sum
 *  While sum is greater than or equal to s:
 *      1. Update rst=min(rst,right−left+1), where right−left+1 is the size of current subarray
 *      2. It means that the first index can safely be incremented,
 *      since, the minimum subarray starting with this index with sum≥s has been achieved
 *      3. nums[left] from sum and increment left
 *
 * Complexity analysis
 *  Time complexity: O(n). Single iteration of O(n).
 *  Each element can be visited at most twice, once by the right pointer(i) and (at most)once by the left pointer.
 *  Space complexity: O(1) extra space.
 *  Only constant space required for left, right, sum and rst.
 *
 * Reference:
 * https://leetcode.com/articles/minimum-size-subarray-sum/#
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int rst = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                rst = Math.min(rst, right - left + 1);
                sum -= nums[left++];
            }
        }

        return rst == Integer.MAX_VALUE ? 0 : rst;
    }
}

//Method 1: O(n), two pointers
//sliding window : right pointer iterate array , increase left pointer when 1 possible solution found
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int i=0, sum = 0;
        int res = Integer.MAX_VALUE;
        
        for (int j=0; j<nums.length; j++){
            sum += nums[j];
            while (sum >=s){ 
                res = Math.min(res, j-i+1); //compare with previous result when sum>=s
                sum -= nums[i]; //move left pointer
                i++;
            }
        }
        
        return res == Integer.MAX_VALUE? 0:res;
    }
}

//Method 2: O(nlgn) binary search:search if a window of size k exists that satisfy the condition
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int left = 1, right = nums.length;
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (valid(s, nums, mid)) right = mid;
            else left = mid;
        }
        return valid(s, nums, left)? left:valid(s, nums, right)? right:0;
    }
    
    //whether there is a subArray with length len that has sum>=s
    public boolean valid(int s, int[] nums, int len){
        int sum = 0;
        for (int i=0; i<len; i++){
            sum += nums[i];
        }
        if (sum >= s) return true;
        
        for(int i=len; i<nums.length; i++){
            sum += nums[i];
            sum -= nums[i - len];
            if (sum >= s) return true;
        }
        
        return false;
    }
}