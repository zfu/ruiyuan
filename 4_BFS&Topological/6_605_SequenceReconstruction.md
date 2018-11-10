### [444\. Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/description/)

Difficulty: **Medium**



Check whether the original sequence `org` can be uniquely reconstructed from the sequences in `seqs`. The `org` sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10<sup>4</sup>. Reconstruction means building a shortest common supersequence of the sequences in `seqs` (i.e., a shortest sequence so that all sequences in `seqs` are subsequences of it). Determine whether there is only one sequence that can be reconstructed from `seqs` and it is the `org` sequence.

**Example 1:**

```
**Input:**
org: [1,2,3], seqs: [[1,2],[1,3]]

**Output:**
false

**Explanation:**
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
```

**Example 2:**

```
**Input:**
org: [1,2,3], seqs: [[1,2]]

**Output:**
false

**Explanation:**
The reconstructed sequence can only be [1,2].
```

**Example 3:**

```
**Input:**
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

**Output:**
true

**Explanation:**
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
```

**Example 4:**

```
**Input:**
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

**Output:**
true
```

**<font color="red" style="display: inline;">UPDATE (2017/1/8):</font>**  
The _seqs_ parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.

**Approach**
```text
____________________
Topological sorting
+++++++++++++++++++++++
Compared to course schedule problem. Only track node that appeared in seqs
in Indegrees
0. build indegrees and edges
    a. eg. [[1,2], [2,3]]
    b. translates to 1->2, 2->3
1. start Topological sorting
    a. only one path <-> Queue is always 1
    b. the path is the same as org
2. if satisify a&&b return True else False
Complexity
____________________
Time - O(N*M)
Space - O(N*M)
```


#### Solution

Language: **Java**

```java
化用拓扑排序的思路
本题看似是一个典型的字符串问题，但是这道题却不能用典型的树的DFS来解决，因为所有树相关的数据结构都有一个特点，即一个节点只能有一个父节点，是针对一种1-N的结构。而本题的节点之间是一种完全发散的M-N的关系，因而不适合用树来解决问题。

如果将每个数字作为一个节点，其前后关系作为一条边，则这道题的seqs参数可以转为一个图数据结构
以例子4为例， [5,2,6,3] -> [5,2],[2,6],[6,3], [4,1,5,2] -> [4,1],[1,5],[5,2]
基于以上的edge list可以构造一个图数据结构，而参数org则是满足有向图的遍历路径。当且仅当，org是唯一合法的拓扑遍历路径时，返回true.

拓扑排序及其解决
伪算法如下：

edgeList = SeqToEdgeList(seqs);
graph = EdgeToList(edgeList);
do
    search beginPoint
    return false if (no beginPoint || more than one begin point)
    remove beginPoint from graph
    update indegrees for remained nodes
Until
    no nodes are remained in the graph
拓扑排序相关内容请参照leetcode 210。代码实现如下：

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        //1. Convert seqs to edge list
        HashSet<List<Integer>> edges = new HashSet();
        for (List<Integer> seq:seqs){
            if (seq.size()==1) edges.add(Arrays.asList(seq.get(0)));
            for (int i=0;i<seq.size()-1;i++){
                edges.add(Arrays.asList(seq.get(i),seq.get(i+1)));
            }
        }

        //2. Convert edge list to graph
        HashMap<Integer,List<Integer>> graph = new HashMap<>();
        HashMap<Integer,Integer>  indegree   = new HashMap<>();
        int count = 0;
        for (List<Integer> edge:edges){
            //System.out.printf("(%d,%d)",edge.get(0),edge.get(1));
            if (edge.size()==1) {
                indegree.put(edge.get(0),indegree.getOrDefault(edge.get(0),0));
                List<Integer> toNodes = new ArrayList<>();
                graph.put(edge.get(0),toNodes);
                continue;
            }
            if (graph.containsKey(edge.get(0))){
                graph.get(edge.get(0)).add(edge.get(1));
            } else{
                List<Integer> toNodes = new ArrayList<>();
                toNodes.add(edge.get(1));
                graph.put(edge.get(0),toNodes);
            }
            indegree.put(edge.get(0),indegree.getOrDefault(edge.get(0),0));
            indegree.put(edge.get(1),indegree.getOrDefault(edge.get(1),0)+1);
        }

        //3. Find begin point
        List<Integer> queue = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            //System.out.printf("%d indegree:%d\n",entry.getKey(),entry.getValue());
            if (entry.getValue()==0) {
                queue.add(entry.getKey());
                entry.setValue(-1);
            }
        }

        //4. Topology Sort
        while (count<org.length && queue.size()==1){
            int beginPoint = queue.remove(0);
            //System.out.printf("Point:%d org[%d]=%d\n",beginPoint,count,org[count]);
            if (beginPoint!= org[count]){
                return false;
            } else {
                count++;
            }
            if (!graph.containsKey(beginPoint)) break;
            for (Integer nextPoint:graph.get(beginPoint)){
                indegree.put(nextPoint,indegree.get(nextPoint)-1);
            }
            for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
                if (entry.getValue()==0) {
                    queue.add(entry.getKey());
                    entry.setValue(-1);
                }
            }
            //System.out.printf("Update Queue(%d):%d\n",queue.size(),queue.get(0));
        }

        //5. Return result
        return count==org.length;
    }
}
```

```java
 /**credit: https://discuss.leetcode.com/topic/65948/java-solution-using-bfs-topological-sort*/
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                if (!map.containsKey(seq.get(0))) {
                    map.put(seq.get(0), new HashSet<>());
                    indegree.put(seq.get(0), 0);
                }
            } else {
                for (int i = 0; i < seq.size() - 1; i++) {
                    if (!map.containsKey(seq.get(i))) {
                        map.put(seq.get(i), new HashSet<>());
                        indegree.put(seq.get(i), 0);
                    }

                    if (!map.containsKey(seq.get(i + 1))) {
                        map.put(seq.get(i + 1), new HashSet<>());
                        indegree.put(seq.get(i + 1), 0);
                    }

                    if (map.get(seq.get(i)).add(seq.get(i + 1))) {
                        indegree.put(seq.get(i + 1), indegree.get(seq.get(i + 1)) + 1);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1) {
                return false;
            }
            int curr = queue.poll();
            if (index == org.length || curr != org[index++]) {
                return false;
            }
            for (int next : map.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return index == org.length && index == map.size();
    }
```

```java
class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // Write your code here
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();
    	
        for (int num : org) {
            map.put(num, new HashSet<Integer>());
            indegree.put(num, 0);
        }

        int n = org.length;
        int count = 0;
        for (int[] seq : seqs) {
            count += seq.length;
            if (seq.length >= 1 && (seq[0] <= 0 || seq[0] > n))
                return false;
            for (int i = 1; i < seq.length; i++) {
                if (seq[i] <= 0 || seq[i] > n)
                    return false;
                if (map.get(seq[i - 1]).add(seq[i]))
                    indegree.put(seq[i], indegree.get(seq[i]) + 1);
            }
        }

        // case: [1], []
        if (count < n)
            return false;
		
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int key : indegree.keySet()) 
            if (indegree.get(key) == 0)
                q.add(key);
		
        int cnt = 0;
        while (q.size() == 1) {
            int ele = q.poll();
            for (int next : map.get(ele)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) q.add(next);
            }
            if (ele != org[cnt]) {
                return false;
            }
            cnt++;
        }
		
        return cnt == org.length;
    }
}
```

