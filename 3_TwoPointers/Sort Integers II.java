/**
 * 464. Sort Integers II
Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example
Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
 */

 /**
 * Approach: QuickSort
 * 快速排序的核心就是选定一个 pivot, 根据它来进行 partition.
 * 使得所有 <pivot 的数都在其左边；所有 >pivot 的数都在其右边。
 * 从而将数组划分成 3 个部分 (小于pivot的；等于pivot的；大于pivot的）
 * 以上就是经典的 荷兰国旗 问题。均摊时间复杂度为 O(n)；空间复杂度为 O(1).
 * 对于这个 partition 部分的详细解析可以参考：
 * https://github.com/cherryljr/LintCode/blob/master/Sort%20Colors.java
 *
 * 然后我们需要对被划分好的左右两个部分再次进行 partition 操作，直到被划分的长度为 1 时 (left >= right).
 * 而整个数组总共可以被划分多少次呢？答案是 logn 次.
 * 因此 随机快速排序 的总体的时间复杂度为 O(nlogn) （均摊）
 *
 * 但是为什么这个时间复杂度是均摊的呢？（具有一定概率）
 * 我们都知道 pivot 的选取，直接关系到了时间复杂度的大小。（不合适的 pivot 将导致非常糟糕的数据分布）
 * 因此为了尽可能地保证 低时间复杂度，从而随机性地选择 pivot。这也就导致了时间复杂度为 O(nlogn) 是一个 概率性事件。
 * 具体优化过程可以参考：
 * https://github.com/cherryljr/LintCode/blob/master/Kth%20Largest%20Element.java
 *
 * partition 部分在代码实现上与 Sort Colors 相差不多，但是二者对于 pivot 的选择不同。
 * QuickSort 中选择的是 nums[right] 作为 pivot,在数组内部；Sort Colors 的 pivot 则是由外界直接给定。
 * 这就导致了 QuickSort 中 大于pivot部分左边界初始化有一点点不同。
 * 这里 more 指针初始化为 right,而不是 right+1. 因为我们需要维持 nums[right] 来作为 pivot,
 * 并在最后交换 nums[more] 和 nums[right]，完成 等于pivot数组部分的 右边界。
 *
 * Tips:
 * 对于快速排序的时间复杂度为什么是均摊 O(nlogn)，最坏O(n^2) 呢？
 * 因为 pivot 的选取是随机的，每个值被选取到作为 pivot 的概率是相同的。而每次
 * partition 的时间复杂度为 O(n),因此经过数学分析证明可得：均摊时间复杂度为 O(nlogn).
 * 但是在少数情况下 ，pivot 每次都选取到了最右边界的那个值，导致退化成了 O(n^2)的时间复杂度。
 *
 * 对于快速排序（随机快排）的空间复杂度为什么是均摊 O(logn)，最坏 O(n) 呢？
 * 因为快速排序必须每次记录下 pivot 的 index,这样才能根据这个 index 对数组进行划分。
 * 因此需要记录的划分点个数，就是其所需要的 额外空间 的大小。
 * 因为 pivot 是被随机选取的，所以经数学证明可得：我们说需要记录的index 个数均摊值为 O(logn) 个.即空间复杂度为 O(logn).
 * 但是同样存在因为 pivot 选择不当未能成功划分数组的情况，导致每个点都被选择作为了 pivot,使得时间复杂度上升到了 O(n).
 *
 * 注意：
 * 工程应用中，当样本为基础类型时，可以直接使用 快排。因为基础类型都一样，先后顺序不会存在问题。
 * 如果为自定义类型，使用 归并。这是考虑到了 排序稳定性 的原因。
 * 快排的 partition 做不到稳定性。比如：4 4 4 3，partition的时候，第一个4 会和 3 进行交换。
 *
 * 快速排序在工程应用上的一步步优化：
 * http://blog.csdn.net/hacker00011000/article/details/52176100
 */
public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length < 2) {
            return;
        }

        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            // swap a random value with nums[right] (shuffle the array)
            swap(nums, left + (int)(Math.random() * (right - left)), right);
            int[] positioin = partition(nums, left, right);
            // Sort the left part ( <pivot )
            quickSort(nums, left, positioin[0] - 1);
            // Sort the right part ( >pivot )
            quickSort(nums, positioin[1] + 1, right);
        }
    }

    private int[] partition(int[] nums, int left, int right) {
        // 初始化左右指针 和 pivot (注意 more 指针初始化的位置)
        int less = left - 1, more = right;
        // int pivot = nums[right];

        // 遍历到 大于pivot数组部分的 左边界 即可
        while (left < more) {
            // 当前元素小于 pivot 则放到 pivot 的左边
            if (nums[left] < nums[right]) {
                swap(nums, ++less, left++);
                // 当前元素大于 pivot 则放到 pivot 的右边
                // 注意 右边部分的最右端是 right-1. 因为 right 要最后用来与 more 进行 swap 以组成右边界.
            } else if (nums[left] > nums[right]) {
                swap(nums, left, --more);
            } else {
                left++;
            }
        }
        // 最后,交换 nums[more] 和 nums[right] (pivot) 完成 partition
        swap(nums, more, right);

        // 返回 等于pivot部分数组的 左右边界
        return new int[]{less + 1, more};
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Approach: Merge Sort
 * 归并操作(Merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。
 * 归并排序算法依赖归并操作。归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。
 *
 * 算法思路：
 *  1. 把 n 个记录看成 n 个长度为 l 的有序子表
 *  2. 进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表
 *  3. 重复第 2 步直到所有记录归并成一个长度为 n 的有序表为止。
 *
 * 时间复杂度为：O(nlogn)
 * 空间复杂度为：O(n)
 *
 * http://bubkoo.com/2014/01/15/sort-algorithm/merge-sort/
 */
public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length < 2) {
            return;
        }

        mergeSort(A, 0, A.length - 1);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        // 归并排序 左半部分数组
        mergeSort(nums, left, mid);
        // 归并排序 右半部分数组
        mergeSort(nums, mid + 1, right);
        // 将排好序的左右部分归并起来
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // 用于存储外部排序后的数组(merge)
        int[] helper = new int[right - left + 1];

        int i = 0;
        // p1 指向 左半部分 数组的起始位置; p2 指向 右半部分 数组的起始位置
        int p1 = left, p2 = mid + 1;
        // 选择左右部分数组中较小的元素填在 helper[i] 位置
        // 然后 i 移动到下一个位置，同时 指向较小的元素的指针 也移动到下一个位置
        while (p1 <= mid && p2 <= right) {
            helper[i++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        // 检查 左侧数组 是否还没遍历完
        while (p1 <= mid) {
            helper[i++] = nums[p1++];
        }
        // 检查 右侧数组 是否还没遍历完 (这两个 while 只有一个会被执行)
        while (p2 <= right) {
            helper[i++] = nums[p2++];
        }

        // 将 helper 中排好序的数据覆盖到原始数组 nums 中
        for (i = 0; i < helper.length; i++) {
            nums[left + i] = helper[i];
        }
    }
}

/**
 * Approach: HeapSort
 * 下面的讲解全部都是按照 从小到大 进行排序的方法来解释的。
 * 如果想要按照 从大到小 的顺序进行排序，则我们需要采用 最小堆结构（原因在看完下面的解释后就清楚了）
 *
 * 堆排序就是把 最大堆 堆顶的最大数取出放到数组的尾部位置，然后将剩余的堆继续调整为最大堆，（即先确定数组中最大的数）
 * 再次将堆顶的最大数取出，这个过程持续到 剩余数只有一个 时结束。
 * 在堆中定义以下几种操作：
 *  最大堆调整（Max-Heapify）：调整堆的结构，使得各个节点的子节点永远小于父节点
 *  堆排序（Heap-Sort）：将堆顶的最大元素调整到数组尾部，堆的大小减1，然后对堆顶元素执行 siftDown 操作
 *  使得堆仍然保持 最大堆 的 结构性 和有序性。
 *
 * 堆排序的核心就是：
 *  Heapify 与 siftDown 操作。
 *  关于这二者的详细解释可以参考：
 *  https://github.com/cherryljr/LintCode/blob/master/Heapify.java
 * 堆排序的动画演示：
 * https://www.youtube.com/watch?v=MtQL_ll5KhQ
 */
class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length < 2) {
            return;
        }

        heapSort(A);
    }

    private void heapSort(int[] nums) {
        // 首先构造最大堆结构
        maxHeapify(nums);
        int size = nums.length;
        // 交换堆顶元素（当前最大值） 和 数组的末尾元素。同时堆的大小 减1
        swap(nums, 0, --size);
        while (size > 0) {
            // 移除堆顶元素后，对交换上来的堆顶元素进行 siftDown 操作，以维持最大堆的特性
            siftDown(nums, 0, size);
            // 继续移除 堆顶元素（当前最大值） 以完成排序
            swap(nums, 0, --size);
        }
    }

    private void maxHeapify(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            siftDown(nums, i, nums.length);
        }
    }

    private void siftDown(int[] nums, int index, int size) {
        while (index < size) {
            int max = index, left = 2 * index + 1;
            if (left < size && nums[left] > nums[max]) {
                max = left;
            }
            if (left + 1 < size && nums[left + 1] > nums[max]) {
                max = left + 1;
            }
            if (max == index) {
                break;
            }

            swap(nums, index, max);
            index = max;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}