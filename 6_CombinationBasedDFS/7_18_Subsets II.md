### 18. Subsets II
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

**Example**
```text
Input: [1,2,2]
Output:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Challenge
Can you do it in both recursively and iteratively?

Notice
Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.
```

```java
递归：找准需要pass along的几个数据结构。    

和Subset I类似，先sort input, 然后递归。但是input可能有duplicates. 
   
为了除去duplicated result, 如果在递归里面用results.contains(),就是O(n), which makes overall O(n^2). 

这里有个基于sorted array的技巧：    
因为我们有mark index。 一旦for loop里面的i != startIndex，并且nums[i] == nums[i-1],说明x = nums[i-1]已经用过，不需要再用一次：     
[a,x1,x2]，x1==x2    
i == startIndex -> [a,x1]    
i == startIndex + 1 -> [a,x2]. 我们要skip这一种。

如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。
class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return null;
        }
        
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        subsetHelper(new ArrayList<Integer>(), nums, 0, results);
        
        return results;
    }
    
    private void subsetHelper(ArrayList<Integer> subset,
                              int[] nums, 
                              int startIndex,
                              ArrayList<ArrayList<Integer>> results) {
        results.add(new ArrayList<Integer>(subset));
        
        //  i表示当前loop要取的元素的下标，startIndex表示从该元素开始取
        for (int i = startIndex; i < nums.length; i++) {
        	//	相比于Subset I,该程序只在这里添加了一个判断条件，这边体现出了模板的应用
            if (i > 0 && i != startIndex && nums[i - 1] == nums[i]) {		
                continue;
            }
             /*
            上面的判断主要是为了去除重复元素影响。
            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
            不应该让后面的2使用。
            */
            
            subset.add(nums[i]);
            // 注意这里递归传入的是i + 1表示startIndex从下一个位置开始
            subsetHelper(subset, nums, i + 1, results);
            subset.remove(subset.size() - 1);
        }
    }
}
```