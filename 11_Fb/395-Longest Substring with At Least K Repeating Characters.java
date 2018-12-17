/**
 Find the length of the longest substring T of a given string 
 (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

/**
 * super cool solution! many thanks for sharing!
this problem prompts us to use two pointer method, 
however it's quite difficult to decide the condition to expand (j++) and shrink (i++) - that's where the above posted solution rocks. thanks again!

if it helps, here are some explanations to understand above code:
how do we explore all possible solutions (substrings that satisfy given constraints) ?

this post's solution explores this way
-- find all sub-strings which have "h=1" unique character(s) and each character in the substring repeats at least "k" times
-- find all sub-strings which have "h=2" unique character(s) and each character in the substring repeats at least "k" times
-- ....
-- find all sub-strings which have "h=26" unique character(s) and each character in the substring repeats at least "k" times
-- and we're done! at h = 26, we're done. Take max of all the above valid substrings (by tracking with --max-- variable) -- that'll be our answer.
Details: (for a fixed h)

basically, we want to find a window (i to j) which has "h" unique chars and if all h occur at least K times, we have a candidate solution
[Rules for window Expansion] so expand (j++) as long as the num of unique characters between 'i' to 'j' are h or less (unique <= h)
during expansion, also track chars that are "noLessThanK" (which occur at least k)
end of the loop update max (max len of valid window which have h unique chars and all h have at least k occurences)
once we see -- unique = h + 1 -- , we just expanded our window with (h+1)th unique char, so we should start to shrink now.
[Rules to window Shrink window] shrink as long as we have unique chars > h (update counts, unique, NoLessThanK along the way)
Hope this helps!
 */
class Solution {
    public int longestSubstring(String s, int k) {
        int len = 0; 
        int[] cnts = new int[26];  
        for (int i = 1; i <= 26; i++) {
            Arrays.fill(cnts, 0);
            int begin = 0, end = 0, uniqueChar = 0; 
            while (end < s.length()) {
                boolean valid = true;
                if (cnts[s.charAt(end++) - 'a']++ == 0) uniqueChar++; 

                // need exactly i unique characters
                while (uniqueChar > i)
                    if (cnts[s.charAt(begin++) - 'a']-- == 1) uniqueChar--;  

                // if the string has any character with less than k occurrences, the string is invalid
                for (int j = 0; j < 26; j++)
                    if (cnts[j] > 0 && cnts[j] < k) valid = false; 
                
                if (valid) len = Math.max(len, end - begin);
            }
        }
        return len; 
    }
}


/**
 * 此题关键是如何用O(1)的时间看当前的子字符串是否所有字母都出现至少k次。
这里用bitmask提供一种方法
int mask = 0; 如果count[s.charAt(j) - 'a'] < k， 那么我们就给mask赋值为mask = mask | (1 << (s.charAt(j) - 'a'));
这样做的结果是只要不满足条件，mask永远都大于0.
如果count[s.charAt(j) - 'a'] >= k, 那么我们给mask赋值为mask = mask & (~(1 << (s.charAt(j) - 'a')));
这样就把满足条件的字符串对应的mask值更改该为了0.
所以总体来说，当不满足条件的时候，mask > 0, 当满足条件的时候 mask == 0，然后再打擂台。

外层循环整体构架是双指针问题，应该还可以再优化，求指正。
 */

public int longestSubstring(String s, int k) {
    if (s == null || k < 1) {
        return 0;
    }

    int maxLen = 0;
    for (int i = 0; i < s.length() - k + 1; i++) {
        // reset below two variables every loop for i
        int[] count = new int[26];
        int mask = 0;
        
        for (int j = i; j < s.length(); j++) {
            int index = s.charAt(j) - 'a';
            count[index] ++;

            if (count[index] < k) {
                mask |= (1 << index);
            } else {
                mask &= (~(1 << index));
            }

            // all characters are at least repeating k times
            if (mask == 0) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
    }
    return maxLen;
}

// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87741/java-divide-and-conquerrecursion-solution
public class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        
        return helper(s.toCharArray(), 0, s.length() - 1, k);
    }
    
    private int helper(char[] arr, int start, int end, int k) {
        if (end - start + 1 < k) {
            return 0;
        }
        int[] dict = new int[26];
        for (int i = start; i <= end; i++) {
            dict[arr[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (dict[i] == 0) {
                continue;
            }
            else if (dict[i] < k) {
                for (int j = start; j <= end; j++) {
                    if (arr[j] == (char) ('a' + i)) {
                        int left = helper(arr, start, j - 1, k);
                        int right = helper(arr, j + 1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start + 1;
    }
}
