
public void mergeSort(int[] nums) {
    int[] tmp = new int[nums.length];
    mergeSort(nums, tmp, 0, nums.length - 1);
}

private void mergeSort(int[] nums, int[] tmp, int start, int end) {
    if (start >= end) {
        return;
    }

    // divide
    int mid = start + (end - start) / 2;
    mergeSort(nums, tmp, start, mid);
    mergeSort(nums, tmp, mid + 1, end);

    // merge
    int index = start;
    int leftStart = start;
    int rightStart = mid + 1;

    while (leftStart <= mid && rightStart <= end) {
        if (nums[leftStart] <= nums[rightStart]) {
            tmp[index++] = nums[leftStart++];
        } else {
            tmp[index++] = nums[rightStart++];
        }
    }

    while (leftStart <= mid) {
        tmp[index++] = nums[leftStart++];
    }

    while (rightStart <= end) {
        tmp[index++] = nums[rightStart++];
    }

    for (int i = start; i <= end; i++) {
        nums[i] = tmp[i];
    }
}
