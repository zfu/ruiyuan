public void heapSort(int[] nums) {
    // build max heap: for each root node bottom to top and right to left
    for (int i = nums.length / 2; i >= 0; i--) {
        maxHeapify(nums, i, nums.length - 1);
    }
​
    // swap first and adjust the new root to right position
    for (int i = nums.length - 1; i > 0; i--) {
        int tmp = nums[0];
        nums[0] = nums[i];
        nums[i] = tmp;
        // after each iteration, largest goes to ith, next end at i - 1
        maxHeapify(nums, 0, i - 1);
    }
}
​
private void maxHeapify(int[] nums, int start, int end) {
    int parent = start;
​
    // top to bottom
    while (parent <= end) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int child = -1;
​
        if (left <= end && right <= end) {
            // large value as swap candidate
            child = (nums[left] >= nums[right] ? left : right);
        } else if (left <= end) {
            child = left;
        } else {
            return;
        }
​
        // max heap root >= left && root >= right
        if (nums[parent] >= nums[child]) {
            return;
        }
​
        // swap
        int tmp = nums[parent];
        nums[parent] = nums[child];
        nums[child] = tmp;
​
        // update parent index
        parent = child;
    }
}
