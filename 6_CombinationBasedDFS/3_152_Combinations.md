### 152. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

**Example**
```text
Given n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4]
]
```
**Notice**
You don't need to care the order of combinations, but you should make sure the numbers in a combination are sorted.



```java
public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (k > n) {
            return result;
        }
        
        helper(result, new ArrayList<Integer>(), 1, n, k);
        return result;
    }
    
    private void helper(List<List<Integer>> result, 
                        List<Integer> list,
                        int startIndex,
                        int n,
                        int k) {
        if (list.size() == k) {
            result.add(new ArrayList(list));
            //	记得return.减少不必要的运算
            return;	
        }
        
        for (int i = startIndex; i <= n; i++) {
            list.add(i);
            helper(result, list, i + 1, n, k);
            list.remove(list.size() - 1);
        }
    }
}
```