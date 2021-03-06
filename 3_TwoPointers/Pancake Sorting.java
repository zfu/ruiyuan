/**
 * 894. Pancake Sorting 
 * 
 * Given an an unsorted array, sort the given array. You
 * are allowed to do only following operation on array.
 * 
 * flip(arr, i): Reverse array from 0 to i Unlike a traditional sorting
 * algorithm, which attempts to sort with the fewest comparisons possible, the
 * goal is to sort the sequence in as few reversals as possible.
 * 
 * Example Given array = [6, 7, 10, 11, 12, 20, 23] Use flip(arr, i) function to
 * sort the array.
 * 
 * Notice You only call flip function. Don't allow to use any sort function or
 * other sort methods.
 * 
 * Java：you can call FlipTool.flip(arr, i) C++： you can call FlipTool::flip(arr,
 * i) Python2&3 you can call FlipTool.flip(arr, i)
 */

 /**
 * public class FlipTool {
 *   static public void flip(int[] arr, int i) {
 *      ...
 *   }
 * }
 */
public class Solution {
    /**
     * @param array: an integer array
     * @return: nothing
     */
    public void pancakeSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        // iterate from right to left,
        // keep flipping to make sure the current largest number is at array[0], then, flip it to the last
        int i = array.length; // track the first index of "largest" element
        while (i > 0) {
            for (int j = 0; j < i; j++) {
                if (array[0] < array[j]) {
                    // find a larger element, flip
                    FlipTool.flip(array, j); // larger element will be put in array[0]
                }
            }
            // flip the larger element to left of the current first index of large element
            FlipTool.flip(array, i - 1);
            i--;
        }
    }
}