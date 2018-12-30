/**
 * Right shift a given string by n characters.

Assumptions

The given string is not null.
n >= 0.
 */

public class Solution {
    public String rightShift(String input, int n) {
      // Write your solution here
      if (input == null || input.length() == 0 || n < 0) {
        return input;
      }
      
      char[] charArray = input.toCharArray();
      n %= charArray.length;
      reverse(charArray, 0, charArray.length - 1 - n);
      reverse(charArray, charArray.length - n, charArray.length - 1);
      reverse(charArray, 0, charArray.length - 1);
      
      return new String(charArray);
    }
    
    private void reverse(char[] charArray, int i, int j) {
      while (i < j) {
        swap(charArray, i, j);
        i++;
        j--;
      }
    }
    
    private void swap(char[] charArray, int i, int j) {
      char temp = charArray[i];
      charArray[i] = charArray[j];
      charArray[j] = temp;
    }
  }
  