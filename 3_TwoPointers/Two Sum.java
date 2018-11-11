/**
 * 56. Two Sum Given an array of integers, find two numbers such that they add
 * up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are zero-based.
 * 
 * Example numbers=[2, 7, 11, 15], target=9
 * 
 * return [0, 1]
 * 
 * Challenge Either of the following solutions are acceptable:
 * 
 * O(n) Space, O(nlogn) Time O(n) Space, O(n) Time Notice You may assume that
 * each input would have exactly one solution
 */

public class Solution {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] res = new int[2];
        if (numbers == null || numbers.length == 0) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[] {map.get(target - numbers[i]), i};
            }
            map.put(numbers[i], i);
        }
        return res;
    }
}