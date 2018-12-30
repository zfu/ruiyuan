/**
 * Reverse a given string.

Assumptions

The given string is not null.
 */

public class Solution {
    public String reverse(String input) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return input;
      }
      
      char[] charArray = input.toCharArray();
      int left = 0, right = charArray.length - 1;
      while (left < right) {
        swap(charArray, left, right);
        left++;
        right--;
      }
      return new String(charArray);
    }
    
    private void swap(char[] charArray, int i, int j) {
      char temp = charArray[i];
      charArray[i] = charArray[j];
      charArray[j] = temp;
    }
  }
  