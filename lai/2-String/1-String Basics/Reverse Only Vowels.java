/**
 * Only reverse the vowels('a', 'e', 'i', 'o', 'u') in a given string, the other characters should not be moved or changed.

Assumptions:

The given string is not null, and only contains lower case letters.
Examples:

"abbegi" --> "ibbega"
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
        if (!isVowel(charArray[left])) {
          left++;
          continue;
        }
        if (!isVowel(charArray[right])) {
          right--;
          continue;
        }
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
    
    private boolean isVowel(char c) { 
      return (c=='a' || c=='A' || c=='e' || 
              c=='E' || c=='i' || c=='I' || 
              c=='o' || c=='O' || c=='u' || 
              c=='U'); 
    } 
  }
  