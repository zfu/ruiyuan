/**
 * Given three sorted integer arrays, pick one element from each of them, what is the min value of |x - y| + |y - z| + |z - x|.

Assumptions:

The given three arrays are not null or empty.
Examples:

a = {1, 2, 3}

b = {4, 5}

c = {3, 4}

The minimum value is |3 - 4| + |4 - 4| + |4 - 3| = 2
 */

public class Solution {
    public int minDistance(int[] a, int[] b, int[] c) {
      // Write your solution here
      if (a == null || b == null || c == null || a.length == 0 || b.length == 0|| c.length == 0) {
        return 0;
      }
      int aLen = a.length, pa = 0;
      int bLen = b.length, pb = 0;
      int cLen = c.length, pc = 0;
      int diff = Integer.MAX_VALUE;
      int res = Integer.MAX_VALUE;
      while (pa < aLen && pb < bLen && pc < cLen) {
        int min = Math.min(Math.min(a[pa], b[pb]), c[pc]);
        int max = Math.max(Math.max(a[pa], b[pb]), c[pc]);
        
        //diff = Math.abs(max - min) < diff ? Math.abs(max - min) : diff;
        if (Math.abs(max - min) < diff) {
          res = Math.abs(a[pa] - b[pb]) + Math.abs(b[pb] - c[pc]) + Math.abs(c[pc] - a[pa]);
          diff = Math.abs(max - min);
        }
        
        if (min == a[pa]) {
          pa++;
        } else if (min == b[pb]) {
          pb++;
        } else {
          pc++;
        }
      }
      return res;
    }
  }
  