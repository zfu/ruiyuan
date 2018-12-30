/**
 * Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

There are no adjacent repeated characters with length > 9

Examples

“acb2c4” → “acbbcccc”
 */

public class Solution {
    public String decompress(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return input;
      }
      
      if (Character.isDigit(input.charAt(0))) {
        return input;
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < input.length(); i++) {
        if (Character.isDigit(input.charAt(i))) {
          for (int j = 0; j < getNumber(input.charAt(i)) - 1; j++) {
            sb.append(input.charAt(i - 1));
          }
        } else {
          sb.append(input.charAt(i));
        }
      }
      return sb.toString();
    }
    
    private int getNumber(char c) {
      return c - '0';
    }
  }
  