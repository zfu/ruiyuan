/**
 * Given two sorted arrays a and b containing only integers, return two list of elements: the elements only in a but not in b, and the elements only in b but not in a.

Do it in one pass.

Assumptions:

The two given arrays are not null.
Examples:

a = {1, 2, 2, 3, 4, 5}

b = {2, 2, 2, 4, 4, 6}

The returned two lists are:

[

  [1, 3, 5],

  [2, 4, 6]  // there are two 2s in a, so there is one 2 in b not in a

]
 */

public class Solution {
    public int[][] diff(int[] a, int[] b) {
      // Write your solution here
      int[][] res = new int[2][];
      int p1 = 0, p2 = 0;
      List<Integer> l1 = new ArrayList<>();
      List<Integer> l2 = new ArrayList<>();
      while (p1 < a.length && p2 < b.length) {    
        if (a[p1] < b[p2]) {
          l1.add(a[p1]);
          p1++;
        } else if (a[p1] > b[p2]) {
          l2.add(b[p2]);
          p2++;
        } else {
          p1++;
          p2++;
        }
      }
      while (p1 < a.length) {
        l1.add(a[p1++]);
      }
      while (p2 < b.length) {
        l2.add(b[p2++]);
      }
      
      res[0] = new int[l1.size()];
      res[1] = new int[l2.size()];
      for (int i = 0; i < l1.size(); i++) {
        res[0][i] = l1.get(i);
      }
      for (int i = 0; i < l2.size(); i++) {
        res[1][i] = l2.get(i);
      }
  
      return res;
    }
  }
  