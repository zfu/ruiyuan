/**
 * Quick sort.
 */
public void quickSort(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
}
​
private void quickSort(int[] nums, int start, int end) {
    if (start >= end) {
        return;
    }
​
    int left  = start;
    int right = end;
    int mid   = start + (end - start) / 2;
    int pivot = nums[mid];
​
    // overview sorted
    while (left <= right) {
        while (left <= right && nums[left] < pivot) {
            left++;
        }
​
        while (left <= right && nums[right] > pivot) {
            right--;
        }
​
        // swap left >= p with right <= p to make left <= p right >= p
        if (left <= right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
​
    quickSort(nums, start, right);
    quickSort(nums, left, end);
}