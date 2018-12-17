/**
 * Given an array with both positive and negative numbers in random order. Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.

If there are more positive/negative numbers, put them at the end of the array. The ordering of positive/negative numbers does not matter.

Assumptions:

The given array is not null.
There is no 0 in the array.
Examples:

{1, 2, 3, 4, 5, -1, -1, -1} --> {1, -1, 2, -1, 3, -1, 4, 5}  (The ordering of positive/negative numbers do not matter)
 */

public class Solution {
    public int[] interleave(int[] array) {
      // Write your solution here
      // The following few lines are similar to partition 
          // process of QuickSort.  The idea is to consider 0 
          // as pivot and divide the array around it. 
          int i = -1, temp = 0; 
          for (int j = 0; j < array.length; j++) 
          { 
              if (array[j] < 0) 
              { 
                  i++; 
                  temp = array[i]; 
                  array[i] = array[j]; 
                  array[j] = temp; 
              } 
          } 
    
          // Now all positive numbers are at end and negative numbers at 
          // the beginning of array. Initialize indexes for starting point 
          // of positive and negative numbers to be swapped 
          int pos = i+1, neg = 0; 
    
          // Increment the negative index by 2 and positive index by 1, i.e., 
          // swap every alternate negative number with next positive number 
          while (pos < array.length && neg < pos && array[neg] < 0) 
          { 
              temp = array[neg]; 
              array[neg] = array[pos]; 
              array[pos] = temp; 
              pos++; 
              neg += 2; 
          } 
      return array;
    }
  }
  