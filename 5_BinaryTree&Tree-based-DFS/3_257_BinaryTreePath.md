### [257\. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/description/)

Difficulty: **Easy**



Given a binary tree, return all root-to-leaf paths.

**Note:** A leaf is a node with no children.

**Example:**

```
**Input:**

   1
 /   \
2     3
 \
  5

**Output:** ["1->2->5", "1->3"]

**Explanation:** All root-to-leaf paths are: 1->2->5, 1->3
```



#### Solution

**Method 1: DFS**

We use DFS to traverse the tree and at the same time we maintain a path of values.

When we reach a leaf, we add the current path to the result.

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helper(result, root, "" + root.val);
        return result;
    }
    
    public void helper(List<String> result, TreeNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(str);
            return;
        }
        if (root.left != null) {
            helper(result, root.left, str + "->" + root.left.val);
        }
        if (root.right != null) {
            helper(result, root.right, str + "->" + root.right.val);
        }
    }
}
```

**Method 2: BFS**

We use two queue to implement the BFS.

One queue stores the nodes we are traversing.

The other queue stores the path to that node.

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> paths = new LinkedList<>();
        nodes.offer(root);
        paths.offer("" + root.val);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            String path = paths.poll();
            if (node.left == null && node.right == null) {
                res.add(path);
                continue;
            }
            if (node.left != null) {
                nodes.offer(node.left);
                paths.offer(path + "->" + node.left.val);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                paths.offer(path + "->" + node.right.val);
            }
        }
        return res;
    }
}
```

**Method 3: DFS iterative**

Use two stacks.

Implementation is similar to Method 2.

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> nodes = new Stack<>();
        Stack<String> paths = new Stack<>();
        nodes.push(root);
        paths.push("" + root.val);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            String path = paths.pop();
            if (node.left == null && node.right == null) {
                res.add(path);
                continue;
            }
            if (node.left != null) {
                nodes.push(node.left);
                paths.push(path + "->" + node.left.val);
            }
            if (node.right != null) {
                nodes.push(node.right);
                paths.push(path + "->" + node.right.val);
            }
        }
        return res;
    }
}
```