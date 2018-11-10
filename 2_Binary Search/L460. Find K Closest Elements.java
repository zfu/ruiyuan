/**
 * 460. Find K Closest Elements Given a target number, a non-negative integer k
 * and an integer array A sorted in ascending order, find the k closest numbers
 * to target in A, sorted in ascending order by the difference between the
 * number and target. Otherwise, sorted in ascending order by number if the
 * difference is same.
 * 
 * Example Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
 * 
 * Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
 * 
 * Challenge O(logn + k) time complexity.
 * 
 * Notice The value k is a non-negative integer and will always be smaller than
 * the length of the sorted array. Length of the given array is positive and
 * will not exceed 10^4 Absolute value of elements in the array and x will not
 * exceed 10^4
 * 
 */

public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // 1：用二分法找到离target最近的两个位置
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (A[mid] >= target)
                end = mid;
            else
                start = mid;
        }

        // 2： 用两个指针从上面找到的两个位置往外移动
        int[] result = new int[k];
        int left = start, right = end;
        for (int i = 0; i < k; i++) {
            // 两个指针都在范围内，那比较哪个比较接近target
            // 注意等于的情况取 左边的，因为左边的一定比右边的
            // 小（题目要求）
            if (left >= 0 && right < A.length) {
                if (Math.abs(A[left] - target) <= Math.abs(A[right] - target)) {
                    result[i] = A[left];
                    left--;
                } else {
                    result[i] = A[right];
                    right++;
                }
            } else if (left >= 0 && right >= A.length) { // 如果右指针出界了那就取左指针
                result[i] = A[left];
                left--;
            } else if (left < 0 && right < A.length) { // 同理如果左指针出界就取右指针
                result[i] = A[right];
                right++;
            }
        }

        return result;
    }
}