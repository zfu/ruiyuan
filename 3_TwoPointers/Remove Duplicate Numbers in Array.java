/**
 * 521. Remove Duplicate Numbers in Array
Given an array of integers, remove the duplicate numbers in it.

You should:

1. Do it in place in the array.
2. Move the unique numbers to the front of the array.
3. Return the total number of the unique numbers.
Example
Given nums = [1,3,1,4,4,2], you should:

1. Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
2. Return the number of unique integers in nums => 4.
Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.

Challenge
Do it in O(n) time complexity.
Do it in O(nlogn) time without extra space.
Notice
You don't need to keep the original order of the integers.
 */


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            if (set.add(nums[left])){
                left++;
            } else {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                right--;
            }
        }
        return start;
    }
}