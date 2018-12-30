/**
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 3}
 */

public class Solution {
    public int[] dedup(int[] array) {
      // Write your solution here
      if (array == null || array.length == 0) {
        return new int[0];
      }
      
      int left = 0;
      for (int right = 0; right < array.length; right++) {
        if (array[left] != array[right]) {
          array[++left] = array[right];
        }
      }
      
      return Arrays.copyOf(array, left + 1);
    }
  }
  