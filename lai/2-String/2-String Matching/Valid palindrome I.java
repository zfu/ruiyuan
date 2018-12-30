/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters('0'-'9','a'-'z','A'-'Z') and ignoring cases.

For example,
"an apple, :) elp pana#" is a palindrome.

"dia monds dn dia" is not a palindrome.
 */
public class Solution {
    public boolean valid(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return true;
      }
      int left = 0, right = input.length() - 1;
      while (left < right) {
        while (left < right && !Character.isLetterOrDigit(input.charAt(left))) {
          left++;
        }
        
        while (left < right && !Character.isLetterOrDigit(input.charAt(right))) {
          right--;
        }
        
        if (input.charAt(left) != input.charAt(right)) {
          return false;
        }
        left++;
        right--;
      }
      return true;
    }
  }
  