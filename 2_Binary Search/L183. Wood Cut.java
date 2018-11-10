/**
 * 183. Wood Cut Given n pieces of wood with length L[i] (integer array). Cut
 * them into small pieces to guarantee you could have equal or more than k
 * pieces with the same length. What is the longest length you can get from the
 * n pieces of wood? Given L & k, return the maximum length of the small pieces.
 * 
 * Example For L=[232, 124, 456], k=7, return 114.
 * 
 * Challenge O(n log Len), where Len is the longest length of the wood.
 * 
 * Notice You couldn't cut wood into float length.
 * 
 * If you couldn't get >= k pieces, return 0.
 */

public class Solution {
    /*
     * @param L: Given n pieces of wood with length L[i]
     * 
     * @param k: An integer
     * 
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        int max = 0;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }

        // find the largest length that can cut more than k pieces of wood.
        int start = 1, end = max;
        int maxSize = 0;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                // 这里是改动的地方
                maxSize = Math.max(maxSize, mid);
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return maxSize;
    }

    private int count(int[] L, int length) {
        int sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += L[i] / length;
        }
        return sum;
    }
}