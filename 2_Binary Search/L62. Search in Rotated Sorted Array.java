/**
 * 62. Search in Rotated Sorted Array Suppose a sorted array is rotated at some
 * pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example For [4, 5, 1, 2, 3] and target=1, return 2.
 * 
 * For [4, 5, 1, 2, 3] and target=0, return -1.
 * 
 * Challenge O(logN) time
 */

public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }

        int left = 0, right = A.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < A[right]) {
                // target might equal to right
                if (A[mid] < target && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (A[left] <= target && target < A[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}