### [173\. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/description/)

Difficulty: **Medium**



Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling `next()` will return the next smallest number in the BST.

**Note:** `next()` and `hasNext()` should run in average O(1) time and uses O(_h_) memory, where _h_ is the height of the tree.



#### Solution

Language: **Java**

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int val = node.val;
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
```


思路：由于是求最小的数，有点像inorder successor。 一开始加root 和最左的branch(因为最小值在leftmost那里)。每次pop一个中间的node,要把下面的右树补上,只需要右node及它的左枝（下一个最小的数是右node的leftmost one）

```java
public class BSTIterator {
    private Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new LinkedList<TreeNode>();// you can also inialize at var declaration
        pushNode(root);//adding the node leftbranch to get the min
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pop();
        int res = cur.val;
        pushNode(cur.right);//when remove a node, need to add its right node's left branch
        return res;
    }
    /*adding the node with its left branch*/
    public void pushNode(TreeNode root){
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
 ```