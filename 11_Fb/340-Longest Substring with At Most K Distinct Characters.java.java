/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.
Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
Author:Mindy927*/

/*
Sliding window
 -- left = 0, right = 0, right ++ in each iteration
 -- move left pointer until result is valid in each iteration, update res
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, right = 0;
        int n = s.length();
        int[] cnt = new int[128];
        Set<Character> set = new HashSet<>(); //chars in sliding window
        int res = 0;
        
        while (right < n){
            char cur = s.charAt(right++);
            cnt[cur]++;
            set.add(cur);
            while (set.size() > k){ //move left pointer until result is valid again
                char prev = s.charAt(left++);
                cnt[ prev]--;
                if (cnt[ prev] == 0) set.remove(prev);
            }
            res = Math.max(res, right - left );
        }
        
        return res;
    }
    
}