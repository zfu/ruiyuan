/**
 * Given an array with both positive and negative numbers in random order. Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.

If there are more positive/negative numbers, put them at the end of the array. The ordering of positive/negative numbers does not matter.

Assumptions:

The given array is not null.
There is no 0 in the array.
Examples:

{1, 2, 3, 4, 5, -1, -1, -1} --> {1, -1, 2, -1, 3, -1, 4, 5}  (The ordering of positive/negative numbers do not matter)
 */

public class Solution {
    public int[] interleave(int[] array) {
      // Write your solution here
      if (array == null || array.length <= 1) {
        return array;
      }
      
      int less = -1;
      for (int i = 0; i < array.length; i++) {
        if (array[i] < 0) {
          swap(array, ++less, i);
        }
      }
      
      int pos = less + 1, neg = 0;
      while (pos < array.length && neg < pos && array[neg] < 0) {
        swap(array, pos, neg);
        pos++;
        neg += 2;
      }
      return array;
    }
    
    private void swap(int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }
  
  