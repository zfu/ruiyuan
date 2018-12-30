/**
 * Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

Assumptions

The given array is not null or empty
K >= 2
Examples

A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
A = {2, 1}, K = 2, return []
 */

public class Solution {
    public List<Integer> majority(int[] array, int k) {
      // Write your solution here
      List<Integer> res = new ArrayList<>();
      if (array == null || array.length == 0 || k < 2) {
        return res;
      }
      
      int size = array.length / k;
      Map<Integer, Integer> map = new HashMap<>();
      
      for (int num : array) {
        map.put(num, map.getOrDefault(num, 0) + 1);
      }
      for (Integer num : map.keySet()) {
        if (map.get(num) > size){
          res.add(num);
        }
      }
      return res;
    }
  }
  