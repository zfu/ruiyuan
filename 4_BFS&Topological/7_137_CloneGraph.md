### [133\. Clone Graph](https://leetcode.com/problems/clone-graph/description/)

Difficulty: **Medium**



Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a `label` (`int`) and a list (`List[UndirectedGraphNode]`) of its `neighbors`. There is an edge between the given node and each of the nodes in its neighbors.

  
**OJ's undirected graph serialization (so you can understand error output):**

Nodes are labeled uniquely.

We use `#` as a separator for each node, and `,` as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

```
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
```

**Note**: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.





#### Solution

Language: **Java**
BFS + HashMap
时间复杂度nm，空间复杂度n

>用queue存放已经复制好但复制品node还没添加neighbor的node。
Map放对应label的复制品node。
做queue的BFS，每次从queue和map拿到一个原node和对应的复制品node，创建neighbors中还没有复制品的node，将新创建的node放入queue。这样保证queue不会重复。
创建新node的条件是map里面没有对应的label，这个条件与限定放入queue的规则一样。
最后返回map中node对应label的node复制品。

```java
/*
Description
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
How we serialize an undirected graph:
Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.
The graph has a total of three nodes, and therefore contains three parts as separated by #.
    1. First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    2. Second node is labeled as 1. Connect node 1 to node 2.
    3. Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:
   1
  / \
 /   \
0 --- 2
     / \
     \_/

Example
return a deep copied graph.

Tags
Breadth First Search Facebook
 */

/**
 * Approach: BFS + HashMap
 * 如果要拷贝一张图，我们首先需要知道其所有的节点以及其所对应的边。
 * 因此我们可以使用 BFS 对图进行遍历，获得其所有的点。
 * 然后我们使用一个 HashMap 存储下 当前节点 以及对应的 拷贝节点。
 * 这使得我们能够通过当前节点直接获得复制的节点，从而根据当前节点的关系
 * 对 拷贝节点 也建立相应的联系（边）。
 * 这种做法在 Copy List with Random Pointer 这道题目中也可以使用。
 * https://github.com/cherryljr/LintCode/blob/master/Copy%20List%20with%20Random%20Pointer.java
 */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        // Use HashMap to store the link ( origin node : copied node )
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // Use queue to BFS
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        // Initialize the map and queue, adding the first node.
        map.put(node, new UndirectedGraphNode(node.label));
        queue.offer(node);

        // BFS
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode neigh : curr.neighbors) {
                // if the origin node is not visited,
                // put it into the map and queue
                if (!map.containsKey(neigh)) {
                    map.put(neigh, new UndirectedGraphNode(neigh.label));
                    queue.add(neigh);
                }
                // add neighbors to the copied node
                // copied node: map.get(curr) -> copied node of curr
                // neighbors: map.get(neigh) -> copied node of neighbor
                map.get(curr).neighbors.add(map.get(neigh));
            }
        }

        return map.get(node);
    }
}

```

```java
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        // collecting new nodes
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        // collecting old nodes
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node.label, new UndirectedGraphNode(node.label));
        while(!queue.isEmpty()){
            UndirectedGraphNode cur = queue.poll();
            UndirectedGraphNode curCopy = map.get(cur.label);
            for(UndirectedGraphNode neighbor : cur.neighbors){
                if(!map.containsKey(neighbor.label)){
                    UndirectedGraphNode newNode = new UndirectedGraphNode(neighbor.label);
                    queue.offer(neighbor);
                    map.put(newNode.label, newNode);
                }
                curCopy.neighbors.add(map.get(neighbor.label));
            }
        }
        return map.get(node.label);
    }
}
```
