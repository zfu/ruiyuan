/**
 * 58. 4Sum Given an array S of n integers, are there elements a, b, c, and d in
 * S such that a + b + c + d = target?
 * 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Example Given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:
 * 
 * (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2) Notice Elements in a quadruplet
 * (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d) The solution
 * set must not contain duplicate quadruplets.
 */

 
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 4){
            return result;
        }
        
        Arrays.sort(nums);
        
        if (nums[0] * 4 > target || nums[nums.length - 1] * 4 < target) {
            return result;
        }
        for(int i = 0; i < nums.length - 3; i ++){
            if(i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
                    
            for(int j = i + 1; j < nums.length - 2; j ++){
                if(j != i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }  
                 
                int left = j + 1, right = nums.length - 1;
                
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        
                        left ++;
                        right --;
                        
                        while(left < right && nums[left] == nums[left - 1]){
                            left ++;
                        }
                        
                        while(left < right && nums[right] == nums[right + 1]){
                            right --;
                        }
                    } else if(sum < target) {
                        left ++;
                    } else {
                        right --;
                    }
                }
            }
        }
        
        return result;
    }
}
