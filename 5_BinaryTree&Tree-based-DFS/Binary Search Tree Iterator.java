/**
 * Description
Design an iterator over a binary search tree with the following rules:

Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.
Have you met this question in a real interview?  
Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
Challenge
Extra memory usage O(h), h is the height of the tree.

Super Star: Extra memory usage O(1)
 */

 /**
  * 用O(h)空间的做法：

理解binary search tree inorder traversal的规律：
   先找left.left.left ....left 到底，这里是加进stack.
   然后考虑parent,然后再right.

例如这题：
   stack里面top，也就是tree最左下角的node先考虑,取名rst.
   其实这个rst拿出来以后, 它也同时是最底层left null的parent，算考虑过了最底层的parent。
   最后就考虑最底层的parent.right, 也就是rst.right.

注意:
   next()其实有个while loop, 很可能是O(h).题目要求average O(1),所以也是okay的.


用O(1)空间的做法：不存stack, 时刻update current为最小值。

找下一个最小值,如果current有right child：   
   和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
   
如果current没有right child:    
    那么就要找current node的右上parent, search in BinarySearchTree from root.

注意：
   一定要确保找到的parent满足parent.left == current.
   反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
   但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。
  */


  /*	O(h) space
    核心：TreeNode next()
		主要功能一直找出最小的那个node：
		1. 如果current != null，一直找left.left.left.....就是所求结果； 
		2. 如果current == null 并且current是 current.parent的left node时，current.parent 就是所求结果，也就是myStack最上面的node；
		3. 如果current == null 并且current是 current.parent的right node时， current.parent已经被pop了，current.parent.parent是所求结果，
		也是myStack最上面的node。
		第一个while loop非常巧妙：当current不等于null时，一直查询left node，并把沿途left node存入myStack，
		一直到最小的node的left node (值为null)，然后再从stack里面读出来上一个left node.
		
		current 为结果时，再从以 current.right 为 root 的 tree 中找 next()， 所以最后要 current = current.right 
*/

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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BSTIterator {
	public Stack<TreeNode> stack = new Stack<TreeNode>();
	public TreeNode current;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
    	current = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
    	return current != null || !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
    	while (current != null) {
    		stack.push(current);
    		current = current.left;
    	}
    	TreeNode rst = stack.pop();
    	current = rst.right;
    	return rst;
    }
}

/*
    Use O(1) space, which means we will not use O(h) stack.
    To begin:
    1. hasNext()? current.val <= endNode.val to check if the tree is fully traversed.
    2. Find min via left-most: We can alwasy look for left-most to find next minimum value.
    
    3. Once left-most min is checked (name it `current`). Next min will be 2 cases:
        If current.right != null, we can keep looking for current.right's left-most child, as next min.
        Or, we need to look backwards for parent. Use binary search tree to find current's parent node.
    Note: when doing binary search for parent, make sure it satisfies parent.left = current.
    
    Because:If parent.right == current, that parent must has been visited before. In binary search tree,
    we know that parent.val < parent.right.val. We need to skip this special case, since it leads
    to ifinite loop.
*/


public class BSTIterator {
    public TreeNode root;
    public TreeNode current;
    public TreeNode endNode;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        this.root = root;
        this.current = root;
        this.endNode = root;
        
        while (endNode != null && endNode.right != null) {
            endNode = endNode.right;
        }
        while (current != null && current.left != null) {
            current = current.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return current != null && current.val <= endNode.val;
    }
    
    //@return: return next node
    public TreeNode next() {
        TreeNode rst = current;
        //current node has right child
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
        } else {//Current node does not have right child.
            current = findParent();
        }
        return rst;
    }

    //Find current's parent, where parent.left == current.
    public TreeNode findParent(){
        TreeNode node = root;
        TreeNode parent = null;
        int val = current.val;
        if (val == endNode.val) {
            return null;
        }
        while (node != null) {
            if (val < node.val) {
                parent = node;
                node = node.left;
            } else if (val > node.val) {
                node = node.right;
            } else {//node.val == current.val
                break;
            }
        }
        return parent;
    }
}

