/**
 * 434. Number of Islands II
Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. 
Return how many island are there in the matrix after each operator.

Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Notice
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.
 */

 /**
 * Approach 1: Union Find (Based on HashMap)
 * The same as Number of Islands II in LeetCode.
 * You can get detail explanations here:
 * https://github.com/cherryljr/LeetCode/blob/master/Number%20of%20Islands%20II.java
 *
 * Union Find Template:
 * https://github.com/cherryljr/LintCode/blob/master/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /*
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int m, int n, Point[] positions) {
        List<Integer> rst = new ArrayList<>();
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return rst;
        }

        int[] pos = new int[m * n];
        boolean[] isIsland = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                pos[index] = index;
            }
        }

        UnionFind uf = new UnionFind(pos);
        int count = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Point p : positions) {
            int index = p.x * n + p.y;
            // 如果原本这个地方已经有岛屿，在相同位置上再次出现一个岛屿的话，不会对 count 造成任何影响
            // 如果不加上这个判断的话，96% 的 case 就会过不去...这个case确实没想到啊，摔~
            if (isIsland[index]) {
                rst.add(count);
                continue;
            }
            isIsland[index] = true;
            count++;

            for (int[] dir : dirs) {
                int nextX = p.x + dir[0];
                int nextY = p.y + dir[1];
                int nextP = nextX * n + nextY;
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n
                        || !isIsland[nextP]) {
                    continue;
                }
                count = uf.union(index, nextP, count);
            }

            rst.add(count);
        }

        return rst;
    }

    class UnionFind {
        HashMap<Integer, Integer> parent;
        HashMap<Integer, Integer> rankMap;

        UnionFind(int[] arr) {
            parent = new HashMap<>();
            rankMap = new HashMap<>();
            for (int i : arr) {
                parent.put(i, i);
                rankMap.put(i, 1);
            }
        }

        public int compressedFind(int i) {
            while (i != parent.get(i)) {
                parent.put(i, parent.get(parent.get(i)));
                i = parent.get(i);
            }
            return i;
        }

        public int union(int a, int b, int count) {
            int aFather = compressedFind(a);
            int bFather = compressedFind(b);
            if (aFather != bFather) {
                int aFRank = rankMap.get(aFather);
                int bFRank = rankMap.get(bFather);
                if (aFRank <= bFRank) {
                    parent.put(aFather, bFather);
                    rankMap.put(bFather, aFRank + bFRank);
                } else {
                    parent.put(bFather, aFather);
                    rankMap.put(aFather, aFRank + bFRank);
                }
                count--;
            }
            return count;
        }
    }
}

/**
 * Approach 2: Union Find (Based on Array)
 * Using Array instead of HashMap.
 * Make the code more concise and the extra space smaller.
 * (The Union Find array can also used as a isIsland array here)
 * You can get detail explanations here:
 * https://github.com/cherryljr/LeetCode/blob/master/Number%20of%20Islands%20II.java
 *
 * Union Find Template:
 * https://github.com/cherryljr/LintCode/blob/master/Find%20the%20Weak%20Connected%20Component%20in%20the%20Directed%20Graph.java
 */
public class Solution {
    /*
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int m, int n, Point[] positions) {
        List<Integer> rst = new ArrayList<>();
        if (n <= 0 || m <= 0 || positions == null || positions.length == 0) {
            return rst;
        }

        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        // Initialize the Union Find (parent and rank array)
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 1);

        int count = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (Point p : positions) {
            int index = p.x * n + p.y;
            if (parent[index] == index) {
                rst.add(count);
                continue;
            }
            parent[index] = index;
            count++;

            for (int[] dir : dirs) {
                int nextX = p.x + dir[0];
                int nextY = p.y + dir[1];
                int nextP = nextX * n + nextY;
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n
                        || parent[nextP] == -1) {
                    continue;
                }
                int aFather = compressedFind(parent, index);
                int bFather = compressedFind(parent, nextP);
                if (aFather != bFather) {
                    // keep balance
                    if (rank[aFather] <= rank[bFather]) {
                        parent[aFather] = bFather;
                        rank[bFather] += rank[aFather];
                    } else {
                        parent[bFather] = aFather;
                        rank[aFather] += rank[bFather];
                    }
                    count--;
                }
            }

            rst.add(count);
        }

        return rst;
    }

    private int compressedFind(int[] uf, int index) {
        while (index != uf[index]) {
            uf[index] = uf[uf[index]];
            index = uf[index];
        }
        return index;
    }
}