/**
 * Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

Assumptions:

The original string only contains alphabetic characters.
Both input and pattern are not null.
Pattern would not contain invalid information like "a0a","0".
Examples:

pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
 */

// https://leetcode.com/problems/valid-word-abbreviation/

public class Solution {
    public boolean match(String input, String pattern) {
      // Write your solution here
      if ((input == null || input.length() == 0) && (pattern == null || pattern.length() == 0)) {
        return true;
      }
      if (input == null || input.length() == 0 || pattern == null || pattern.length() == 0) {
        return false;
      }
      int M = input.length();
      int N = pattern.length();
      int i = 0, j = 0;
      
      while (i < M && j < N) {
        int count = 0;
        if (count == 0 && pattern.charAt(j) == '0') {
          return false;
        }
        while (j < N && Character.isDigit(pattern.charAt(j))) {
          count = count * 10 + pattern.charAt(j) - '0';
          j++;
        }
        i += count;
        if (i >= M || j >= N) {
          break;
        }
        if (input.charAt(i) != pattern.charAt(j)) {
          return false;
        }
        i++;
        j++;
      }
      return i == M && j == N;
    }
  }
  