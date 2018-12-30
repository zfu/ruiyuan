/**
 * Reverse the words in a sentence and truncate all heading/trailing/duplicate space characters.

Examples

“ I  love  Google  ” → “Google love I”

Corner Cases

If the given string is null, we do not need to do anything.
 */

public class Solution {
    public String reverseWords(String input) {
      // Write your solution here
      input = removeSpaces(input);
      char[] charArray = input.toCharArray();
        reverse(charArray, 0, charArray.length - 1);
        int start = 0;
        for (int i = 0; i < charArray.length; i++) {
          if (charArray[i] != ' ' && (i == 0 || charArray[i - 1] == ' ')) {
            start = i;
          }
          
          if (charArray[i] != ' ' && (i == charArray.length - 1 || charArray[i + 1] == ' ')) {
            reverse(charArray, start, i);
          }
        }
        return new String(charArray);
      }
      
      private void reverse(char[] charArray, int left, int right) {         
        while (left < right) {
          swap(charArray, left++, right--);      
        }   
      }
      
      private void swap(char[] charArray, int left, int right) {
        char temp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = temp;
      }  
    
    private String removeSpaces(String input) {
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
  