### [272\. Closest Binary Search Tree Value II](https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/)

Difficulty: **Hard**



Given a non-empty binary search tree and a target value, find _k_ values in the BST that are closest to the target.

**Note:**

*   Given target value is a floating point.
*   You may assume _k_ is always valid, that is: _k_ ≤ total nodes.
*   You are guaranteed to have only one unique set of _k_ values in the BST that are closest to the target.

**Example:**

```text
**Input:** root = [4,2,5,1,3], target = 3.714286, and _k_ = 2

    4
   / \
  2   5
 / \
1   3

**Output:** [4,3]```
```
**Follow up:**  
Assume that the BST is balanced, could you solve it in less than _O_(_n_) runtime (where _n_ = total nodes)?



#### Solution

**思路**
- 先inorder遍历BST
- 再用两个同向指针找到K个数,要注意的是list的remove操作是O(n),所以这个方法会造成O(n2)的时间
- 或者将中序遍历的结果放进deque,然后进行删减和添加操作,这个比较好一点,只是0(n)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        List<Integer> traverse = new ArrayList<Integer>();
        inorderTraverse(root, traverse);

        int size = traverse.size();
        for (int i = 0; i < k; i++) {
            result.add(traverse.get(i));
        }

        for (int i = k; i < size; i++) {
            int first = result.get(0);
            int cur = traverse.get(i);
            if (Math.abs((double)cur - target) < Math.abs((double)first - target)) {
                result.remove(0);
                result.add(cur);
            } else {
                break;
            }
        }
        return result;
    }

    public void inorderTraverse(TreeNode root, List<Integer> traverse) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left, traverse);
        traverse.add(root.val);
        inorderTraverse(root.right, traverse);

    }
}
```

**Follow Up**

最开始还不明白,然后把Balance BST 的图画出来就明白了
简直精妙
找predeccusor 和 successor
用pre和succ比较,如果在succ一边,那么久找到succ那边其他的小值,如果在pre这边,那么找到其他的最大值,直到找到k个

```java
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();
        // populate the predecessor and successor stacks
        Stack<TreeNode> pred = new Stack<TreeNode>();
        Stack<TreeNode> succ = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            if (target <= curr.val) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }
        while (k > 0) {
            if (pred.empty() && succ.empty()) {
                break;
            } else if (pred.empty()) {
                result.add( getSuccessor(succ) );
            } else if (succ.empty()) {
                result.add( getPredecessor(pred) );
            } else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
                result.add( getPredecessor(pred) );
            } else {
                result.add( getSuccessor(succ) );
            }
            k--;
        }
        return result;
     }

    private int getPredecessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.left;
        while (p != null) {
            st.push(p);
            p = p.right;
        }
        return popped.val;
    }

    private int getSuccessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.right;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
        return popped.val;
    }
}
```