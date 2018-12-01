/*
Description
Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest to origin.
Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.
Example
Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
return [[1,1],[2,5],[4,4]]
Tags
LinkedIn Amazon Heap
 */

 /**
 * 遇到寻找 前k个数 /  最大的k个数 / 最小的k个数，通常都是使用 PriorityQueue 来解决问题
 * 根据题目的要求使用 maxHeap 或者 minHeap.
 * 由此可见，该题是对于 优先级队列 这个数据结构的考察。
 *
 * 解题注意点：
 * 1. 因为答案需要的是各个Point的坐标，而我们的堆是根据 点之间的距离 来排序的。
 *    故我们新建了一个 Result 类以便于我们处理和获得结果。
 * 2. 因为我们只需要距离最近的前 k 个点，故PriorityQueue大小为 k 即可。
 *
 * 因此时间复杂度为：O(nlogk); 空间复杂度为: O(k)
 * Note: 对 PriorityQueue 不仅仅可以应用在求 前k个 元素的问题上，还能应用在求中值上。
 * 如：Data Stream Median 同时使用了 maxHeap 和 minHeap 来获得 Median.
 * https://github.com/cherryljr/LintCode/blob/master/Data%20Stream%20Median.java
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
    class Result {
        Point point;
        float distance;
        
        public Result(Point point, float distance) {
            this.point = point;
            this.distance = distance;
        }
    }
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        if (points == null || points.length == 0) {
            return null;
        }
        
        Point[] rst = new Point[k];
        
        PriorityQueue<Result> minheap = new PriorityQueue<>(k, (a, b) -> {
            // When a - b > 0 then swap, in this case here is ascending order
            if (a.distance - b.distance != 0) {
                return (int)(a.distance - b.distance);
            } else if (a.point.x - b.point.x != 0) {
                return a.point.x - b.point.x;
            } else {
                return a.point.y - b.point.y;
            }
        });
        for (Point point : points) {
            float dis = getDistance(point, origin);
            minheap.add(new Result(point, dis));
        }
        
        for (int i = 0; i < k; i++) {
            rst[i] = minheap.poll().point;
        }
        return rst;
    }
    
    private float getDistance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}

/**
 * 使用 Heapify 的方法 
 * heapify 时间复杂度 O(n)O(n) 
 * 然后 pop k 个点出来，时间复杂度 klognklogn 
 * 总共的时间复杂度 O(n + klogn)O(n+klogn) 
 * 如果 n >> k 的话，这种方法的时间复杂度是很优秀的。
 * https://www.jiuzhang.com/solution/k-closest-points/#tag-highlight
 */

public class Solution {
    private Point origin;
    private int size;
    
    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        if (points == null || points.length == 0) {
            return points;
        }
        
        this.origin = origin;
        heapify(points); // O(n)
        
        Point[] results = new Point[k];
        // O(klogn)
        for (int i = 0; i < k; i++) {
            results[i] = pop(points);
        }
        
        return results;
    }
    
    private void heapify(Point[] points) {
        size = points.length;
        for (int i = size / 2; i >= 0; i--) {
            siftDown(points, i);
        } 
    }
    
    private void siftDown(Point[] points, int index) {
        while (index < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int min = index;
            if (left < size && compare(points[min], points[left]) > 0) {
                min = left;
            }
            if (right < size && compare(points[min], points[right]) > 0) {
                min = right;
            }
            if (index != min) {
                Point temp = points[index];
                points[index] = points[min];
                points[min] = temp;
                index = min;
            } else {
                break;
            }
        }
    }
    
    private Point pop(Point[] points) {
        Point top = points[0];
        points[0] = points[size - 1];
        this.size--;
        
        siftDown(points, 0);
        return top;
    }
    
    private int compare(Point a, Point b) {
        int square = distanceSquare(a, origin) - distanceSquare(b, origin);
        if (square != 0) {
            return square;
        }
        if (a.x != b.x) {
            return a.x - b.x;
        }
        return a.y - b.y;
    }
    
    private int distanceSquare(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}