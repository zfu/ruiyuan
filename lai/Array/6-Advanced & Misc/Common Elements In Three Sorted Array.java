/**
 * Find all common elements in 3 sorted arrays.

Assumptions

The 3 given sorted arrays are not null
There could be duplicate elements in each of the arrays
Examples

A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
 */

public class Solution {
    public List<Integer> common(int[] a, int[] b, int[] c) {
      // Write your solution here
      List<Integer> res = new ArrayList<>();
      if (a == null || b == null || c == null) {
        return res;
      }
      
      int i = 0, j = 0, k = 0;
      while (i < a.length && j < b.length && k < c.length) {
        if (a[i] == b[j] && a[i] == c[k]) {
          res.add(a[i]);
          i++;
          j++;
          k++;
        } else if (a[i] <= b[j] && a[i] <= c[k]) {
          i++;
        } else if (b[j] <= a[i] && b[j] <= c[k]) {
          j++;
        } else {
          k++;
        }
      }
      return res;
    }
  }
  