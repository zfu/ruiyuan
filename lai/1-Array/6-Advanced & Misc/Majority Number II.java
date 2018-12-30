/**
 * Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

Assumptions

The given array is not null
Examples

A = {1, 2, 1, 2, 1}, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, return [1]
A = {1, 2, 2, 3, 1, 3}, return []
 */

public class Solution {
    public List<Integer> majority(int[] array) {
      // Write your solution here
      List<Integer> res = new ArrayList<>();
      if (array == null || array.length == 0) {
        return res;
      }
      int num1 = 0, num2 = 0, count1 = 0, count2 = 0;
      for (int i = 0; i < array.length; i++) {
        if (array[i] == num1) {
          count1++;
        } else if (array[i] == num2) {
          count2++;
        } else if (count1 == 0) {
          num1 = array[i];
          count1++;
        } else if (count2 == 0) {
          num2 = array[i];
          count2++;
        } else {
          count1--;
          count2--;
        }
      }
      count1 = 0;
      count2 = 0;
      for (int i = 0; i < array.length; i++) {
        if (array[i] == num1) {
          count1++;
        } else if (array[i] == num2) {
          count2++;
        }
      }
      
      if (count1 > array.length / 3) {
        res.add(num1);
      }
      
      if (count2 > array.length / 3) {
        res.add(num2);
      }
      
      return res;
    }
  }
  