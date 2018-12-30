/**
 * Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.

Assumptions

The given input string is not null.
The characters to be removed is given by another string, it is guaranteed to be not null.
Examples

input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
 */

public class Solution {
    public String remove(String input, String t) {
      // Write your solution here
      if (input == null || input.length() == 0 || t == null || t.length() == 0) {
        return input;
      }
      
      char[] charArray = input.toCharArray();
      Set<Character> set = ConvertToSet(t);
      int index = 0;
      
      for (int i = 0; i < charArray.length; i++) {
        if (!set.contains(charArray[i])) {
          charArray[index++] = charArray[i];
        }
      }
      
      return new String(charArray, 0, index);
    }
    
    private Set<Character> ConvertToSet(String t) {
      Set<Character> set = new HashSet<>();
      
      for (int i = 0; i < t.length(); i++) {
        set.add(t.charAt(i));
      }
      return set;
    }
  }
  