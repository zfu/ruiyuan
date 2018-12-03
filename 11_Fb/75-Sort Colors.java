/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.
Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
Author: Mindy927 */

class Solution {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length-1; //left:swap pos when we found 0; right:swap pos when we found 2
        int i = 0;
        while (i<=right){ //right+1 is first 2, still need to check when i==right
            if (nums[i] == 0){
                swap(nums, i, left);
                left++;
                i++;
            }else if (nums[i] == 2){
                swap(nums, i, right);
                right--; //No need to i++, since we still need to check the number swapped back from pos right
            }else {
                i++;
            }
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Approach: QuickSelect | Partition
 * 与 Partition Array / Sort Letters by Case 属于同一类题
 * 不同在于该题中需要针对 3 个元素进行排序。
 * 因此我们需要 3 个指针来进行排序，分别代表 0, 1, 2 即 red, white, blue
 * 实际上就是 荷兰国旗问题。
 * 属于经过优化的 快速排序 的 partition 方法。
 * （将数组由原来的分成 2 个部分，改成分为 3 个部分。分别为：<pivot; =pivot; >pivot）
 *
 * 具体做法:
 * 选定 pivot 为 white,大小为1.
 * 定义 小于pivot部分的数组(red)的右边界 less 和 大于pivot部分数组(blue)的左边界 more.
 * 然后利用指针 left 从 left 开始向 right 进行遍历。(直接利用了 left 的空间，省些空间哈）
 *  当 nums[left] = 0 时，说明为 red, 应将该元素放在 小于pivot部分 中，
 *  因此将 小于pivot部分的 下一个位置 与 nums[left] 进行交换（小于pivot部分的数组扩大） 即 swap(nums[++less], nums[left++])
 *  当 nums[left] = 1 时，说明为 white, 应将该元素放在 等于pivot部分 中，故 left 继续向后移动 (left++)
 *  当 nums[left] = 2 时，说明为 blue, 应将该元素放在 大于pivot部分 中，
 *  因此将 大于pivot部分的 前一个位置 与 nums[left] 进行交换（大于pivot部分的数组扩大）故 swap(nums[--more], nums[left])
 *  注意：交换后 nums[left] 位置上的数大小并不能确定(可能为 0,1,2),故需要再次进行判断，不能进行 left++ 的操作.
 *  直至 left 与 more 相遇，说明已经遍历到大于pivot部分数组的边界了，则结束遍历。
 *
 * 算法时间复杂度为 O(N)； 时间复杂度为 O(1)
 */
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        sortColorsHelper(nums, 0, nums.length - 1);
    }

    private void sortColorsHelper(int[] nums, int left, int right) {
        int less = left - 1;    // 小于pivot部分的 右边界
        int more = right + 1;   // 大于pivot部分的 左边界

        while (left < more) {
            if (nums[left] == 0) {
                // 将 小于pivot部分的 下一个位置 与 nums[i] 进行交换（小于pivot部分的数组扩大）
                swap(nums, ++less, left++);
            } else if (nums[left] == 1) {
                // 当 nums[i]==pivot 的时候，直接将 i 移动到下一个位置即可
                left++;
            } else {
                // 将 大于pivot部分的 前一个位置 与 nums[i] 进行交换（大于pivot部分的数组扩大）
                // 交换后 a[i] 位置上的数大小并不能确定(可能为 0,1,2),故需要再次进行判断，不能进行 i++ 的操作
                swap(nums, --more, left);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    } 
}


/*
Description
Given an array of n objects with k different colors (numbered from 1 to k),
sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
Notice
You are not suppose to use the library's sort function for this problem.
k <= n
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
Challenge
A rather straight forward solution is a two-pass algorithm using counting sort.
That will cost O(k) extra memory. Can you do it without using extra memory?
Tags
Sort Two Pointers
 */

/**
 * Approach: Two Pointers (Partition) + BinarySearch
 * 该题需要排序的范围上升到了 k,此时已经和普通的 排序 算法几乎一样了。
 * 我们无法通过仅一次遍历便能够得到结果。因此我们考虑使用排序算法。
 * 而排序算法的时间复杂度最少也需要 O(nlogn),但该题已经给出了排序的范围 1~k.
 * 故我们考虑能够通过该信息将复杂度降低到 O(nlogk) 呢？
 *
 * 于是我们想到了 二分 的思想。
 * 做法与 Sort Colors 几乎相同。只是将每次用于划分数组的 pivot 改成了 colorMid.
 * 而 colorMid 则是每次通过二分的方法进行确定。
 * 当前数组划分完毕后将形成 <colorMid; =colorMid; >colorMid 这三部分的数组。
 * 然后我们再分别对 <colorMid 和 >colorMid 部分递归处理即可。
 *
 * 算法时间复杂度为 O(nlogk)
 */
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }

        sortColors2Helper(colors, 0, colors.length - 1, 1, k);
    }

    private void sortColors2Helper(int[] colors,
                                   int  left,
                                   int right,
                                   int colorFrom,
                                   int colorTo) {
        if (colorFrom >= colorTo) {
            return;
        }
        if (left >= right) {
            return;
        }

        int less = left - 1;    // 小于pivot部分的 右边界
        int more = right + 1;   // 大于pivot部分的 左边界
        // take colorMid as the pivot
        int colorMid = colorFrom + (colorTo - colorFrom) / 2;
        int i = left;
        while (i < more) {
            if (colors[i] < colorMid) {
                swap(colors, ++less, i++);
            } else if (colors[i] > colorMid) {
                swap(colors, --more, i);
            } else {
                i++;
            }
        }

        // 对 <colorMid 部分的数组进行排序(partition)
        sortColors2Helper(colors, left, less, colorFrom, colorMid - 1);
        // 对 >colorMid 部分的数组进行排序(partition)
        sortColors2Helper(colors, more, right, colorMid + 1, colorTo);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}