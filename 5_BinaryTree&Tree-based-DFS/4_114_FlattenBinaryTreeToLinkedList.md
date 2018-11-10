### [114\. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/)

Difficulty: **Medium**



Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

```
    1
   / \
  2   5
 / \   \
3   4   6
```

The flattened tree should look like:

```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```



#### Solution

**Method 1: recursive**

Flatten the left child of root and the right child of root.

Set root.right with it's left child and set root.left with null.

From root go to the last right node.

Set its right with root's right child.

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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        flatten(root.left);
        flatten(root.right);
        
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = right;
    }
}
```

**Method 2: iterative **

If current node has right child, push it into the stack. Connect its left child to its right and set its left child with null.

If current node does not have a left child, we pop the node from the stack and attach to its right.

We set the current node to its right child, and do this all over again.

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
    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                curr.right = curr.left;
                curr.left = null;
            }
            else if (!stack.isEmpty()) {
                curr.right = stack.pop();
            }
            curr = curr.right;
        }
    }
}
```