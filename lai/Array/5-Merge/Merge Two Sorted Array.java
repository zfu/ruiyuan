/**
 * Merge given amount of numbers from two sorted arrays.

Input: [1, 2, 3], 3, [2], 1

Output: [1,2, 2, 3]
 */

public class Solution {
    public int[] merge(int[] A, int m, int[] B, int n) {
      // Write your solution here
      if (A == null && B == null) {
        return new int[0];
      }
      int[] res = new int[m + n];
      int pa = 0, pb = 0, index = 0;
      
      while (pa < m && pb < n) {
        if (A[pa] < B[pb]) {
          res[index++] = A[pa++];
        } else {
          res[index++] = B[pb++];
        }
      }
      
      while (pa < m) {
        res[index++] = A[pa++];
      }
      
      while (pb < n) {
        res[index++] = B[pb++];
      }
      return res;
    }
  }
  