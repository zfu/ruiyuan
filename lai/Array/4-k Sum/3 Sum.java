/**
 * Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

Assumptions

The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter
Examples

A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 */

public class Solution {
    public List<List<Integer>> allTriples(int[] array, int target) {
      // Write your solution here
      List<List<Integer>> res = new ArrayList<>();
      if (array == null || array.length < 3) {
        return res;
      }
      Arrays.sort(array);
      if (3 * array[0] > target || 3 * array[array.length - 1] < target) {
        return res;
      }
      for (int i = 0; i < array.length - 2; i++) {
        if (i != 0 && array[i] == array[i - 1]) {
          continue;
        }
        int left = i + 1, right = array.length - 1;
        while (left < right) {
          int sum = array[i] + array[left] + array[right];
          if (sum == target) {
            List<Integer> list = new ArrayList<>();
            list.add(array[i]);
            list.add(array[left]);
            list.add(array[right]);
            res.add(list);
            left++;
            right--;
            
            while (left < right && array[left] == array[left - 1]) {
              left++;
            }
            
            while (left < right && array[right] == array[right + 1]) {
              right--;
            }
          } else if (sum < target) {
            left++;
          } else {
            right--;
          }
        }
      }
      return res;
    }
  }
  