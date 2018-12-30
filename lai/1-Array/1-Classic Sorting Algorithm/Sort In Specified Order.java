/**
 * 
Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.

For the elements that are not in A2, append them in the right end of the A1 in an ascending order.

Assumptions:

A1 and A2 are both not null.
There are no duplicate elements in A2.
Examples:

A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
 */

public class Solution {
    class CustomComparator implements Comparator<Integer> {
     private Map<Integer, Integer> map = null;
     public CustomComparator(Map<Integer, Integer> map) {
       this.map = map;
     }
     @Override
     public int compare(Integer x, Integer y) {
       if (map.containsKey(x) && map.containsKey(y)) {
         return map.get(x) - map.get(y);
       } else if (map.containsKey(y)) {
         return 1;
       } else if (map.containsKey(x)) {
         return -1;
       } else {
         return x - y;
       }
     }
   }
   public int[] sortSpecial(int[] A1, int[] A2) {
     // Write your solution here
     if (A1 == null || A2 == null) {
       return A1;
     }
     
     Integer[] res = new Integer[A1.length];
     for (int i = 0; i < A1.length; i++) {
       res[i] = Integer.valueOf(A1[i]);
     }
     
     Map<Integer, Integer> map = new HashMap<>();
     for (int i = 0; i < A2.length; i++) {
       map.put(A2[i], i);
     }
     
     Arrays.sort(res, new CustomComparator(map));
     
     for (int i = 0; i < A1.length; i++) {
       A1[i] = res[i].intValue();
     }
     
     return A1;
   }
 }

 /**
  * import java.util.Arrays;
https://www.mkyong.com/tutorials/java-8-tutorials/
class Main
{
	// Program to convert int array to Integer array in Java
	public static void main (String[] args)
	{
		int[] primitiveArray = { 1, 2, 3, 4, 5 };

		Integer[] boxedArray = Arrays.stream(primitiveArray) // IntStream
								.boxed()				// Stream<Integer>
								.toArray(Integer[]::new);

		System.out.println(Arrays.toString(boxedArray));
	}
}
  */