/**
 * 
 * 75. Find Peak Element There is an integer array which has the following
 * features:
 * 
 * The numbers in adjacent positions are different. A[0] < A[1] && A[A.length -
 * 2] > A[A.length - 1]. We define a position P is a peak if:
 * 
 * A[P] > A[P-1] && A[P] > A[P+1] Find a peak element in this array. Return the
 * index of the peak.
 * 
 * Example Given [1, 2, 1, 3, 4, 5, 7, 6]
 * 
 * Return index 1 (which is number 2) or 6 (which is number 7)
 * 
 * Challenge Time complexity O(logN)
 * 
 * Notice It's guaranteed the array has at least one peak. The array may contain
 * multiple peeks, find any of them. The array has at least 3 numbers in it.
 */


public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left = 0, right = A.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}