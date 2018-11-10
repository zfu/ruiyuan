
/*
544. Top k Largest Numbers
Given an integer array, find the top k largest numbers in it.

Example
Given [3,10,1000,-99,4,100] and k = 3.
Return [1000, 100, 10].
*/

/**
 * Min Heap
 * Poll出去最小的，留下的都是最大的。
 */
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0 || k <= 0) {
            return null;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> {
            return a - b;
        });
        
        for (int num : nums) {
            if (minHeap.size() == k) {
                minHeap.poll();
            }
            minHeap.add(num);
        }
        
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[k - i - 1] = minHeap.poll();
        }
        return res;
    }
}

/**
 * QuickSelect
 */

import java.util.Random;

class Solution {
    /*
     * @param nums an integer array
     * 
     * @param k an integer
     * 
     * @return the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // Write your code here
        quickSort(nums, 0, nums.length - 1, k);

        int[] topk = new int[k];
        for (int i = 0; i < k; ++i)
            topk[i] = nums[i];

        return topk;
    }

    private void quickSort(int[] A, int start, int end, int k) {
        if (start >= k)
            return;

        if (start >= end) {
            return;
        }

        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        Random rand = new Random(end - start + 1);
        int index = rand.nextInt(end - start + 1) + start;
        int pivot = A[index];

        // key point 2: every time you compare left & right, it should be
        // left <= right not left < right
        while (left <= right) {
            // key point 3: A[left] < pivot not A[left] <= pivot
            while (left <= right && A[left] > pivot) {
                left++;
            }
            // key point 3: A[right] > pivot not A[right] >= pivot
            while (left <= right && A[right] < pivot) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        quickSort(A, start, right, k);
        quickSort(A, left, end, k);
    }
};