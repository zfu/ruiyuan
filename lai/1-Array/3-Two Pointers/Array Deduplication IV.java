/**
 * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

Do this in-place, using the left side of the original array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
 */
public class Solution {
    public int[] dedup(int[] array) {
      if (array.length <= 1) {
        return array;
      }
      
      int slow = -1;
      int fast = 0;
      while (fast < array.length) {
        if (slow == -1 || array[fast] != array[slow]) {
          array[++slow] = array[fast++];
        }
        else if (array[fast] == array[slow]) {
          while (fast < array.length && array[fast] == array[slow]) {
            fast++;
          }
          slow--;
        }
      }
      return Arrays.copyOf(array, slow + 1);
    }
  }

  
public class Solution {
    public int[] dedup(int[] array) {
      // Write your solution here
      if (array == null || array.length <= 1) {
        return array;
      }
      
      Stack<Integer> stack = new Stack<Integer>();
      for (int i = 0; i < array.length; ) {
        if (!stack.isEmpty() && stack.peek() == array[i]) {
          while (i < array.length && array[i] == stack.peek()) {
            i++;
          }
          stack.pop();
        } else {
          stack.push(array[i++]);
        }
      }
      
      if (stack.isEmpty()) {
        return new int[0];
      }
      
      int[] res = new int[stack.size()];
      for (int j = res.length - 1; j >= 0; j--) {
        res[j] = stack.pop();
      }
      return res;
    }
  }
  