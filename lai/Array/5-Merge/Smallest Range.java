/**
 * Given k sorted integer arrays, pick k elements (one element from each of sorted arrays), what is the smallest range.

Assumptions:

k >= 2
None of the k arrays is null or empty
Examples:

{ { 1, 4, 6 },

  { 2, 5 },

  { 8, 10, 15} }

pick one element from each of 3 arrays, the smallest range is {5, 8} (pick 6 from the first array, pick 5 from the second array and pick 8 from the third array).
 */

 /**
  * https://leetcode.com/problems/smallest-range/solution/
  终于做到一道hard的题目了！这一题的思路见http://www.geeksforgeeks.org/find-smallest-range-containing-elements-from-k-lists/
我翻译成中文：

思路就是用一个array: ptr[k] ，来存储每个list的指针。
将ptr[0...k]中的每个元素初始化为0，即把每个list的指针初始化为0
重复以下步骤，直到某一个指针指到了list的末尾：
     1. 找到当前ptr[0…k]中所指的所有元素中的最大值和最小值
     2. 如果当前的(max-min)比minrange小，则更新minrange
     3. 把指向当前最小元素的那个指针在它的list上右移一位。
举例讲就是这样：

[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]

0 0 0 :4,0,5 min=0 max=5 minrange=5

0 1 0 :4,9,5 min=4 max=9 

1 1 0: 10,9,5 min=5 max=10

1 1 1: 10,9,18 min=9 max=18

1 2 1: 10,12,18 min=10 max=18

2 2 1: 15,12,18 min=12,max=18

2 3 1: 15,20,18 min=15,max=20

3 3 1: 24,20,18 min=18,max=24

3 3 2: 24,20,22 min=20,max=24 midrange=4

3 4 2: end

可见，在ptr[0~k]指向的元素中的[min~max]范围，因为每个Index都指向了每个list中的元素，所以一定能保证这个范围中每个list至少有一个数。
具体思想就是这样，但是如果单纯按照一个数组来存pointer，每次循环都要找最小值最大值，这样的解法的时间复杂度是O(n*k*k)，会TLE。因此进行了改进，将数组改为堆，用一个最小堆来存ptk[0...k]，这样的话找出最小值就不需要用循环来找了，步骤如下：

创建一个长度为k的最小堆，并且把k个list的首元素都插入进堆中。
维持两个变量：min和max，来存储在任何时间点时，当前堆中的最小值和最大值。
重复一下步骤：
      1. 从堆中poll出堆顶元素（即最小元素），赋值给min，计算max-min， 如果当前的(max-min)比minrange小，则更新minrange
      2. 找到当前最小元素所在的list中的下一个元素，插入进堆中。如果这个元素比max大，则更新max为这个元素。如果该list没有下一个元素了，已经结束了，则break出循环。

这样改进后的时间复杂度是O(nk Logk)，是可以接受的。
--------------------- 
作者：huanghanqian 
来源：CSDN 
原文：https://blog.csdn.net/huanghanqian/article/details/74765018 
版权声明：本文为博主原创文章，转载请附上博文链接！
  */

// 此题的实际意义，如何找到最小的时间范围，可以cover所有用户登录的时间点投放广告

public class Solution {
    public int[] smallestRange(int[][] nums) {
        int minx = 0, miny = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] next = new int[nums.length];
        boolean flag = true;
        PriorityQueue < Integer > min_queue = new PriorityQueue < Integer > ((i, j) -> nums[i][next[i]] - nums[j][next[j]]);
        for (int i = 0; i < nums.length; i++) {
            min_queue.offer(i);
            max = Math.max(max, nums[i][0]);
        }
        for (int i = 0; i < nums.length && flag; i++) {
            for (int j = 0; j < nums[i].length && flag; j++) {
                int min_i = min_queue.poll();
                if (miny - minx > max - nums[min_i][next[min_i]]) {
                    minx = nums[min_i][next[min_i]];
                    miny = max;
                }
                next[min_i]++;
                if (next[min_i] == nums[min_i].length) {
                    flag = false;
                    break;
                }
                min_queue.offer(min_i);
                max = Math.max(max, nums[min_i][next[min_i]]);
            }
        }
        return new int[] { minx, miny};
    }
}

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        //queue is used to save positions <row, col> and sort by value
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> nums.get(o[0]).get(o[1])));
        //yes, update two pointers later
        int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            //put the first element in each row to the queue
            queue.offer(new int[]{i, 0});
            //max is the maximum of the first num in all rows
            max = Math.max(max, nums.get(i).get(0));
        }
        while (queue.size() == nums.size()) {
            //after you polled the smallest: 
            //update two pointers;  offer the next one in that row;  update max
            int[] a = queue.poll();
            int row = a[0], col = a[1];
            if (end-start > max - nums.get(row).get(col)) {
                start = nums.get(row).get(col);
                end = max;
            }
            if (col + 1 < nums.get(row).size()) {
                queue.offer(new int[]{row, col + 1});
                max = Math.max(max, nums.get(row).get(col + 1));
            }
        }
        return new int[]{start, end};
    }
}
