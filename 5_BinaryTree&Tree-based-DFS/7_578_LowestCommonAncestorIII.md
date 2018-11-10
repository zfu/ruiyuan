### Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist.

**Notice**
node A or node B may not exist in tree.


**Example**
```text
For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7
```

**思路**
这题要注意A和B不一定都在子树里。
用一个ResultType来记录A和B是否在子树里存在，以及LCA node。
用分治法。
1：如果A或B在root上，那么LCA就在root上。
2：如果左子树和右子树都有LCA，那么也说明当前LCA在root上。
3：如果只有左边有LCA，那么LCA就在左边。
4：如果只有右边有LCA，那么LCA就在右边。
最后递归结束以后，需要判断是否A和B都存在。

这题和 LCA 原题的区别主要是要找的 A 和 B 可能并不存在树里。所以我们要做出这两个改变

用全局变量把 A 和 B 是否找到保存起来。最后在 main function 里面要查看是否都找到
当 root 等于 A 或者 B 时不能直接返回root了。原题可以直接返回是因为两个 node 是保证存在的所以这情况下 LCA 一定是 root。
现在 root 等于 A 或者 B 我们还是要继续往下找是否存在另外的一个
不用 ResultType 的一个好处是：如果面试的时候出了一个原题，然后问这题做 follow up。如果从头开始写 result type 代码改动会比较大。一是比较容易写错，二是时间可能会不够。

这个方法只需要增加两个全局变量并且改动 LCA 原题的代码两行即可。
```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
class ResultType {
    public boolean a_exist, b_exist;
    public TreeNode node;
    ResultType(boolean a, boolean b, TreeNode n) {
        a_exist = a;
        b_exist = b;
        node = n;
    }
}

public class Solution {
    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        ResultType rt = helper(root, A, B);
        if (rt.a_exist && rt.b_exist)
            return rt.node;
        else
            return null;
    }

    public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null)
            return new ResultType(false, false, null);
            
        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);
        
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
        
        if (root == A || root == B)
            return new ResultType(a_exist, b_exist, root);

        if (left_rt.node != null && right_rt.node != null)
            return new ResultType(a_exist, b_exist, root);
        if (left_rt.node != null)
            return new ResultType(a_exist, b_exist, left_rt.node);
        if (right_rt.node != null)
            return new ResultType(a_exist, b_exist, right_rt.node);

        return new ResultType(a_exist, b_exist, null);
    }
}
```
