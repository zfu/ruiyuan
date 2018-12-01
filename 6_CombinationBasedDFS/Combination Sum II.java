/*
Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.
Each number in candidates may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */

/**
 * Approach: Backtracking
 * 与 LintCode 上的 Combination Sun II 一样。
 * 具体解释可以参考：
 * https://github.com/cherryljr/LintCode/blob/master/Combination%20Sum%20II.java
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new LinkedList<>();
        }

        List<List<Integer>> rst = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new LinkedList<>(), rst);
        return rst;
    }

    private void dfs(int[] candidates, int index, int remainTraget, LinkedList<Integer> list, List<List<Integer>> rst) {
        if (remainTraget == 0) {
            rst.add(new LinkedList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 当 remainTarget < candidates[i] 时,可以直接结束递归
            // 以起到加速效果
            if (remainTraget < candidates[i]) {
                break;
            }
            if (i != index && candidates[i - 1] == candidates[i]) {
                continue;
            }

            list.add(candidates[i]);
            dfs(candidates, i + 1, remainTraget - candidates[i], list, rst);
            list.remove(list.size() - 1);
        }
    }
}

/*
排列组合题（更准确来说是组合题） 递归 / DFS
和Combination Sum I 类似.      
不过这题不同的是在于每个元素不能被重复使用，反而与Recursive Template更加符合。
即令 startIndex + 1 即可，这里已经在Combination Sum I 中分析过了，不再赘述。

```
/*
Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used once in the combination.
Note
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
Example
For example, given candidate set 10,1,6,7,2,1,5 and target 8,
A solution set is: 
[1,7]
[1,2,5]
[2,6]
[1,1,6]
Tags Expand 
Backtracking Array
Thinking process:
Exact same idea as in Combination Sum I. The difference is, 
cannot reuse the current index in nums. Instead, in helper() function, use index of i + 1
*/

public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (num == null || num.length == 0) {
            return result;
        }
        
        Arrays.sort(num);
        combinationSum2Helper(result, new ArrayList<Integer>(), num, target, 0);
        return result;
    }
    
    private void combinationSum2Helper(List<List<Integer>> result,
                                       List<Integer> list,
                                       int[] num,
                                       int remainTarget,
                                       int startIndex) {
        if (remainTarget == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = startIndex; i < num.length; i++) {
            if (num[i] > remainTarget) {
                break;
            }
            if (i > 0 && i != startIndex && num[i - 1] == num[i]) {
                continue;
            }
            
            list.add(num[i]);
            combinationSum2Helper(result, list, num, 
            remainTarget - num[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}