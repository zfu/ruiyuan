/**
 * Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.

Assumptions

The three given arrays are not null and have length of at least 1
Examples

A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
 */

public class Solution {
    public boolean exist(int[] a, int[] b, int[] c, int target) {
      // Write your solution here
      HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
          if(a==null || b==null || c==null || a.length<1 || b.length<1 || c.length<1) {
              return false;
          }
          for(int i=0; i<c.length; i++) {
              dict.put(c[i], 1);
          }
          for(int i=0; i<a.length; i++) {
              for(int j=0; j<b.length; j++) {
                  if(dict.containsKey(target-a[i]-b[j])==true) {
                      return true;
                  }
              }
          }
          return false;
    }
  }
  

 // 不能用hashset，如果遇到duplicate，不会加入进去
public class Solution {
    public boolean exist(int[] a, int[] b, int[] c, int target) {
      // Write your solution here
      if (a == null || b == null || c == null || a.length < 1 || b.length < 1 || c.length < 1) {
        return false;
      }
      
      Set<Integer> set = new HashSet<>();
      for (int i = 0; i < c.length; i++) {
        set.add(c[i]);
      }
      
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < b.length; j++) {
          if (set.contains(target - a[i] - b[i])) {
            return true;
          }
        }
      }
    }
  }
  