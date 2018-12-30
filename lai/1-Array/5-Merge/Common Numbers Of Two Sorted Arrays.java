/**
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

Assumptions

In each of the two sorted arrays, there could be duplicate numbers.
Both two arrays are not null.
Examples

A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]  
 */

public class Solution {
    public List<Integer> common(List<Integer> A, List<Integer> B) {
      // Write your solution here
      List<Integer> res = new ArrayList<>();
      if (A == null || B == null) {
        return res;
      }
      int pa = 0, pb = 0;
      while (pa < A.size() && pb < B.size()) {
        if (A.get(pa) < B.get(pb)) {
          pa++;
        } else if (A.get(pa) > B.get(pb)) {
          pb++;
        } else {
          res.add(A.get(pa));
          pa++;
          pb++;
        }
      }
      return res;
    }
  }
  