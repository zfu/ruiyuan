/**
 * Given a sorted integer array, remove duplicate elements. 
 * For each group of elements with the same value keep at most two of them. 
 * Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
 */

public class Solution {
    public int[] dedup(int[] array) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return array;
      }
      
      int left = 1;
      for (int right = 2; right < array.length; right++) {
        if (array[left - 1] != array[right]) {
          array[++left] = array[right];
        }
      }
      
      return Arrays.copyOf(array, left + 1);
    }
  }
  