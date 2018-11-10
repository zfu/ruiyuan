/**
 * 61. Search for a Range Given a sorted array of n integers, find the starting
 * and ending position of a given target value.
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * Example Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 * 
 * Challenge O(log n) time.
 */

public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return new int[]{-1, -1};
        }
        
        int start = searchFirst(A, target);
        int end = searchLast(A, target);
        
        return new int[] {start, end};
    }
    
    private int searchFirst(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int ans = -1;
        
        while(start <= end)
        {
            int mid = start + (end - start)/2;
            if(A[mid] == target){
                ans = mid;
                end = mid - 1;
            } else if(A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans; 
    }
    
    private int searchLast(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int ans = -1;
        
        while(start <= end)
        {
            int mid = start + (end - start)/2;
            if(A[mid] == target){
                ans = mid;
                start = mid + 1;
            } else if(A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans; 
    }
}