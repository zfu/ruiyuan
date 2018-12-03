/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
Example 1:
Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:
Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?
Author: Mindy927 */


class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length==0) return 0;
        int[] sum = new int[nums.length+1];
        Map<Integer,Integer> map = new HashMap<>(); //sum:first index of this sum
       
        sum[0] = 0;
        for (int i=1; i<nums.length+1; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }

       
        int max = 0;
        for (int i=0; i<sum.length; i++){
            if (!map.containsKey(sum[i])) map.put(sum[i],i);
            if (map.containsKey(sum[i]-k)) max = Math.max(max, i-map.get(sum[i]-k));  
        }
        
        return max;
    }
}