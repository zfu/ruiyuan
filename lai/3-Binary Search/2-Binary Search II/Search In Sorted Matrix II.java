/**
 * 
 Given a 2D matrix that contains integers only, which each row is sorted in ascending order and each column is also sorted in ascending order.

Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.

Assumptions:

The given matrix is not null.
Examples:

matrix = { {1, 2, 3}, {2, 4, 5}, {6, 8, 10} }

target = 5, return {1, 2}

target = 7, return {-1, -1}
 */

public class Solution {
    public int[] search(int[][] matrix, int target) {
      // Write your solution here
      if (matrix == null || matrix.length == 0) {
        return new int[] {-1, -1};
      }
      int row = 0, col = matrix[0].length - 1;
      while (row < matrix.length && col >= 0) {
        if (matrix[row][col] == target) {
          return new int[] {row, col};
        } else if (matrix[row][col] < target) {
          row++;
        } else {
          col--;
        }
      }
      return new int[] {-1, -1};
    }
  }
  