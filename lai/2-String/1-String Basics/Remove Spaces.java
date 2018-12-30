/**
 * Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

The given string is not null.
Examples:

“  a” --> “a”
“   I     love MTV ” --> “I love MTV”
 */

public class Solution {
    public String removeSpaces(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return new String();
      }
      
      char[] charArray = input.toCharArray();
      int index = 0;
      for (int i = 0; i < charArray.length; i++) {
        if (charArray[i] == ' ' && (i == 0 || charArray[i - 1] == ' ')) {
          continue;
        }
        charArray[index++] = charArray[i];
      }
      
      if (index > 0 && charArray[index - 1] == ' ') {
        return new String(charArray, 0, index - 1);
      }
      return new String(charArray, 0, index);
    }
  }
  