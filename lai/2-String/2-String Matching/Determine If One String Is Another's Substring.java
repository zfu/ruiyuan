/**
 * Determine if a small string is a substring of another large string.

Return the index of the first occurrence of the small string in the large string.

Return -1 if the small string is not a substring of the large string.

Assumptions

Both large and small are not null
If small is empty string, return 0
Examples

“ab” is a substring of “bcabc”, return 2
“bcd” is not a substring of “bcabc”, return -1
"" is substring of "abc", return 0  
 */

 // https://leetcode.com/problems/implement-strstr/
public class Solution {
    public int strstr(String large, String small) {
      // Write your solution here
      if (small == null || small.length() == 0) {
        return 0;
      }
      if (large == null || large.length() == 0) {
        return -1;
      }
      
      
      for (int i = 0; i < large.length() - small.length() + 1; i++) {
        int j = 0;
        for (; j < small.length(); j++) {
          if (small.charAt(j) != large.charAt(i + j)) {
            break;
          }
        }
        if (j == small.length()) {
          return i;
        }
      }
      return -1;
    }
  }
  