### [297\. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)

Difficulty: **Hard**



Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

**Example: **

```
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as `"[1,2,3,null,null,4,5]"`
```

**Clarification:** The above format is the same as . You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

**Note: **Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.



#### Solution

Language: **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
​
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
    }
​
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
    }
}
​
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```
```java
/*
Description
Design an algorithm and write code to serialize and deserialize a binary tree. 
Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

Notice
There is no limit of how you deserialize or serialize a binary tree, 
LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.

Example
An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:
  3
 / \
9  20
  /  \
 15   7
Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.
You can use other method to do serializaiton and deserialization.

Tags 
Binary Tree Uber Yahoo Microsoft
*/

/**
 * Serialize means we need to convert the tree into a String to restore it.
 * But we can design our own algorithm to serialize a binary tree to a string and
 * guarantee that it could be deserialize.
 * So we support two algorithms here.
 *  1. Level Order Traversal / BFS
 *  2. PreOrder Traversal
 */

/**
 * Approach 1: Level Order Traversal (Using Queue)
 * For serializaiton, it just like binary tree level order traversal.
 * We can write it just like it.(or BFS Template).
 * https://github.com/cherryljr/LintCode/blob/master/BFS%20Template.java
 * We use "#" to denote null node and split node with " ", and use a StringBuilder to store the traversal result.
 * For deserialization, we use a Queue to store the level-order traversal and since we have "#" as null node,
 * so we know exactly how to where to end building subtress.
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
 */
public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("# ");    // "#" denote the null nodes.
                continue;
            }
            sb.append(node.val + " ");  // Using space to split nodes.
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by system,
     * it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] values = data.split(" ");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode parent = queue.poll();
            if (!values[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                queue.offer(left);
            }
            if (!values[++i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                queue.offer(right);
            }
        }

        return root;
    }
}

/**
 * Approach 2: PreOrder Traversal (Using Stack)
 * For serializaiton, it just like binary tree preorder traversal.
 * We can write it just like it.
 * https://github.com/cherryljr/LintCode/blob/master/Binary%20Tree%20Preorder%20Traversal.java
 * We use "#" to denote null node and split node with " ", and use a StringBuilder to store the traversal result.
 * For deserialization, we use a Queue to store the pre-order traversal and since we have "#" as null node,
 * so we know exactly how to where to end building subtress.
 */
public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
//        buildString(root, sb);
//        return sb.toString();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr == null) {
                sb.append("# ");
                continue;
            }
            sb.append(curr.val + " ");
            stack.push(curr.right);
            stack.push(curr.left);
        }

        return sb.toString();
    }

    // Traverse it (Using recursion)
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("# ");
        } else {
            sb.append(node.val + " ");
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(" ")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals("#")) {
            return null;
        }
        else {
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
```


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


Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Solution
Approach 1: Depth First Search (DFS)
Intuition


The serialization of a Binary Search Tree is essentially to encode its values and more importantly its structure. One can traverse the tree to accomplish the above task. And it is well know that we have two general strategies to do so:

Breadth First Search (BFS)

We scan through the tree level by level, following the order of height, from top to bottom. The nodes on higher level would be visited before the ones with lower levels.

Depth First Search (DFS)

In this strategy, we adopt the depth as the priority, so that one would start from a root and reach all the way down to certain leaf, and then back to root to reach another branch.

The DFS strategy can further be distinguished as preorder, inorder, and postorder depending on the relative order among the root node, left node and right node.

In this task, however, the DFS strategy is more adapted for our needs, since the linkage among the adjacent nodes is naturally encoded in the order, which is rather helpful for the later task of deserialization.

Therefore, in this solution, we demonstrate an example with the preorder DFS strategy. One can check out more tutorial about Binary Search Tree on the LeetCode Explore.

Algorithm

First of all, here is the definition of the TreeNode which we would use in the following implementation.

```java
  /* Definition for a binary tree node. */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
```
  The preorder DFS traverse follows recursively the order of
root -> left subtree -> right subtree.

As an example, let's serialize the following tree. Note that serialization contains information about the node values as well as the information about the tree structure.


We start from the root, node 1, the serialization string is 1,. Then we jump to its left subtree with the root node 2, and the serialization string becomes 1,2,. Now starting from node 2, we visit its left node 3 (1,2,3,None,None,) and right node 4 (1,2,3,None,None,4,None,None) sequentially. Note that None,None, appears for each leaf to mark the absence of left and right child node, this is how we save the tree structure during the serialization. And finally, we get back to the root node 1 and visit its right subtree which happens to be a leaf node 5. Finally, the serialization string is done as 1,2,3,None,None,4,None,None,5,None,None,.

```java
  // Serialization
  public class Codec {
    public String rserialize(TreeNode root, String str) {
      // Recursive serialization.
      if (root == null) {
        str += "null,";
      } else {
        str += str.valueOf(root.val) + ",";
        str = rserialize(root.left, str);
        str = rserialize(root.right, str);
      }
      return str;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      return rserialize(root, "");
    }
  };

```

Now let's deserialize the serialization string constructed above 1,2,3,None,None,4,None,None,5,None,None,. It goes along the string, initiate the node value and then calls itself to construct its left and right child nodes.

```java
  public class Codec {
    public TreeNode rdeserialize(List<String> l) {
      // Recursive deserialization.
      if (l.get(0).equals("null")) {
        l.remove(0);
        return null;
      }

      TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
      l.remove(0);
      root.left = rdeserialize(l);
      root.right = rdeserialize(l);

      return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      String[] data_array = data.split(",");
      List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
      return rdeserialize(data_list);
    }
  };
  ```

  