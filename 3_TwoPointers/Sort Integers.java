/**
 * 463. Sort Integers
Given an integer array, sort it in ascending order. Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.

Example
Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
 */

/**
 * Approach: Bubble Sort
 * 两两比较，如果后面的数比前面的数要大，则交换这两个数，否则继续。 这样一轮下去，便可以确定
 *  最后一个数（最大的数） 是多少。 然后除去最后一个数，再次从头到尾
 *  行 两
 * 比较，第二轮结束，我们便可以确定 第二大的元素。 依次循环下去，直到
 * 
 * 
 * 
 * 待比较的只有一个数时，说明排序完成。 时间复杂度为：O(n^2) 与数据状况无关
 * 
 * 
 *
 * http://bubkoo.com/2014/01/12/sort-algorithm/bubble-sort/
 */
public class Solution {
    /*
     * @param A: an integer array
     * 
     * @return:
     */
    public void sortIntegers(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // 每确定一个最大值 end--
        for (int end = nums.length - 1; end > 0; end--) {
            // 两两比较 nums[i] 和 nums[i+1] 直到 end 位置
            for (int i = 0; i < end; i++) {
                // 如果前的数比后面的数大，交换二者位置
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}

/**
 * Approach: Selection Sort 选择排序（Selection Sort）是一种简
 * 直观的排序算法。它的工作原理如下：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置， 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 
 * 以此类推，直到所有元素均排序完毕。 时间复杂度为：O(n^2) 与数据状况无关
 * 
 *
 * 选择排序的主要优点与数据移动有关。 如果某个元素位于正确的最终位置上，则它不会被移动。
 * 
 * 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，因此对n个元素的序列进行排序总共进行至多n-1次交换。
 *
 * http://bubkoo.com/2014/01/13/sort-algorithm/selection-sort/
 */
public class Solution {
    /*
     * @param A: an integer array
     * 
     * @return:
     */
    public void sortIntegers(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            // 在 未被排序 的部分中寻找 最小值
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            // 将 最小值 换到 已经排序好 的序列的末尾
            swap(nums, i, minIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Approach: Insertion Sort (稳定) 具体算法描述如下： 
 * 1. 从第一个元素开始，该元素可以认为已经被排序 
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描 
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置 
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置 
 * 5. 将新元素插入到该位置后 
 * 6. 重复步骤 2~5 
 * 时间复杂度为：O(n^2) 与数据状态有关
 * 如果数据本来就有序，则时间复杂度为：O(n) （最佳情况）
 *
 * 如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的数目。 该算法可以认为是插入排序的一个变种，称为二分查找排序。
 *
 * http://bubkoo.com/2014/01/14/sort-algorithm/insertion-sort/
 *
 * 注意点： 工程综合排序中，当样本量小于 60 时，直接使用 插排，因为其常数项特别小。 并且在样本量小的时候 n^2 的劣势并不会明显地体现出来。
 * 因此在实际工程中，面对大数据时，通常先利用 快排/归并 将数组划分成小数组，然后符合插排条件时，直接 插排。
 */
public class Solution {
    /*
     * @param A: an integer array
     * 
     * @return:
     */
    public void sortIntegers(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
                swap(nums, j, j + 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}