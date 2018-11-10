/**
 * 625. Partition Array II Partition an unsorted integer array into three parts:
 * 
 * 1.The front part < low 
 * 2.The middle part >= low & <= high 
 * 3.The tail part > high
 * Return any of the possible solutions.
 * 
 * Example Given [4,3,4,1,2,3,1,2], and low = 2 and high = 3.
 * 
 * Change to [1,1,2,3,2,3,4,4].
 * 
 * ([1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not)
 * 
 * Challenge Do it in place. 
 * Do it in one pass (one loop). 
 * Notice low <= high in
 * all testcases.
 */

public class Solution {
    /*
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: 
     
    public void partition2(int[] nums, int low, int high) {
        // write your code here
        int left = -1, right = nums.length;
        int i = 0;
        while (i < right) {
            if (nums[i] < low) {
                swap(nums, i++, ++left);
            } else if (nums[i] > high) {
                swap(nums, i, --right);
            } else {
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    */
    public void partition2(int[] nums, int low, int high) {
        // Write your code here
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int pl = 0, pr = nums.length - 1;
        int i = 0;
        while (i <= pr) {
            if (nums[i] < low) {
                swap(nums, pl, i);
                pl++;
                i++;
            } else if (nums[i] > high) {
                swap(nums, pr, i);
                pr--;
            } else {
                i ++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}