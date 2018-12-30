/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.


Example 1:

Input: "aba"
Output: True
Example 2:

Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:

The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

public class Solution {
    public boolean validPalindrome(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return true;
      }
      int left = 0, right = input.length() - 1;
      while (left < right) {
        if (input.charAt(left) != input.charAt(right)) {
          return isValidPalindrome(input, left + 1, right) || isValidPalindrome(input, left, right - 1);
        }
        left++;
        right--;
      }
      return true;
    }
    
    private boolean isValidPalindrome(String s, int i, int j) {
      while (i < j) {
        if (s.charAt(i++) != s.charAt(j--)) {
          return false;
        }
      }
      return true;
    }
  }

  
  // https://leetcode.com/problems/valid-palindrome-ii/solution/
  class Solution {
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
}