import java.util.Map;

/**
 * 610. Two Sum - Difference equals to target Given an array of integers, find
 * two numbers that their difference equals to a target value. where index1 must
 * be less than index2. Please note that your returned answers (both index1 and
 * index2) are NOT zero-based.
 * 
 * Example Given nums = [2, 7, 15, 24], target = 5 return [1, 2] (7 - 2 = 5)
 * 
 * Notice It's guaranteed there is only one available solution
 */

 /**
  * We have to consider about two scenarios that sum and difference when asking about the difference.
  */
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        // write your code here
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] + target;
            int diff = nums[i] - target;
            if (map.containsKey(diff)) {
                res[0] = map.get(diff) + 1;
                res[1] = i + 1;
                return res;
            } else if (map.containsKey(sum)) {
                res[0] = map.get(sum) + 1;
                res[1] = i + 1;
                return res;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}