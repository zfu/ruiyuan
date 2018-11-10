### [102\. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/)

Difficulty: **Medium**



Given a binary tree, return the _level order_ traversal of its nodes' values. (ie, from left to right, level by level).

For example:  
Given binary tree `[3,9,20,null,null,15,7]`,  

```
    3
   / \
  9  20
    /  \
   15   7
```

return its level order traversal as:  

```
[
  [3],
  [9,20],
  [15,7]
]
```



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
class Solution {
    Solution 1: BFS O(n); O(w)
    /**
     * BFS O(n);O(w)
     * Since each time we need to traverse each level of the tree, we can use BFS to solve this problem.
     * 1.Use Queue to store nodes of each level.
     * 2.Traverse one level at each iteration instead of regular BFS, since result needs to be stored in List-List.
     * 3.By getting the size of the queue, we know how many nodes in current level.
     * 4.If left or right child of current node is not null, add it to the queue.
     * 5.Add value of nodes of each level into the result.
     */    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 1.add start node into queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 2.while queue is not empty
        while (!queue.isEmpty()) {
            // 3.level x -> x + 1
            List<Integer> curLevel = new ArrayList<>(); //can be replaced by deep copy, but will lead to low efficiency
            int size = queue.size();
            for (int i = 0; i < size; i++) { //level x
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left); //level x + 1
                }
                if (node.right != null) {
                    queue.add(node.right); //level x + 1
                }
            }
            res.add(curLevel); // no deep copy
        }
        return res;
    }
}
```

```java
//Solution 2: DFS O(n); O(h)
    public List<List<Integer>> levelOrderB(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }
```

```java

//	version 1: BFS Use One Queue (Best way)	与BFS模板的程序代码相同
//	参见BFS Template.java 里面有对于BFS算法的详细解析与注意点说明
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> rst = new ArrayList();
                
        if (root == null) {
            return rst;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            rst.add(level);
        }
        
        return rst;
    }
}

// version 2:  DFS
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        
        if (root == null) {
            return results;
        }
        
        int maxLevel = 0;
        while (true) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxLevel);
            if (level.size() == 0) {
                break;
            }
            
            results.add(level);
            maxLevel++;
        }
        
        return results;
    }
    
    private void dfs(TreeNode root,
                     ArrayList<Integer> level,
                     int curtLevel,
                     int maxLevel) {
        if (root == null || curtLevel > maxLevel) {
            return;
        }
        
        if (curtLevel == maxLevel) {
            level.add(root.val);
            return;
        }
        
        dfs(root.left, level, curtLevel + 1, maxLevel);
        dfs(root.right, level, curtLevel + 1, maxLevel);
    }
}


// version 3: BFS. two queues
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        
        ArrayList<TreeNode> Q1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> Q2 = new ArrayList<TreeNode>();

        Q1.add(root);
        while (Q1.size() != 0) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for (int i = 0; i < Q1.size(); i++) {
                TreeNode node = Q1.get(i);
                level.add(node.val);
                if (node.left != null) {
                    Q2.add(node.left);
                }
                if (node.right != null) {
                    Q2.add(node.right);
                }
            }
            
            // swap q1 and q2
            ArrayList<TreeNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;
            
            // add to result
            result.add(level);
        }
        
        return result;
    }
}

// version 4: BFS, queue with dummy node
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);
        Q.offer(null); // dummy node
        
        ArrayList<Integer> level = new ArrayList<Integer>();
        while (!Q.isEmpty()) {
            TreeNode node = Q.poll();
            if (node == null) {
                if (level.size() == 0) {
                    break;
                }
                result.add(level);
                level = new ArrayList<Integer>();
                Q.offer(null); // add a new dummy node
                continue;
            }
            
            level.add(node.val);
            if (node.left != null) {
                Q.offer(node.left);
            }
            if (node.right != null) {
                Q.offer(node.right);
            }
        }
        
        return result;
    }
}
```


#### Follow Up:
1.打印出每层最大的两个数字，如果不到2个，就打印null.
```java
    public List<List<Integer>> levelOrderC(TreeNode root) { //BFS
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 1.add start node into queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 2.while queue is not empty
        while (!queue.isEmpty()) {
            // 3.level x -> x + 1
            // List<Integer> curLevel = new ArrayList<>(); //can be replaced by deep copy, but will lead to low efficiency
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            int secondMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) { //level x
                TreeNode head = queue.poll();
                // curLevel.add(head.val);
                if (head.val > max) {
                    secondMax = max;
                    max = head.val;
                } else if (head.val > secondMax) {
                    secondMax = head.val;
                }
                // System.out.print(head.val + " ");
                if (head.left != null) {
                    queue.add(head.left); //level x + 1
                }
                if (head.right != null) {
                    queue.add(head.right); //level x + 1
                }
            }
            System.out.println(max == Integer.MIN_VALUE ? "NULL" : max + " " + (secondMax == Integer.MIN_VALUE ? "NULL" : secondMax));
            // res.add(curLevel); // no deep copy
            // System.out.println(curLevel);
        }
        return res;
    }
```