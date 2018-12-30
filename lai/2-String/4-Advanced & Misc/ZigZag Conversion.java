/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility).

And then read line by line: "PAHNAPLSIIGYIR". Write the code that will take a string and make this conversion given a number of rows. convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".    
 */

public class Solution {
    public String convert(String input, int nRows) {
      // Write your solution here
      if (input == null || input.length() == 0) {
        return "";
      }
      if (nRows == 1 || nRows == input.length()) {
        return input;
      }
      
      StringBuilder sb = new StringBuilder();
      int len = input.length();
      int size = 2 * nRows - 2;
      
      for (int i = 0; i < nRows; i++) {
        for (int j = 0; j + i < len; j += size) {
          sb.append(input.charAt(i + j));
          if (i != 0 && i != nRows - 1 && (i + j + size - 2 * i) < len) {
            sb.append(input.charAt(i + j + size - 2 * i));
          }
        }
      }
      return sb.toString();
    }
  }
  