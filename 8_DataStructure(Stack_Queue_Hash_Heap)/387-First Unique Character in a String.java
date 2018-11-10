/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Examples:
s = "leetcode"
return 0.
s = "loveleetcode",
return 2.
*/

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a'] ++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cnt[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}