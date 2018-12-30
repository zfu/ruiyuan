/**
 * In URL encoding, whenever we see an space " ", we need to replace it with "20%". Provide a method that performs this encoding for a given string.

Examples

"google/q?flo wer market" → "google/q?flo20%wer20%market"
Corner Cases

If the given string is null, we do not need to do anything.
 */

public class Solution {
    public String encode(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return "";
      }
      char[] charArray = input.toCharArray();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < charArray.length; i++) {
        if (charArray[i] == ' ') {
          sb.append("20%");
        } else {
          sb.append(charArray[i]);
        }
      }
      return sb.toString();
    }
  }
  