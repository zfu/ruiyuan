/**
 * Generate an M * N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, M * N in increasing order.

Assumptions

M >= 0, N >= 0
Examples

M = 3, N = 4, the generated matrix is

{ {1,  2,  3,  4}

  {10, 11, 12, 5},

  {9,  8,  7,  6} }
 */

public class Solution {
    public int[][] spiralGenerate(int m, int n) {
      // Write your solution here
      int[][] res = new int[m][n];
      int r1 = 0, r2 = m - 1;
      int c1 = 0, c2 = n - 1;
      int count = 1;
      while (r1 <= r2 && c1 <= c2) {
        // from left to right
        for (int c = c1; c <= c2; c++) {
          res[r1][c] = count++;
        }
        
        for (int r = r1 + 1; r <= r2; r++) {
          res[r][c2] = count++;
        }
        
        if (r1 < r2 && c1 < c2) {
          for (int c = c2 - 1; c > c1; c--) {
            res[r2][c] = count++;
          }
          
          for (int r = r2; r > r1; r--) {
            res[r][c1] = count++;
          }
        }
        
        r1++;
        r2--;
        c1++;
        c2--;
      }
      return res;
    }
  }
  