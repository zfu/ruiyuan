/**
 * Given an array of strings, and two different string s and t. We need to return the smallest indices difference between the two given strings.

Return -1 if we can not find s or t in the array.

Assumptions:

The given array is not null, none of the strings in the array is null.
s and t are different and they are not null.
Examples:

array =  {"this", "is", "a", "is", "fox", "happy"}, the distance of "fox" and "is" is 1.
 */

public class Solution {
    public int distance(String[] array, String source, String target) {
      // Write your solution here
      if(array == null || array.length < 2) {
              return -1;
          }
          int si = -1;
          int ti = -1;
          int result = Integer.MAX_VALUE;
          for(int i = 0; i < array.length; i++) {
              if(array[i] == source) {
                  si = i;
              } else if(array[i] == target) {
                  ti = i;
              }
              if(si != -1 && ti != -1) {
                  result = Math.min(result, Math.abs(ti - si));
              }
          }
          if(si == -1 || ti == -1) {
              return -1;
          }
          return result;
    }
  }
  