/**
 * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

Examples

“abbcccdeee” → “ab2c3de3”
 */

public class Solution {
    public String compress(String input) {
          if(input == null || input.length() <= 1) {
              return input;
          }
          String output = "";
          output += input.charAt(0);
          int count = 1;
          for(int i = 1; i < input.length();) {
              while(i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                  count++;
                  i++;
              }
              if(count > 1) {
                  output += String.valueOf(count);
              }
              if(i < input.length()) {
                  output += input.charAt(i);
              }
              count = 1;
              i++;
          }
          return output;
      }
  }
  
  