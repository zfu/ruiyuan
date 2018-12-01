/**
 * Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

The node has an extra attribute parent which point to the father of itself. The root's parent is null.

Have you met this question in a real interview?  
Example
For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7
 */

 /**
  * 这个题有个奇葩的地方，每个node还有一个parent。 所以可以自底向上.
普通做法：2 lists。
自底向上。利用parent往root方向返回。   

  */

  /*
    题目提供了parent相较于Lowest Common Ancestor I没有parent更加简单.
    从头遍历两个list. 
    1. 一旦分叉，分叉口的parent就是要找的。
    2. 如果两个list一直相等，那他们就是同一个node
    border case: if both null, just return null.
    if only 1 is null, let one of the node be ancestor; since null can be anywhere.
*/

public class Solution {
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                    ParentTreeNode A,ParentTreeNode B) {
        if (root == null || (A == null && B == null)) {
            return null;
        } else if (A == null || B == null) {
            return A == null ? B : A;
        }
        //Populate listA, listB
        ArrayList<ParentTreeNode> listA = new ArrayList<ParentTreeNode>();
        ArrayList<ParentTreeNode> listB = new ArrayList<ParentTreeNode>();

        while (A != root) {
            listA.add(0, A);
            A = A.parent;
        }
        listA.add(0, A);
        while (B != root) {
            listB.add(0, B);
            B = B.parent;
        }
        listB.add(0, B);

        int size = listA.size() > listB.size() ? listB.size() : listA.size();

        for (int i = 0; i < size; i++) {
            if (listA.get(i) != listB.get(i)) {
                return listA.get(i).parent;
            }
        }

        return listA.get(size - 1);
    }
}