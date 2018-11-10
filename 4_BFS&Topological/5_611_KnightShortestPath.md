
### [611. Knight Shortest Path](https://www.lintcode.com/problem/knight-shortest-path/description)

**Description**

Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if knight can not reached.

source and destination must be empty.
Knight can not enter the barrier.

**Clarification**

If the knight is at (x, y), he can get to the following positions in one step:
```text
(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
```
**Example**
```text
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 2

[[0,1,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return 6

[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2] return -1
```

```java
//version 硅谷算法班
public class Solution {
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[grid.length][grid[0].length];  //Set<Point> v will TLE

        q.offer(source);
        v[source.x][source.y] = true;

        if (source.x == destination.x && source.y == destination.y) {
            return 0;
        }

        int dist = 0;

        while (!q.isEmpty()) {
            dist++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                for (int k = 0; k < 8; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];
                    if (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && !grid[nx][ny] && !v[nx][ny]) {
                        if (nx == destination.x && ny == destination.y) {
                            return dist;
                        }
                        q.offer(new Point(nx, ny));
                        v[nx][ny] = true;
                    }
                }
            }

        }
        return -1;
    }
}
```

The idea is to use [Breadth First Search (BFS)](http://www.techiedelight.com/breadth-first-search/) as it is a Shortest Path problem. Below is the complete algorithm.

  
1\. Create an empty queue and enqueue source cell having  
   distance 0 from source (itself)

2\. do till queue is not empty

   a) Pop next unvisited node from queue

   b) If the popped node is destination node, return its distance

   c) else we mark current node as visited and for each of 8 possible  
      movements for a knight, we enqueue each valid movement into the  
      queue with +1 distance (min distance of given node from source  
      = min distance of parent from source + 1)  

A knight can move in 8 possible directions from a given cell as illustrated in below figure –

   
  ![knight-movements](:/25104678d386411784fca1b3cfe0de8a)

We can find all the possible locations the Knight can move to from the given location by using the array that stores the relative position of Knight movement from any location. For example, if the current location is (x, y), we can move to (x + row\[k\], y + col\[k\]) for 0 <= k <=7 using below array.

row\[\] = \[ 2, 2, -2, -2, 1, 1, -1, -1 \]  
col\[\] = \[ -1, 1, 1, -1, 2, -2, 2, -2 \]  

So, from position (x, y) Knight’s can move to:

(x + 2, y – 1)  
(x + 2, y + 1)  
(x – 2, y + 1)  
(x – 2, y – 1)  
(x + 1, y + 2)  
(x + 1, y – 2)  
(x – 1, y + 2)  
(x – 1, y – 2)  

   
Note that in BFS, all cells having shortest path as 1 are visited first, followed by their adjacent cells having shortest path as 1 + 1 = 2 and so on.. so if we reach any node in BFS, its shortest path = shortest path of parent + 1. So, the first occurrence of the destination cell gives us the result and we can stop our search there. **It is not possible that the shortest path exists from some other cell for which we haven’t reached the given node yet. If any such path was possible, we would have already explored it.

```java

import java.util.*;

// queue node used in BFS
class Node
{
    // (x, y) represents chess board coordinates
    // dist represent its minimum distance from the source
    int x, y, dist;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    // As we are using class object as a key in a HashMap
    // we need to implement hashCode() and equals()

    // -- below methods are generated by IntelliJ IDEA (Alt + Insert) --
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        if (y != node.y) return false;
        return dist == node.dist;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + dist;
        return result;
    }
};

class ChessKnight
{
    // Below arrays details all 8 possible movements
    // for a knight
    private static int row[] = { 2, 2, -2, -2, 1, 1, -1, -1 };
    private static int col[] = { -1, 1, 1, -1, 2, -2, 2, -2 };

    // Check if (x, y) is valid chess board coordinates
    // Note that a knight cannot go out of the chessboard
    private static boolean valid(int x, int y, int N)
    {
        if (x < 0 || y < 0 || x >= N || y >= N)
            return false;

        return true;
    }

    // Find minimum number of steps taken by the knight
    // from source to reach destination using BFS
    public static int BFS(Node src, Node dest, int N)
    {
        // map to check if matrix cell is visited before or not
        Map<Node, Boolean> visited = new HashMap<>();

        // create a queue and enqueue first node
        Queue<Node> q = new ArrayDeque<>();
        q.add(src);

        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            Node node = q.poll();

            int x = node.x;
            int y = node.y;
            int dist = node.dist;

            // if destination is reached, return distance
            if (x == dest.x && y == dest.y)
                return dist;

            // Skip if location is visited before
            if (visited.get(node) == null)
            {
                // mark current node as visited
                visited.put(node, true);

                // check for all 8 possible movements for a knight
                // and enqueue each valid movement into the queue
                for (int i = 0; i < 8; ++i)
                {
                    // Get the new valid position of Knight from current
                    // position on chessboard and enqueue it in the
                    // queue with +1 distance
                    int x1 = x + row[i];
                    int y1 = y + col[i];

                    if (valid(x1, y1, N))
                        q.add(new Node(x1, y1, dist + 1));
                }
            }
        }

        // return INFINITY if path is not possible
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args)
    {
        int N = 8;

        // source coordinates
        Node src = new Node(0, 7);

        // destination coordinates
        Node dest = new Node(7, 0);

        System.out.println("Minimum number of steps required is " + BFS(src, dest, N));
    }
}

```

  ```java

package fbOnsite;

import java.util.*;

public class KnightShortestPath {
    public static int shortestPath(int[][] board, int[] src, int[] dst) {
        int[][] directions = new int[][]{{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        int m = board.length;
        int n = board[0].length;
        int res = 0;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        queue.offer(src[0]*n + src[1]);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int cur = queue.poll();
                visited.add(cur);
                int x = cur / n;
                int y = cur % n;
                if (x == dst[0] && y == dst[1]) return res;
                
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx<0 || nx>=m || ny<0 || ny>=n || visited.contains(nx*n+ny) || board[nx][ny]!=0)
                        continue;
                    queue.offer(nx*n + ny);
                }
            }
            res++;
        }
        return res;
    }
    
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] board = new int[][] {{0,1,0},{0,0,0},{0,0,0}};
        int[] src = new int[]{2,0};
        int[] dst = new int[]{2,2};
        int res = shortestPath(board, src, dst);
        System.out.println(res);
    }

}
```