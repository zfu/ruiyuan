/**
 * 
 Two Strings are called isomorphic if the letters in one String can be remapped to get the second String. 
 Remapping a letter means replacing all occurrences of it with another letter. 
 The ordering of the letters remains unchanged. The mapping is two way and no two letters may map to the same letter, 
 but a letter may map to itself. Determine if two given String are isomorphic.

Assumptions:

The two given Strings are not null.
Examples:

"abca" and "xyzx" are isomorphic since the mapping is 'a' <-> 'x', 'b' <-> 'y', 'c' <-> 'z'.

"abba" and "cccc" are not isomorphic.
 */

 //https://leetcode.com/problems/isomorphic-strings/

public class Solution {
    public boolean isomorphic(String source, String target) {
      // Write your solution here
      if (source.length() != target.length()) {
        return false;
      }
      Map<Character, Character> map = new HashMap<>();
      for (int i = 0; i < source.length(); i++) {
        char sChar = source.charAt(i);
        char tChar = target.charAt(i);
        if (map.containsKey(sChar)) {
          if (map.get(sChar) != tChar) {
            return false;
          }
        } else {
          if (map.containsValue(tChar)) {
            return false;
          }
          map.put(sChar, tChar);
        }
      }
      return true;
    }
  }

  
  class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length())
            return false;
        int[] arr1 = new int[256], arr2 = new int[256];
        for(int i=0;i<s.length();i++)
        {
            if(arr1[s.charAt(i)]!=arr2[t.charAt(i)])
                return false;
            arr1[s.charAt(i)] = arr2[t.charAt(i)] = i+1;
        }
        return true;
    }
}