/**
 * Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.

Assumptions

The given array is not null and has length of at least 2
The order of the values in the pair does not matter
Examples

A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
 */

public class Solution {
    public List<List<Integer>> allPairs(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return new ArrayList<List<Integer>>();
      }
      List<List<Integer>> res = new ArrayList<>();
      Arrays.sort(array);
      int left = 0, right = array.length - 1;
      while (left < right) {
        int sum = array[left] + array[right];
        if (sum == target) {
          List<Integer> list = new ArrayList<>();
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
      return res;
    }
  }
  