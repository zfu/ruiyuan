### [200\. Number of Islands](https://leetcode.com/problems/number-of-islands/description/)

Difficulty: **Medium**



Given a 2d grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

```text
**Input:**
11110
11010
11000
00000

**Output:** 1
```

**Example 2:**

```text
**Input:**
11000
11000
00100
00011

**Output:** 3
```



```text

The idea is to start BFS from each unprocessed node and increment the island count. Each BFS traversal will mark all cells which make one island as processed. So the problem reduces to finding number of BFS calls.
In each BFS traversal, we start by creating an empty queue. Then we enqueue starting cell and mark it as processed. Then we pop front node from the queue and process all 8 adjacent cells of current cell and enqueue each valid cell which is land. We repeat this process till queue is empty.

We can find all the possible locations we can move to from the given location by using the array that stores the relative position of movement from any location. For example, if the current location is
(x, y), we can move to (x + row[k], y + col[k]) for 0 <= k <= 7 using below array.
int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

So, from position (x, y), we can move to:
(x – 1, y – 1)
(x – 1, y)
(x – 1, y + 1)
(x, y – 1)
(x, y + 1)
(x + 1, y – 1)
(x + 1, y)
(x + 1, y + 1)
```

```java 
/**
  * BFS
  * O(m*n), O(m*n)
  */
public class Solution {
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        boolean[][] v = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] && !v[i][j]) {
                    ans++;
                    bfs(grid, v, i, j);
                }
            }
        }
        return ans;
    }

    private void bfs(boolean[][] grid, boolean[][] v, int sx, int sy) {
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};

        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        qx.offer(sx);
        qy.offer(sy);
        v[sx][sy] = true;

        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && !v[nx][ny] && grid[nx][ny]) {
                    qx.offer(nx);
                    qy.offer(ny);
                    v[nx][ny] = true;
                }
            }
        }
    }
}
```

```java
/**
     * BFS 
     * Start from the top-left corner of the grid.
     * Go through each position row by row and check if it is island.
     * If it is not, skip.
     * If it is, BFS to find the whole region.
     * Mark the region as "0" when finished.
     * Number of islands increment by 1.
     */
    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * Set the starting grid to '0' to mark it as visited.
     * Add it to queue to start BFS.
     * Find the region and mark all grids as '0'.
     */
    private void bfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] head = queue.poll();
            for (int[] dir : dirs) {
                int row = head[0] + dir[0];
                int col = head[1] + dir[1];
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    queue.offer(new int[]{row, col});
                }
            }
        }
    }
```

```java
/**
 * DFS
 *  
 */
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || 
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rst = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j, rows, cols);
                    rst++;
                }
            }
        }
        
        return rst;
    }
    
    // Using DFS Method to find the region
    private void infect(char[][] grid, int i, int j, int rows, int cols) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1') {
            return;
        }
        // Mark the explored island cells with 'x'.
        grid[i][j] = 'x'; 
        // Use the directions array to make code more concise
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            infect(grid, i + dir[0], j + dir[1], rows, cols);
        }
    }
}
```


```java
// Solution 3: UF + count O(m * n); O(m * n)
 class UnionFind {
        private int[] father;
        // an extra variable to record the number of reduced subsets
        // can't be initialized in the constructor since the total number of initial subsets depends on '1'
        // so use another variable in main function to record the total number
        private int count; 

        public UnionFind(int n) {
            father = new int[n];
            for (int i = 1; i < n; i++) {
                father[i] = i;
            }
        }

        public int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]); // return find(father[x]);
        }

        public void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) { // unite 2 subsets, the count of subsets decreases by one
                father[root_a] = root_b;
                count++;
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length; 
        UnionFind uf = new UnionFind(m * n);
        int total = 0; // the total number of lands
        // int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dirs = {{-1, 0}, {0, -1}}; // only need to check up and left neighbors
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                total++;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                        uf.union(i * n + j, x * n + y); // use the number of columns
                    }
                }
            }
        }
        return total - uf.count;
    }
```

#### Follow Up:
如果不能modify input怎么办
用辅助数组boolean记录land是否被访问过
并查集
remove island that less than k size. 类似数岛，区别在于要先算出岛的大小，如果岛小于k，就把整个岛删除掉。


largest size of island instead of number of islands (LC 200). 就做了这一道题，可能是做得太慢。

Start a new discussion
Post



1）如果给的数组不是一个square的，是个不定长的怎么办？
2）如果不光是0， 1，而是>1 （mountain）, =1 （island）, <1 （water），仍然求有多少数量的island。
用了辅助数组完成，让去掉辅助数组再写一个。辅助数组指的是坐标变换数组吗
return linked list，list中每一个node代表一个岛，以及岛有多大
接下来又问了道没见过的，是贰佰200和肆佰陆拾叁463的综合：给定条件与前者相同，但不找个数了，要找出周长最大的岛的周长，比那道要找最大面积的hard题都麻烦。
(Bloomberg)返回每个island的边界线长度 求所有islands的周长加起来是多少 对每个island求perimeter即可， 因为不不能确定形状，所以要⽤用dfs和借⽤用global instance来求周⻓

```java
 final int[] b = new int[]{0, 1, 0, -1, 0};
    int cell = 0, neighbor = 0;

    public int maxPerimeter(char[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    getPerimeter(grid, i, j);
                    max = Math.max(max, cell * 4 - neighbor * 2);
                    System.out.println(cell + " " + neighbor);
                    cell = 0;
                    neighbor = 0;
                }
            }
        }
        return max;
    }

    public void getPerimeter(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        cell++;
        for (int k = 0; k < 4; k++) {
            if (isValidNeighbor(grid, i + b[k], j + b[k + 1])) neighbor++;
        }
        for (int k = 0; k < 4; k++) getPerimeter(grid, i + b[k], j + b[k + 1]);
    }

    public boolean isValidNeighbor(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return false;
        return true;
    }
```

```java
import java.util.ArrayDeque;
import java.util.Queue;
 
class Pair {
    int x, y;
 
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
class CountIslands
{
    // Below arrays details all 8 possible movements from a cell
    // (top, right, bottom, left and 4 diagonal moves)
    private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
    private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };
 
    // Function to check if it is safe to go to position (x, y)
    // from current position. The function returns false if (x, y)
    // is not valid matrix cordinates or (x, y) represents water or
    // position (x, y) is already processed
    public static boolean isSafe(int[][] mat, int x, int y,
                             boolean[][] processed)
    {
        return (x >= 0) && (x < processed.length) &&
                (y >= 0) && (y < processed[0].length) &&
                (mat[x][y] == 1 && !processed[x][y]);
    }
 
    public static void BFS(int[][] mat, boolean[][] processed, int i, int j)
    {
        // create an empty queue and enqueue source node
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(i, j));
 
        // mark source node as processed
        processed[i][j] = true;
 
        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();
 
            // check for all 8 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 8; k++)
            {
                // Skip if location is invalid or already processed
                // or has water
                if (isSafe(mat, x + row[k], y + col[k], processed))
                {
                    // skip if location is invalid or it is already
                    // processed or consists of water
                    processed[x + row[k]][y + col[k]] = true;
                    q.add(new Pair(x + row[k], y + col[k]));
                }
            }
        }
    }
 
    public static void main(String[] args)
    {
        int[][] mat=
        {
            { 1, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
            { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
            { 1, 1, 1, 1, 0, 0, 1, 0, 0, 0 },
            { 1, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
            { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
            { 0, 1, 0, 1, 0, 0, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 1, 1, 1, 0 },
            { 1, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
            { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 }
        };
 
        int M = mat.length;
        int N = mat[0].length;
 
        // stores if cell is processed or not
        boolean[][] processed = new boolean[M][N];
 
        int island = 0;
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                // start BFS from each unprocessed node and
                // increment island count
                if (mat[i][j] == 1 && !processed[i][j])
                {
                    BFS(mat, processed, i, j);
                    island++;
                }
            }
        }
 
        System.out.print("Number of islands are " + island);
    }
}
```