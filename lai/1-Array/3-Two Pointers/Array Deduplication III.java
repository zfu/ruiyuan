/**
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1}
[[1,1,2,3,3,4,5,5,5]] -> [[2,4]]
 */
public class Solution {
    public int[] dedup(int[] array) {
      if (array.length <= 1) {
        return array;
      }
      
      int slow = 0;
      int end = 0;
      while (end < array.length) {
        int start = end;
        while (end < array.length && array[end] == array[start]) {
          end++;
        }
        if (end - start == 1) {
          array[slow++] = array[start];
        }
      }
  
      return Arrays.copyOf(array, slow);
    }
  }
  
public class Solution {
    public int[] dedup(int[] array) {
      // Write your solution here
      if (array == null || array.length <= 1) {
        return array;
      }
      
      int left = 0;
      for (int p1 = 0, p2 = 0; p1 < array.length;) {
        if (array[p1] == array[p2]) {
          while (p2 < array.length && array[p1] == array[p2]) {
            p2++;
          }
        }
        
        if (p1 + 1 < p2) {
          p1 = p2;
        } else {
          array[left++] = array[p1++];
        }
      }
      
      return Arrays.copyOf(array, left);
    }
  }
  