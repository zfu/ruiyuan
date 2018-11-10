/**
 * 143. Sort Colors II Given an array of n objects with k different colors
 * (numbered from 1 to k), sort them so that objects of the same color are
 * adjacent, with the colors in the order 1, 2, ... k.
 * 
 * Example Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors
 * in-place to [1, 2, 2, 3, 4].
 * 
 * Challenge A rather straight forward solution is a two-pass algorithm using
 * counting sort. That will cost O(k) extra memory. Can you do it without using
 * extra memory?
 * 
 * Notice You are not suppose to use the library's sort function for this
 * problem.
 * 
 * k <= n
 */

public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0){
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    public void rainbowSort(int[] colors, int start, int end, int From, int To){
        if (start >= end || From >= To){
            return;
        }
        //partition
        int pivot = (From + To) / 2;
        int i = start;
        for (int j = start; j <= end; ++j){
            if (colors[j] <= pivot){
                int tmp = colors[i];
                colors[i] = colors[j];
                colors[j] = tmp;
                i++;
            }
        }
        //invariant: any index < i has value <= pivot
        //any index >= i has value > pivot
        //sort left and right subproblem
        rainbowSort(colors, start, i - 1, From, pivot);
        rainbowSort(colors, i, end, pivot + 1, To);   
    }
}