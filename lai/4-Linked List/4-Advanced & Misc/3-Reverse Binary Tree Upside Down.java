/**
 * 
 Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes as the root.

Examples

        1

      /    \

    2        5

  /   \

3      4

is reversed to

        3

      /    \

    2        4

  /   \

1      5

How is the binary tree represented?

We use the pre order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

    1

  /   \

 2     3

      /

    4


 */

 /**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
    public TreeNode reverse(TreeNode root) {
      // Write your solution here
      if(root==null || root.left==null) {
              return root;
          }
          TreeNode subtree = reverse(root.left);
          root.left.left = root;
          root.left.right = root.right;
          root.left = null;
          root.right = null;
          return subtree;
    }
  }
  