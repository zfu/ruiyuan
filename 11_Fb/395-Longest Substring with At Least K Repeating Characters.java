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
this problem prompts us to use two pointer method, however it's quite difficult to decide the condition to expand (j++) and shrink (i++) - that's where the above posted solution rocks. thanks again!

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