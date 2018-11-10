### [270\. Closest Binary Search Tree Value](https://leetcode.com/problems/closest-binary-search-tree-value/description/)

Difficulty: **Easy**



Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

**Note:**

*   Given target value is a floating point.
*   You are guaranteed to have only one unique value in the BST that is closest to the target.

**Example:**

```
**Input:** root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

**Output:** 4
```



#### Solution

- 分治(因为是BST所以每次保留一边就行)
- O(logn)
- BST不用一来就准备利用中序遍历，也可以根据二分呀，每次舍弃一半子树，会更快

Language: **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // Iterative
    public int closestValue(TreeNode root, double target) {
        int closestVal = root.val; 
        while(root != null){ 
            //update closestVal if the current value is closer to target
            closestVal = (Math.abs(target - root.val) < Math.abs(target - closestVal))? root.val : closestVal;
            if(closestVal == target){   //already find the best result
                return closestVal;
            }
            root = (root.val > target)? root.left: root.right;   //binary search
        }
        return closestVal;
  }

    // Recursive
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}
```