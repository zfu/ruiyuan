/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */

 /**
  * Imagine a very short string is at the end of the array. The above approach will still do S comparisons. 
  One way to optimize this case is to do vertical scanning. We compare characters 
  from top to bottom on the same column (same character index of the strings) before moving on to the next column.
  */

  //https://leetcode.com/problems/longest-common-prefix/solution/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
      // Write your solution here
      if (strs == null || strs.length == 0) {
        return "";
      }
      
      for (int i = 0; i < strs[0].length(); i++) {
        for (int j = 1; j < strs.length; j++) {
          if (i == strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
            return strs[0].substring(0, i);
          }
        }
      }
      return strs[0];
    }
  }
  