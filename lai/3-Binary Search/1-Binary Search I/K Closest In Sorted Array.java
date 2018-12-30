/**
 * 
 Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return

A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
 */

public class Solution {
    public int[] kClosest(int[] array, int target, int k) {
      // Write your solution here
      if (array == null || array.length == 0 || k < 0 || k > array.length) {
        return new int[0];
      }
      int left = firstSmaller(array, target);
      int right = left + 1;
      int[] res = new int[k];
      for (int i = 0; i < k; i++) {
        if (right >= array.length || left >= 0 && (target - array[left] <= array[right] - target)) {
          res[i] = array[left--];
        } else {
          res[i] = array[right++];
        }
      }
      return res;
    }
    
    private int firstSmaller(int[] array, int target) {
      int left = 0, right = array.length - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (array[mid] >= target) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
      return right;
    }
  }
  

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