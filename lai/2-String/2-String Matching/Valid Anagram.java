/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

public class Solution {
    public boolean isAnagram(String source, String target) {
      // Write your solution here
      if (source == null || target == null) {
        return false;
      }
      Map<Character, Integer> map = new HashMap<>();
      for (Character c : source.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
      }
      
      for (Character c : target.toCharArray()) {
        if (!map.containsKey(c)) {
          return false;
        } else {
          map.put(c, map.get(c) - 1);
        }
      }
      
      for (Integer i : map.values()) {
        if (i != 0) {
          return false;
        }
      }
      return true;
    }
  }

  //https://leetcode.com/problems/valid-anagram/solution/
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
        table[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
        table[t.charAt(i) - 'a']--;
        if (table[t.charAt(i) - 'a'] < 0) {
            return false;
        }
    }
    return true;
}

public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    return Arrays.equals(str1, str2);
}