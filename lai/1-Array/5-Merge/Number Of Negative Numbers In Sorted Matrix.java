/**
 * Given an matrix of integers, each row is sorted in ascending order from left to right, each column is also sorted in ascending order from top to bottom.

Determine how many negative numbers in the matrix.

Assumptions:

The given matrix is not null.
Examples:

{ {-5, -3, 0, 0, 1},

  {-3, -2, 1, 1, 3}

  {-2, 0,  3, 5, 6} }

The number of negative elements in the matrix is 5.
 */

 /**
  * Traverse the matrix from right-> left, top -> bottom.
Find the last negative number’s index in the 1st row.
Using this information, find the last negative number’s index in the 2nd row.
Keep repeating this process until either you run out of negative numbers or you get to the last row.
  */

  public class Solution {
    public int negNumber(int[][] matrix) {
      // Write your solution here
      if (matrix == null || matrix.length == 0) {
        return 0;
      }
      
      int res = 0;
      int m = matrix.length, n = matrix[0].length;
      int i = 0, j = n - 1;
      
      // start from the top right
      while (i < m && j >= 0) {
        if (matrix[i][j] < 0) {
          res += (j + 1);
          i++;
        } else {
          j--;
        }
      }
      return res;
    }
  }
  