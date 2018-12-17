/**
 * Rotate an N * N matrix clockwise 90 degrees.

Assumptions

The matrix is not null and N >= 0
Examples

{ {1,  2,  3}

  {8,  9,  4},

  {7,  6,  5} }

after rotation is

{ {7,  8,  1}

  {6,  9,  2},

  {5,  4,  3} }

Medium
2 D Array
 */

 /**
  * 
Time ~ O(N^2), Space ~ O(1) 
Rotate 90 clockwise: A -> B -> C -> D -> A
                         [i, j]
                     —  A  ————
                    |                          |
                    |                         B [j, n - 1 - i]
                    |                          |
                    |                          |
[n - j - 1, i]   D                         |
                    | ———— C —  |
                       [n - i - 1, n - 1 - j]

  */
public class Solution {
    public void rotate(int[][] matrix) {
      // Write your solution here
      int len = matrix.length;
      int halfLen = len % 2 == 0 ? len / 2 : len / 2 + 1;
      for (int i = 0; i < halfLen; i++) {
        for (int j = i; j < len - i - 1; j++) {
              int k = len - i - 1, l = len - j - 1;
              int tmp = matrix[i][j];
              matrix[i][j] = matrix[l][i];    // top   <- left
              matrix[l][i] = matrix[k][l];    // right <- top
              matrix[k][l] = matrix[j][k];    // down  <- right
              matrix[j][k] = tmp;             // left  <- down
        }
      }
    }
  }

  private int[][] spiral(int[][] s, int n, int count, int offset) {
    if (n > 0) {
      int x = offset, y = offset;
      if (n == 1) {
        s[y][x] = ++count;
      } else {
        for (int i=1; i<n; ++i) s[y][x++] = ++count;   // top
        for (int i=1; i<n; ++i) s[y++][x] = ++count;   // right side
        for (int i=1; i<n; ++i) s[y][x--] = ++count;   // bottom
        for (int i=1; i<n; ++i) s[y--][x] = ++count;   // left side
  
        spiral(s, n-2, count, ++offset);  // inner spiral has n-2 (top/right & bottom/left each decrease it by 1) and is offset toward the center by one
      }
    }
    return s;
  }
  
  public int[][] generateMatrix(int n) {
    return spiral(new int[n][n], n, 0, 0);
  }

  
  // Main idea: Using symmetic transformation implement matrix's rotate transformation
/*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry by diagonal line
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
 */
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // Reverse top to down
        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows-i-1][j];
                matrix[rows-i-1][j] = temp;
            }
        }
        // Swap the symmetry
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

/*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
 */
class Solution {
    public void anti_rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // Reverse left to right
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols-j-1];
                matrix[i][cols-j-1] = temp;
            }
        }
        // Swap the symmetry by diagonal line
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}