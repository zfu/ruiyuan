/**
 * 447. Search in a Big Sorted Array Given a big sorted array with positive
 * integers sorted by ascending order. The array is so big so that you can not
 * get the length of the whole array directly, and you can only access the kth
 * number by ArrayReader.get(k) (or ArrayReader->get(k) for C++). Find the first
 * index of a target number. Your algorithm should be in O(log k), where k is
 * the first index of the target number.
 * 
 * Return -1, if the number doesn't exist in the array.
 * 
 * Example Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.
 * 
 * Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.
 * 
 * Challenge O(log k), k is the first index of the given target number.
 * 
 * Notice If you accessed an inaccessible index (outside of the array),
 * ArrayReader.get will return 2,147,483,647.
 */

public class Solution {
    /*
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code 
        if (reader == null) {
            return -1;
        }

        int left = 0, right = 2;
        while (reader.get(right) < target) {
            right *= 2;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        if (reader.get(left) == target) {
            return left;
        }

        return -1;
    }
}