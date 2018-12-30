/**
 * Given a 2D integer matrix, where every row is sorted in ascending order. How to find a common element in all rows. If there is no common element, then returns -1.

Example

matrix = { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } }

the common element is 4.
 */
/**
 * Explanation for working of above code
Let us understand working of above code for following example.

Initially entries in last column array are N-1, i.e., {4, 4, 4, 4}
    {1, 2, 3, 4, 5},
    {2, 4, 5, 8, 10},
    {3, 5, 7, 9, 11},
    {1, 3, 5, 7, 9},

The value of min_row is 0, so values of last column index for rows with value greater than 5 is reduced by one. So column[] becomes {4, 3, 3, 3}.
    {1, 2, 3, 4, 5},
    {2, 4, 5, 8, 10},
    {3, 5, 7, 9, 11},
    {1, 3, 5, 7, 9},

The value of min_row remains 0 and and value of last column index for rows with value greater than 5 is reduced by one. So column[] becomes {4, 2, 2, 2}.
    {1, 2, 3, 4, 5},
    {2, 4, 5, 8, 10},
    {3, 5, 7, 9, 11},
    {1, 3, 5, 7, 9},

The value of min_row remains 0 and value of last column index for rows with value greater than 5 is reduced by one. So colomun[] becomes {4, 2, 1, 2}.
    {1, 2, 3, 4, 5},
    {2, 4, 5, 8, 10},
    {3, 5, 7, 9, 11},
    {1, 3, 5, 7, 9},

Now all values in current last columns of all rows is same, so 5 is returned.
 */
public class Solution {
    public int search(int[][] matrix) {
      // Write your solution here
      if (matrix == null || matrix.length == 0) {
        return -1;
      }
      int rows = matrix.length;
        int cols = matrix[0].length;
          
        int[] aux = new int[rows];
        for (int i = 0; i < rows; i++) {
        aux[i] = cols - 1;
      }
      
        int minVal = Integer.MAX_VALUE;
        int minIndex = 0;
        
      while (aux[minIndex] >= 0) {
        for (int i = 0; i < rows; i++) {
          if (matrix[i][aux[i]] < minVal) {
            minVal = matrix[i][aux[i]];
            minIndex = i;
          }
        } 
          int count = 0;
          for (int i = 0; i < rows; i++) {
            if (matrix[i][aux[i]] > minVal) {
              if (aux[i] == 0) {
                return -1;
              }
              aux[i]--;
            } else {
              count++;
            }
          }
          
        if (count == rows) {
          return matrix[minIndex][aux[minIndex]];
        }
      }
      return -1;
    }
  }

  /**
   * https://www.techiedelight.com/find-common-elements-present-rows-matrix/
   * Efficient solution is to use map. We start by inserting every element of the first row in an empty map.
   * Then for every element in the remaining rows, if they are present in the map and is occurring for the first time
   * in current row, we increment its value in map by 1. Finally for the last row, we print the element if it has already 
   * appreared Row - 1 times
   */
  public class Solution {
    public int search(int[][] matrix) {
      // Write your solution here
      int rows = matrix.length;
      int cols = matrix[0].length;
      Map<Integer, Integer> map = new HashMap<>();
      
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          // Insert elements of the first row into the map and
          // initialize them with value of 1
          if (i == 0) {
            map.put(matrix[0][j], 1);
          }
          
          // from second rows onwards, check if the current element exists
          // in the map and first in the current row
          if (i > 0 && map.containsKey(matrix[i][j]) && map.get(matrix[i][j]) == i) {
            //increment the count of the element by 1
            map.put(matrix[i][j], i + 1);
            // if i represent the last row, print the element
            if (i == rows - 1) {
              return matrix[i][j];
            }
          }
        }
      }
      return -1;
    }
  }
  