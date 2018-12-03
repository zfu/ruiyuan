/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
Author: Mindy927 */

class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); //cur cnt: first index
        
        int cnt = 0, max = 0;
        map.put(0, -1);  //initialize, deal with case 01
        
        for (int i=0; i<nums.length; i++){
            cnt += nums[i]==1? 1:-1;
            if (map.containsKey(cnt)) max = Math.max(i-map.get(cnt), max);
            else map.put(cnt, i);
        }
        
        return max;
    }
}