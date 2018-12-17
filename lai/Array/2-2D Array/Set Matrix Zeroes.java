/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
Example 1:
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:
Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */

class Solution {
    public void setZeroes(int[][] matrix) {
      Boolean isCol = false;
      int R = matrix.length;
      int C = matrix[0].length;
  
      for (int i = 0; i < R; i++) {
  
        // Since first cell for both first row and first column is the same i.e. matrix[0][0]
        // We can use an additional variable for either the first row/column.
        // For this solution we are using an additional variable for the first column
        // and using matrix[0][0] for the first row.
        if (matrix[i][0] == 0) {
          isCol = true;
        }
  
        for (int j = 1; j < C; j++) {
          // If an element is zero, we set the first element of the corresponding row and column to 0
          if (matrix[i][j] == 0) {
            matrix[0][j] = 0;
            matrix[i][0] = 0;
          }
        }
      }
  
      // Iterate over the array once again and using the first row and first column, update the elements.
      for (int i = 1; i < R; i++) {
        for (int j = 1; j < C; j++) {
          if (matrix[i][0] == 0 || matrix[0][j] == 0) {
            matrix[i][j] = 0;
          }
        }
      }
  
      // See if the first row needs to be set to zero as well
      if (matrix[0][0] == 0) {
        for (int j = 0; j < C; j++) {
          matrix[0][j] = 0;
        }
      }
  
      // See if the first column needs to be set to zero as well
      if (isCol) {
        for (int i = 0; i < R; i++) {
          matrix[i][0] = 0;
        }
      }
    }
  }

  //Method 2: O(m+n) solution
//boolean[] row, col to keep track whether certain row/col has 0 element
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1;
        
        for (int i=0; i<m; i++){
            if (matrix[i][0] == 0) col0 = 0;
            for (int j=1; j<n; j++){
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        
        //update table from bottom up, otherwize row 0 will be overwritten
        for (int i=m-1; i>=0; i--){
            for (int j=n-1; j>=1; j--){
                if( matrix[i][0]==0 || matrix[0][j]==0 ) matrix[i][j] = 0;
            }
            if (col0==0) matrix[i][0] = 0;
        }
    }
}
