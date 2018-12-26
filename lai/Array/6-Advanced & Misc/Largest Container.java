/**
 * Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.

Assumptions

The given array is not null and has size of at least 2
Examples

{ 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8.
 */

public class Solution {
    public int largest(int[] array) {
      // Write your solution here
      if (array == null || array.length < 2) {
        return 0;
      }
      
      int left = 0, right = array.length - 1;
      int res = 0;
  
      while (left < right) {
        res = Math.max(res, Math.min(array[left], array[right]) * (right - left));
        if (array[left] < array[right]) {
          left++;
        } else {
          right--;
        }
      }
      return res;
    }
  }

  public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<> ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }
}