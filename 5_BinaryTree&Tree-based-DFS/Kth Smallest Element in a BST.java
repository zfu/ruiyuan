/*
Description
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Have you met this question in a real interview?  
Example
Given root = {1,#,2}, k = 2, return 2.

Challenge
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
*/
/**
 * 该题应用到了这么一个知识点：
    搜索二叉树按照中序遍历得到的序列，一定是一个递增序列。
所以可以利用这点来求得 第k小的数。
这里为了方便，我们使用了非递归方法来进行中序遍历。
解法思想同：Validate Binary Search Tree
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (--k == 0) {
                break;
            }
            curr = curr.right;
        }
        return curr.val;
    }
}