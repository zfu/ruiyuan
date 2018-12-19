/**
 * Find the pair of elements in a given array that sum to a value that is closest to the given target number. Return the values of the two numbers.

Assumptions

The given array is not null and has length of at least 2
Examples

A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
 */

public class Solution {
    public List<Integer> closest(int[] array, int target) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return new ArrayList<Integer>();
      }
      
      List<Integer> res = new ArrayList<>();
      if (array.length == 2) {
        res.add(array[0]);
        res.add(array[1]);
        return res;
      }
      Arrays.sort(array);
      int left = 0, right = array.length - 1;
      int min = Integer.MAX_VALUE;
      Integer[] arr = new Integer[2]; 
      while (left < right) {
        int sum = array[left] + array[right];
        int diff = Math.abs(sum - target);
        if (diff < min) {
          arr[0] = array[left];
          arr[1] = array[right];
          min = diff;
        }
        if (diff == 0) {
          res = Arrays.asList(arr);
          return res;
        } else if (sum < target) {
          left++;
        } else {
          right--;
        }
      }
      res = Arrays.asList(arr);
      return res;
    }
  }
  