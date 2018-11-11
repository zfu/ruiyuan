/**
 * 461. Kth Smallest Numbers in Unsorted Array 
 * 
 * Find the kth smallest number in
 * an unsorted integer array.
 * 
 * Example Given [3, 4, 1, 2, 5], k = 3, the 3rd smallest numbers is 3.
 * 
 * Challenge An O(nlogn) algorithm is acceptable, if you can do it in O(n), that
 * would be great.
 */

class Solution {
    /*
     * @param k an integer
     * @param nums an integer array
     * @return kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return helper(nums, 0, nums.length - 1, k - 1);
    }
    
    public int helper(int[] nums, int lo, int hi, int k) {
        if (hi <= lo) {
            return nums[hi];
        }
        
        int pos = partition(nums, lo, hi);
        
        if (pos > k) {
            return helper(nums, lo, pos - 1, k);
        }
        else if (pos < k) {
            return helper(nums, pos + 1, hi, k);
        }
        
        return nums[pos];
    }
    
    public int partition(int[] nums, int lo, int hi) {
        if (hi <= lo) {
            return lo;
        }
        
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        
        int v = nums[lo];
        
        while (i <= gt) {
            if (nums[i] < v) {
                exch(nums, lt++, i++);
            }
            else if (nums[i] > v) {
                exch(nums, gt--, i);
            }
            else {
                i++;
            }
        }
        
        return lt;
    }

    public void exch(int nums[], int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

public class Solution {
    /*
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    private Comparator<Integer> comp = new Comparator<Integer>() {
    	@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	};
	
    public int kthSmallest(int k, int[] nums) {
        if(nums == null || nums.length == 0 || k < 0){
            return 0;
        }
        Queue<Integer> queue = new PriorityQueue<>(comp);
        for(int i = 0; i < nums.length; i++){
            if(queue.size() >= k){
                if(nums[i] < queue.peek()){
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }else{
                queue.offer(nums[i]);
            }
        }
        return queue.poll();
    }
}



// // Java code for kth smallest element in an array 
// import java.util.Arrays; 
// import java.util.Collections; 
  
// class GFG 
// {   
//     // Standard partition process of QuickSort.  
//     // It considers the last element as pivot  
//     // and moves all smaller element to left of 
//     // it and greater elements to right 
//     public static int partition(Integer [] arr, int l,  
//                                                int r) 
//     { 
//         int x = arr[r], i = l; 
//         for (int j = l; j <= r - 1; j++) 
//         { 
//             if (arr[j] <= x) 
//             { 
//                 //Swapping arr[i] and arr[j] 
//                 int temp = arr[i]; 
//                 arr[i] = arr[j]; 
//                 arr[j] = temp; 
  
//                 i++; 
//             } 
//         } 
          
//         //Swapping arr[i] and arr[r] 
//         int temp = arr[i]; 
//         arr[i] = arr[r]; 
//         arr[r] = temp; 
  
//         return i; 
//     } 
      
//     // This function returns k'th smallest element  
//     // in arr[l..r] using QuickSort based method.  
//     // ASSUMPTION: ALL ELEMENTS IN ARR[] ARE DISTINCT 
//     public static int kthSmallest(Integer[] arr, int l,  
//                                          int r, int k) 
//     { 
//         // If k is smaller than number of elements 
//         // in array 
//         if (k > 0 && k <= r - l + 1) 
//         { 
//             // Partition the array around last  
//             // element and get position of pivot  
//             // element in sorted array 
//             int pos = partition(arr, l, r); 
  
//             // If position is same as k 
//             if (pos-l == k-1) 
//                 return arr[pos]; 
              
//             // If position is more, recur for 
//             // left subarray 
//             if (pos-l > k-1)  
//                 return kthSmallest(arr, l, pos-1, k); 
  
//             // Else recur for right subarray 
//             return kthSmallest(arr, pos+1, r, k-pos+l-1); 
//         } 
  
//         // If k is more than number of elements 
//         // in array 
//         return Integer.MAX_VALUE; 
//     } 
  
//     // Driver program to test above methods  
//     public static void main(String[] args) 
//     { 
//         Integer arr[] = new Integer[]{12, 3, 5, 7, 4, 19, 26}; 
//         int k = 3; 
//         System.out.print( "K'th smallest element is " + 
//                      kthSmallest(arr, 0, arr.length - 1, k) ); 
//     } 
// } 
  