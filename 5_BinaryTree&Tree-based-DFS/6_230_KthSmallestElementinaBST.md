### [230\. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/)

Difficulty: **Medium**



Given a binary search tree, write a function `kthSmallest` to find the **k**th smallest element in it.

**Note:**  
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

**Example 1:**

```text
**Input:** root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
**Output:** 1```

**Example 2:**

**Input:** root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
**Output:** 3
```

**Follow up:**  
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?



#### Solution

The idea is to use in-order traversal and find the k-th element.

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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }
}

```

**思路1： using inorder traversal by recursion, keep a global var count to track kth element.**
Inorder we will first get the min(leftmost node), then 2nd min (parent node of min),
then 3rd min(right node of 2nd min)....
Complexity: O(N) - time O(N) - stack

```java
public class Solution {
    int count = 0;
    int result = -1;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }
    public void traverse(TreeNode root, int k) {
        if(root == null) return;
        traverse(root.left, k);
        count++;
        if(count == k)
            result = root.val;
        traverse(root.right, k);
    }    
}
```

**思路2： using iteration to store the path to current min node into a stack.**
When we pop node, we check if it has left node and add till the leftmost node all the way to leftmost.
```java
public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> st = new LinkedList<>();
        int res = 0;
        while(root != null){//find the min - leftmostnode
            st.push(root);
            root = root.left;
        }
        while(!st.isEmpty() && k > 0){
            TreeNode cur = st.pop();
            res = cur.val;
            k--;
            cur = cur.right;
            while(cur != null){//keep adding till the leftmost node of right node
                st.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
```